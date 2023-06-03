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
import java.util.Scanner;

import com.google.gson.*;

import brickbreaker.common.Error;
import brickbreaker.controllers.listener.ErrorListener;
import brickbreaker.model.user.User;

/**
 * Class to load the resources: map files, game icons, ranking stats.
 */
public class ResourceLoader {

    public final Integer MAP_COLUMNS_FILE_FORMAT = 6;
    public final Integer MAP_ROWS_FILE_FORMAT = 3;

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

    private List<Integer> currentMapList;
    private String currentMapName;

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
        this.currentMapName = "";
        this.currentMapList = new ArrayList<>();
    }

    /**
     * @return the list of name map files in the directory
     */
    public List<String> getMapsNames() {
        return Arrays.asList(new File(mapsPath).list());
    }

    /**
     * Retunr the game map request, if new: load the new map, else return the same in memory.
     * If name map is absent catch exception and return an optional empty.
     * If size map is not correct return an optional empty.
     * @param fileName is the name of map file to load
     * @return a list of bricks num life
     */
    public Optional<List<Integer>> loadMap(final String fileName) {

        if (!this.currentMapName.equals(fileName)) {
            try (Scanner sc = new Scanner(new File(this.mapsPath + sep + fileName))) {
                currentMapList.clear();
                while (sc.hasNextInt()) {
                    this.currentMapList.add(sc.nextInt());
                }
                sc.close();
                this.currentMapName = fileName;
            } catch (FileNotFoundException e) {
                this.currentMapName = "";
                e.printStackTrace();
                return Optional.empty();
            }
        }
        return Optional.of(this.currentMapList);
    }

    /**
     * @return the number of bricks for each line
     */
    public Integer getMapColumns() {
        return this.MAP_COLUMNS_FILE_FORMAT;
    }

    /**
     * @return the number of bricks for each column
     */
    public Integer getMapRows() {
        return this.MAP_ROWS_FILE_FORMAT;
    }

    private JsonArray loadJson(final String filePath, Error err) {
        try {
            return JsonParser.parseReader(new FileReader(filePath)).getAsJsonArray();
        } catch (Exception e) {
            ErrorListener.notifyError(err);
            e.printStackTrace();
        }
        return new JsonArray();
    }

    private void writeJson(final String filePath, final JsonArray jsonArray, Error err) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(jsonArray, fileWriter);
        } catch (Exception e) {
            ErrorListener.notifyError(err);
            e.printStackTrace();
        }
    }

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
     * Method to write on a file the list of players stats passed.
     * @param file
     * @param level
     * @param playerName
     * @param newScore
     */
    public Integer writeRank(final String file, final Integer level, final String playerName, final Integer newScore) {

        Integer diff = 0;
        JsonArray js = this.loadJson(this.ranksPath + sep + file, Error.RANKLOADER_ERROR);

        try {
            JsonArray scoresArray = js.get(level).getAsJsonObject().getAsJsonArray(SCORES);
            Integer idx = getIdxUserName(playerName, scoresArray);
            if (idx >= 0) {
                JsonObject scoreObject = scoresArray.get(idx).getAsJsonObject();
                int currentScore = scoreObject.get(SCORE).getAsInt();
                if (newScore > currentScore) {
                    scoreObject.addProperty(SCORE, newScore);
                    diff = newScore - currentScore;
                }
            } else {
                JsonObject newScoreObject = new JsonObject();
                newScoreObject.addProperty(NAME, playerName);
                newScoreObject.addProperty(SCORE, newScore);
                scoresArray.add(newScoreObject);
                diff = newScore;
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
            diff = newScore;
        }
        this.writeJson(this.ranksPath + sep + file, js, Error.RANKWRITER_ERROR);
        return diff;
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
    public void addUser(final String playerName) {

        JsonArray js = this.loadJson(this.userPath, Error.USERLOADER_ERROR);
        if (getIdxUserName(playerName, js) < 0) {
            JsonObject newUser = new JsonObject();
            newUser.addProperty(NAME, playerName);
            newUser.addProperty(LEVEL_REACHED, 1);
            js.add(newUser);
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


    public Integer[][] convertToListArray(List<Integer> list, int MCols, int MRows) {
        Integer[][] array = new Integer[MRows][MCols];
        Integer index = 0;

        for (int row = 0; row < MRows; row++) {
            for (int col = 0; col < MCols; col++) {
                if (index < list.size()) {
                    array[row][col] = list.get(index);
                    index++;
                } else {
                    // If the list is shorter than the desired array size,
                    // you can decide how to handle the remaining elements.
                    // Here, we fill the remaining elements with 0.
                    array[row][col] = 0;
                }
            }
        }

        return array;
    }
}
