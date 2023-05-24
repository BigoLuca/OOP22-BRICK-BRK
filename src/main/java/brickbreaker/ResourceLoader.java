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
     * Method to get from a file a rank list of players.
     * @param file
     * @return a list of players stats
     */
    public Map<String,Integer> getRank(final String file) {
        
        Map<String,Integer> rank = new HashMap<>();
        JsonArray js = this.loadJson(this.ranksPath + sep + file, Error.RANKLOADER_ERROR);

        for (JsonValue element : js) {
            JsonObject jObj = element.asJsonObject();
            rank.put(jObj.getString("name"), jObj.getInt("score"));
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
                    .add("name", e.getKey())
                    .add("score", e.getValue())
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
            JsonObject jObj = element.asJsonObject();
            JsonArray scoresArray = jObj.getJsonArray("scores");
            Map<Integer, Integer> map = new HashMap<>();
            for(JsonValue j : scoresArray) {
                JsonObject jo = j.asJsonObject();
                map.put(jo.getInt("level"), jo.getInt("score"));
            }
            users.add(new User(jObj.getString("name"), map, jObj.getInt("levelReached")));
        }

        return users;
    }

    /**
     * Method to add a user to the json user file.
     * @param user
     */
    public void addUser(final User user) {

        JsonArray js = this.loadJson(this.userPath, Error.USERLOADER_ERROR);
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        for (Map.Entry<Integer, Integer> entry : user.getScores().entrySet()){
            jsonObjectBuilder.add("level", entry.getKey());
            jsonObjectBuilder.add("score", entry.getValue());
            jsonArrayBuilder.add(jsonObjectBuilder.build());
        }
        JsonObject newUser = Json.createObjectBuilder()
                .add("name", user.getName())
                .add("scores", jsonArrayBuilder.build())
                .add("levelReached", user.getLevelReached())
                .build();

        this.writeJson(this.userPath, this.addElem(js, newUser), Error.USERWRITER_ERROR);
    }

    /**
     * @return the list of name ranks files in the directory
     */
    private List<String> getRanksFileName() {
        return Arrays.asList(new File(ranksPath).list());
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
            if(!j.getString("name").equals(username)){
                jsonArrayBuilder.add(j);
            }
        }
        this.writeJson(this.userPath, jsonArrayBuilder.build(), Error.USERWRITER_ERROR);

        for (String s : this.getRanksFileName()) {
            js = this.loadJson(this.ranksPath + sep + s, Error.RANKLOADER_ERROR);
            jsonArrayBuilder = Json.createArrayBuilder();
            for (JsonValue jv : js){
                JsonObject j = jv.asJsonObject();
                System.out.println(j.getString("name"));
                if(!j.getString("name").equals(username)){
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
