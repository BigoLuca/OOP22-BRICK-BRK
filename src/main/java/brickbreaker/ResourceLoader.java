package brickbreaker;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import brickbreaker.common.Mode;
import brickbreaker.model.rank.PlayerStats;

/**
 * Class to load the resources: map files, game icons, ranking stats.
 */
public class ResourceLoader {

    public static final Integer MAP_COLUMNS_FILE_FORMAT = 20;
    public static final Integer MAP_ROWS_FILE_FORMAT = 10;

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
        return ResourceLoader.MAP_COLUMNS_FILE_FORMAT;
    }

    /**
     * @return the number of bricks for each column
     */
    public Integer getMapRows() {
        return ResourceLoader.MAP_ROWS_FILE_FORMAT;
    }

    @SuppressWarnings("unchecked")
    public SortedSet<PlayerStats> getRank(final Mode mode) {

        SortedSet<PlayerStats> rank = new TreeSet<>();
        String file = mode.equals(Mode.LEVEL) ? "levels.txt" : "endless.txt";
        String path = this.ranksPath + sep + file;
        
        try (ObjectInput input = new ObjectInputStream(
            new BufferedInputStream(new FileInputStream(path)))) {
                rank.addAll((SortedSet<PlayerStats>) input.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rank;
    }

    public void writeRank(final SortedSet<PlayerStats> rank, final Mode mode) {
        String file = mode.equals(Mode.LEVEL) ? "levels.txt" : "endless.txt";
        String path = this.ranksPath + sep + file;
        try(ObjectOutput output = new ObjectOutputStream(
            new BufferedOutputStream(new FileOutputStream(path)))) {
                output.writeObject(rank);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
