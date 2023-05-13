package brickbreaker.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to work with map files.
 * 
 * @author Bighini Luca
 */
public class GameMap {

    private static final Integer MAPCOL = 20;
    private static final Integer MAPLINE = 10;
    private final String sep;
    private final String path;

    private List<Integer> currentMap;
    private String mapName;

    /**
     * GameMap constructor.
     */
    public GameMap() {
        this.currentMap = new ArrayList<Integer>();
        this.mapName = "";
        this.sep = File.separator + File.separator;
        this.path = "." + sep + "app" + sep + "src" + sep + "main" + sep + "resources" + sep + "mapsFile";
    }

    /**
     * @return the directory path of maps files
     */
    protected String getPathMapFile() {
        return this.path;
    }

    /**
     * Retunr the game map request, if new: load the new map, else return the same in memory.
     * If name map is absent catch exception and return an empty list.
     * If size map is not correct return an empty list.
     * @param fileName is the name of map file to load
     * @return a list of bricks num life
     */
    public List<Integer> loadMap(final String fileName) {
        if (!this.mapName.equals(fileName)) {
            currentMap.clear();
            try (Scanner sc = new Scanner(new File(path + sep + fileName))) {
                while (sc.hasNextInt()) {
                    currentMap.add(sc.nextInt());
                }
                sc.close();
                this.mapName = fileName;
            } catch (FileNotFoundException e) {
                this.mapName = "";
                e.printStackTrace();
                return List.of();
            }

            if (currentMap.size() != MAPCOL * MAPLINE) {
                return List.of();
            }
        }
        return this.currentMap;
    }

    /**
     * @return the number of bricks for each line
     */
    public Integer getMapColumns() {
        return GameMap.MAPCOL;
    }

    /**
     * @return the number of bricks for each column
     */
    public Integer getMapLines() {
        return GameMap.MAPLINE;
    }
}
