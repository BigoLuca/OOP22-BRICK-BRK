package brickbreaker;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Iterator;

import brickbreaker.common.Difficulty;
import brickbreaker.common.Error;
import brickbreaker.common.GameImages;
import brickbreaker.common.GameObjectsImages;
import brickbreaker.controllers.listener.ErrorListener;
import brickbreaker.model.user.User;
import javafx.scene.image.Image;

/**
 * Class to load the resources: map files, game icons, ranking stats.
 */
public class ResourceLoader {

    public final Integer map_COLUMNS_FILE_FORMAT = 6;
    public final Integer map_ROWS_FILE_FORMAT = 6;

    private final String NAME = "name";
    private final String SCORE = "score";
    private final String SCORES = "scores";
    private final String LEVEL_REACHED = "levelReached";
    private final String LEVEL = "level";

    private static ResourceLoader instance;
    private String mapsPath;
    private String ranksPath;
    private String userPath;
    private String sep;

    /**
     * @return the instance of ResourceLoader if it not exists yet.
     */
    public static ResourceLoader getInstance() {
        if (instance == null) {
            instance = new ResourceLoader();
        }

        return instance;
    }

    /**
     * Initializes path.
     */
    private ResourceLoader() {
        this.sep = File.separator + File.separator;
        this.mapsPath = "." + sep + "src" + sep + "main" + sep + "resources" + sep + "mapsFile";
        this.ranksPath = "." + sep + "src" + sep + "main" + sep + "resources" + sep + "ranks";
        this.userPath = "." + sep + "src" + sep + "main" + sep + "resources" + sep + "users" + sep + "user.json";
    }

    /**
     * Method to load the json file.
     */
    public void start() {
        Arrays.stream(GameImages.values()).forEach(value -> {
            value.setImage(new Image(ClassLoader.getSystemResourceAsStream(value.getFilePath())));
        });

        Arrays.stream(GameObjectsImages.values()).forEach(value -> {
            value.setImage(new Image(ClassLoader.getSystemResourceAsStream(value.getFilePath())));
        });
    }

    /**
     * @return the list of name map files in the directory
     */
    public List<MapInfo> getMapsInfo() {
        List<MapInfo> m = new ArrayList<>();
        JsonArray a = this.loadJson(this.mapsPath + sep + "levels.json", Error.MAPLOADER_ERROR);
        Iterator<JsonElement> iterator = a.iterator();

        while (iterator.hasNext()) {
            JsonObject e = iterator.next().getAsJsonObject();
            Integer index = e.get("index").getAsInt();
            String landScape = e.get("landscape").getAsString();
            List<Integer> map = new ArrayList<>();

            for (Integer i = 0; i < map_ROWS_FILE_FORMAT; i++) {
                for (Integer j = 0; j < map_COLUMNS_FILE_FORMAT; j++) {
                    map.add(e.get("map").getAsJsonArray().get(i).getAsJsonArray().get(j).getAsInt());
                }
            }

            Difficulty d = Difficulty.valueOf(e.get("difficulty").getAsString());
            String name = e.get("name").getAsString();
            MapInfo i = new MapInfo(index, map, d, name, landScape);

            m.add(i);
        }

        return m;
    }

    /**
     * @return the number of bricks for each line
     */
    public Integer getMapColumns() {
        return this.map_COLUMNS_FILE_FORMAT;
    }

    /**
     * @return the number of bricks for each column
     */
    public Integer getMapRows() {
        return this.map_ROWS_FILE_FORMAT;
    }

    /**
     * Method to get the ranking of the game.
     * 
     * @param filePath the path of the file
     * @param err      the error to notify
     * @return the ranking of the game
     */
    private JsonArray loadJson(final String filePath, final Error err) {
        try {
            return JsonParser.parseReader(new FileReader(filePath)).getAsJsonArray();
        } catch (Exception e) {
            ErrorListener.notifyError(err);
            e.printStackTrace();
        }
        return new JsonArray();
    }

