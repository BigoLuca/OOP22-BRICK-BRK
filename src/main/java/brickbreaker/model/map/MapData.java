package brickbreaker.model.map;

import java.util.List;

import brickbreaker.common.Difficulty;
import brickbreaker.common.GameImages;

public class MapData {

    public static final int MAP_ROWS_FILE_FORMAT = 6;
    public static final int MAP_COLUMNS_FILE_FORMAT = 6;

    private int index;
    private String landscape;
    private List<Integer> map;
    private Integer difficulty;
    private String name;

    /**
     * Map info constructor.
     * @param i
     * @param b
     * @param d
     * @param name
     * @param landscape
     */
    public MapData(final Integer i, final List<Integer> b, final Integer d, final String name, final String landscape) {
        this.index = i;
        this.map = b;
        this.difficulty = d;
        this.name = name;
        this.landscape = landscape;
        return;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public GameImages getLandscape() {
        return GameImages.valueOf(landscape);
    }

    public void setLandscape(String landscape) {
        this.landscape = landscape;
    }

    public List<Integer> getMap() {
        return map;
    }

    public void setMap(List<Integer> map) {
        this.map = map;
    }

    public Difficulty getDifficulty() {
        return Difficulty.values()[difficulty];
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty.ordinal();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
