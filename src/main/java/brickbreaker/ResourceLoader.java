package brickbreaker;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import javax.json.*;

import brickbreaker.common.Error;
import brickbreaker.controllers.state.ErrorListener;
import brickbreaker.model.user.User;

/**
 * Class to load the resources: map files, game icons, ranking stats.
 */
public class ResourceLoader {

    public final Integer MAP_COLUMNS_FILE_FORMAT = 6;
    public final Integer MAP_ROWS_FILE_FORMAT = 3;

    private final String NAME = "name";
    private final String SCORE = "score";
    private final String LEVEL_REACHED = "levelReached";

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
        try (JsonReader reader = Json.createReader(new FileReader(filePath))) {
            return reader.readArray();
        } catch (Exception e) {
            ErrorListener.notifyError(err);
            e.printStackTrace();
        }
        return Json.createArrayBuilder().build();
    }

    private JsonArray addElem(final JsonArray jsonArray, final JsonObject elemento) {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder(jsonArray);
        jsonArrayBuilder.add(elemento);
        return jsonArrayBuilder.build();
    }

    private void writeJson(final String filePath, final JsonArray jsonArray, Error err) {
        try (JsonWriter writer = Json.createWriter(new FileWriter(filePath))) {
            writer.writeArray(jsonArray);
        } catch (Exception e) {
            ErrorListener.notifyError(err);
            e.printStackTrace();
        }
    }

    /**
     * @return the list of name ranks files in the directory
     */
    private List<String> getRanksFileName() {
        return Arrays.asList(new File(ranksPath).list());
    }

    /**
     * Method to get from a file a rank list of players.
     * @param file
     * @return a list of players stats
     */
    public Map<String,Integer> getRank(final String file) {
        
        Map<String,Integer> rank = new HashMap<>();
        JsonArray js = this.loadJson(this.ranksPath + sep + file, Error.RANKLOADER_ERROR);

        for (JsonValue element : js) {
            JsonObject jObj = element.asJsonObject();
            rank.put(jObj.getString(NAME), jObj.getInt(SCORE));
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(rank.entrySet());
        entryList.sort(Map.Entry.comparingByValue());

        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    /**
     * Method to write on a file the list of players stats passed.
     * @param rank
     * @param file
     */
    public void writeRank(final Map<String,Integer> rank, final String file) {

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Map.Entry<String, Integer> e : rank.entrySet()) {
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add(NAME, e.getKey())
                    .add(SCORE, e.getValue())
                    .build();
            arrayBuilder.add(jsonObject);
        }
        this.writeJson(this.ranksPath + sep + file, arrayBuilder.build(), Error.RANKWRITER_ERROR);
    }

    /**
     * Method to read on a file the list of users.
     * @return a list of users
     */
    public List<User> getUsers() {

        List<User> users = new ArrayList<>();
        JsonArray js = this.loadJson(this.userPath, Error.USERLOADER_ERROR);

        for (JsonValue element : js) {
            users.add(new User(element.asJsonObject().getString(NAME)));
        }
        return users;
    }

    /**
     * Method to get the level reached by the user.
     * @param username
     * @return an Integer representing the level
     */
    public Integer getLevelReached(final String username) {
        JsonArray js = this.loadJson(this.userPath, Error.USERLOADER_ERROR);
        for (JsonValue element : js) {
            JsonObject jObj = element.asJsonObject();
            if (jObj.getString(NAME).equals(username)) {
                return jObj.getInt(LEVEL_REACHED);
            }
        }
        return 1;
    }

    /**
     * Method to increment the level reached by the user.
     * @param username
     */
    public void incLevelReached(final String username) {
        JsonArray js = this.loadJson(this.userPath, Error.USERLOADER_ERROR);
        for (int i = 0; i < js.size(); i++) {
            JsonObject jsonObject = js.getJsonObject(i);
            if (jsonObject.getString(NAME).equals(username)) {
                int currentLevel = jsonObject.getInt(LEVEL_REACHED);
                jsonObject = Json.createObjectBuilder(jsonObject)
                        .add(LEVEL_REACHED, currentLevel + 1)
                        .build();
                js = (JsonArray) js.set(i, jsonObject);
                break;
            }
        }
        this.writeJson(this.userPath, js, Error.USERWRITER_ERROR);
    }

    /**
     * Method to add a new user to the json user file.
     * @param user
     */
    public void addUser(final String user) {

        JsonArray js = this.loadJson(this.userPath, Error.USERLOADER_ERROR);
        JsonObject newUser = Json.createObjectBuilder()
                .add(NAME, user)
                .add(LEVEL_REACHED, 1)
                .build();
        this.writeJson(this.userPath, this.addElem(js, newUser), Error.USERWRITER_ERROR);
    }

    /**
     * Method to remove a user from the json user file and from rank.
     * @param user
     */
    public void removeUser(final String username) {
        
        JsonArray js = this.loadJson(this.userPath, Error.USERLOADER_ERROR);
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (JsonValue jv : js){
            JsonObject j = jv.asJsonObject();
            if(!j.getString(NAME).equals(username)){
                jsonArrayBuilder.add(j);
            }
        }
        this.writeJson(this.userPath, jsonArrayBuilder.build(), Error.USERWRITER_ERROR);

        for (String s : this.getRanksFileName()) {
            js = this.loadJson(this.ranksPath + sep + s, Error.RANKLOADER_ERROR);
            jsonArrayBuilder = Json.createArrayBuilder();
            for (JsonValue jv : js){
                JsonObject j = jv.asJsonObject();
                if(!j.getString(NAME).equals(username)){
                    jsonArrayBuilder.add(j);
                }
            }
            this.writeJson(this.ranksPath + sep + s, jsonArrayBuilder.build(), Error.USERWRITER_ERROR);
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
