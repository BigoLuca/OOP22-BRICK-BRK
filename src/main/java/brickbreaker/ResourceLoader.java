package brickbreaker;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import brickbreaker.model.rank.PlayerStats;

/**
 * Class to load the resources: map files, game icons, ranking stats.
 */
public class ResourceLoader {

    public final Integer MAP_COLUMNS_FILE_FORMAT = 20;
    public final Integer MAP_ROWS_FILE_FORMAT = 10;

    private static ResourceLoader instance;
    private String mapsPath;
    private String ranksPath;
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

    /**
     * Method to get from a file a rank list of players.
     * @param file
     * @return a list of players stats
     */
    public List<PlayerStats> getRank(final String file) {
        
        List<PlayerStats> rank = new ArrayList<>();

        try (Reader br = new BufferedReader(new FileReader(this.ranksPath + sep + file))) {
            JsonElement jsonElement = com.google.gson.JsonParser.parseReader(br);
            
            if (jsonElement.isJsonArray()) {
                for (JsonElement element : jsonElement.getAsJsonArray()) {
                    JsonObject jObj = element.getAsJsonObject();
                    rank.add(new PlayerStats(
                                jObj.get("nome").getAsString(), 
                                jObj.get("punteggio").getAsInt())
                            );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return rank;
    }

    /**
     * Method to write on a file the list of players stats passed.
     * @param rank
     * @param file
     * @return
     */
    public boolean writeRank(final List<PlayerStats> rank, final String file) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(rank);

        try (Writer writer = new FileWriter(this.ranksPath + sep + file)) {
            writer.write(json);
            writer.flush();
            System.out.println("File JSON scritto con successo.");
            return true;
        } catch (IOException e) {
            System.out.println("Errore durante la scrittura del file JSON: " + e.getMessage());
            return false;
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