    /**
     * Method save in a json file the ranking of the game.
     * 
     * @param filePath  the path of the file
     * @param jsonArray the JsonArray of the ranking
     * @param err       the error to notify
     */
    private void writeJson(final String filePath, final JsonArray jsonArray, Error err) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(jsonArray, fileWriter);
        } catch (Exception e) {
            ErrorListener.notifyError(err);
            e.printStackTrace();
        }
    }

    /**
     * Method to get the index of the user in the ranking.
     * 
     * @param playerName the name of the player
     * @param js         the JsonArray of the ranking
     * @return the index of the user in the ranking
     */
    private Integer getIdxUserName(final String playerName, final JsonArray js) {
        for (int i = 0; i < js.size(); i++) {
            JsonObject scoreObject = js.get(i).getAsJsonObject();
            if (scoreObject.get(NAME).getAsString().equals(playerName)) {
                return i;
            }
        }
        return -1;
    }    

    /**
     * Method to get from a file the map landscape.
     * @param mapName
     * @return GameImages rappresenting the landscape of the map
     */
    public GameImages getMapLandscape(final String mapName) {
        try {
            JsonObject f = JsonParser.parseReader(new FileReader(mapsPath + sep + mapName)).getAsJsonObject();
            return GameImages.valueOf(f.get("landscape").getAsString());
        } catch (FileNotFoundException e) {
            ErrorListener.notifyError(Error.MAPLOADER_ERROR);
            return GameImages.NOT_LOADED_ITEM;
        }
    }

    /**
     * Method to get from a file the difficulty of a level.
     * @param file
     * @param level
     * @return Optional of Difficulty 
     */
    public Optional<Difficulty> getMapDifficulty(final String mapName) {
        try {
            JsonObject f =  JsonParser.parseReader(new FileReader(mapsPath + sep + mapName)).getAsJsonObject();
            String d = f.get("difficulty").getAsString();
            return Optional.of(Difficulty.valueOf(d));
        } catch (FileNotFoundException e) {
            ErrorListener.notifyError(Error.MAPLOADER_ERROR);
            return null;
        } catch (NullPointerException n) {
            return Optional.empty();
        }
    }

    /**
     * @return the list of name ranks files in the directory
     */
    private List<String> getRanksFileName() {
        return Arrays.asList(new File(ranksPath).list());
    }

    /**
     * Method to get from a file a level rank list of players in descending order.
     * @param file
     * @param level
     * @return a list of players stats
     */
    public Map<String,Integer> getRank(final String file, final Integer level) {
        Map<String,Integer> rank = new HashMap<>();
        JsonArray js = loadJson(this.ranksPath + sep + file, Error.RANKLOADER_ERROR);
        try {
            JsonArray scoresArray = js.get(level).getAsJsonObject().getAsJsonArray(SCORES);
            for (JsonElement scoreElement : scoresArray) {
                JsonObject scoreObject = scoreElement.getAsJsonObject();
                String name = scoreObject.get(NAME).getAsString();
                Integer score = scoreObject.get(SCORE).getAsInt();
                rank.put(name, score);
            }
        } catch (IndexOutOfBoundsException e) {
            return new HashMap<>();
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(rank.entrySet());
        entryList.sort(Map.Entry.comparingByValue());

        Collections.reverse(entryList);

        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    /**
     * Method to get from a file a list of all level rank list of players in descending order.
     * @param filename
     * @return a list of players stats
     */
    public List<Map<String, Integer>> getAllRanks(final String filename) {
        List<Map<String, Integer>> rawRanks = new ArrayList<>();
        Integer max = this.loadJson(this.ranksPath + sep + filename, Error.RANKLOADER_ERROR).size();

        try {
            for (Integer i = 0; i < max; i++) {
                rawRanks.add(this.getRank(filename, i));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("end");
        }
        return rawRanks;
    }
    
    /**
     * Method to write on a file the list of players stats passed.
     * @param file
     * @param level
     * @param playerName
     * @param newScore
     */
    public void writeRank(final String file, final Integer level, final String playerName, final Integer newScore) {

        JsonArray js = this.loadJson(this.ranksPath + sep + file, Error.RANKLOADER_ERROR);

        try {
            JsonArray scoresArray = js.get(level).getAsJsonObject().getAsJsonArray(SCORES);
            Integer idx = getIdxUserName(playerName, scoresArray);
            if (idx >= 0) {
                JsonObject scoreObject = scoresArray.get(idx).getAsJsonObject();
                int currentScore = scoreObject.get(SCORE).getAsInt();
                if (newScore > currentScore) {
                    scoreObject.addProperty(SCORE, newScore);
                }
            } else {
                JsonObject newScoreObject = new JsonObject();
                newScoreObject.addProperty(NAME, playerName);
                newScoreObject.addProperty(SCORE, newScore);
                scoresArray.add(newScoreObject);
            }
        } catch (IndexOutOfBoundsException e) {
            JsonObject newLevelObject = new JsonObject();
            newLevelObject.addProperty(LEVEL, level);
            JsonArray scoresArray = new JsonArray();
            JsonObject newScoreObject = new JsonObject();
            newScoreObject.addProperty(NAME, playerName);
            newScoreObject.addProperty(SCORE, newScore);
            scoresArray.add(newScoreObject);
            newLevelObject.add(SCORES, scoresArray);
            js.add(newLevelObject);
        }
        this.writeJson(this.ranksPath + sep + file, js, Error.RANKWRITER_ERROR);
    }

    /**
     * Method to read on a file the list of users.
     * @return a list of users
     */
    public List<User> getUsers() {

        List<User> users = new ArrayList<>();
        JsonArray js = this.loadJson(this.userPath, Error.USERLOADER_ERROR);
        for (JsonElement element : js) {
            JsonObject jObj = element.getAsJsonObject();
            users.add(new User(jObj.get(NAME).getAsString()));
        }
        return users;
    }

    /**
     * Method to get the level reached by the user.
     * @param playerName
     * @return an Integer representing the level
     */
    public Integer getLevelReached(final String playerName) {
        JsonArray js = this.loadJson(this.userPath, Error.USERLOADER_ERROR);
        try {
            return js.get(getIdxUserName(playerName, js)).getAsJsonObject().get(LEVEL_REACHED).getAsInt();
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    /**
     * Method to increment the level reached by the user.
     * @param playerName
     */
    public void incLevelReached(final String playerName) {
        JsonArray js = this.loadJson(this.userPath, Error.USERLOADER_ERROR);
        try {
            JsonObject jObj = js.get(getIdxUserName(playerName, js)).getAsJsonObject();
            int currentLevel = jObj.get(LEVEL_REACHED).getAsInt();
            jObj.addProperty(LEVEL_REACHED, currentLevel + 1);
            this.writeJson(this.userPath, js, Error.USERWRITER_ERROR);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to add a new user to the json user file.
     * @param playerName
     */
    public void addUser(final User newUser) {

        JsonArray js = this.loadJson(this.userPath, Error.USERLOADER_ERROR);
        if (getIdxUserName(newUser.getName(), js) < 0) {
            JsonObject u = new JsonObject();
            u.addProperty(NAME, newUser.getName());
            u.addProperty(LEVEL_REACHED, 1);
            js.add(u);
        }
        this.writeJson(this.userPath, js, Error.USERWRITER_ERROR);
    }

    /**
     * Method to remove a user from the json user file and from rank.
     * @param playerName
     */
    public void removeUser(final String playerName) {
        
        JsonArray js = this.loadJson(this.userPath, Error.USERLOADER_ERROR);
        try {
            js.remove(getIdxUserName(playerName, js));
        } catch (IndexOutOfBoundsException e) {}
        this.writeJson(this.userPath, js, Error.USERWRITER_ERROR);

        for (String s : this.getRanksFileName()) {
            js = this.loadJson(this.ranksPath + sep + s, Error.RANKLOADER_ERROR);
            for (JsonElement element : js) {
                JsonArray scoresArray = element.getAsJsonObject().get(SCORES).getAsJsonArray();
                Integer idx = getIdxUserName(playerName, scoresArray);
                if (idx >= 0) {
                    scoresArray.remove(idx);
                }
            }
            this.writeJson(this.ranksPath + sep + s, js, Error.USERWRITER_ERROR);
        }
    }
}
