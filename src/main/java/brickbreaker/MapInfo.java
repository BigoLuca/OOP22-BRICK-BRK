package brickbreaker;

import java.util.List;

import brickbreaker.common.Difficulty;
import brickbreaker.common.GameImages;

/**
 * Map info class.
 */
public class MapInfo {

    private List<Integer> map;
    private GameImages landscape;
    private Difficulty difficulty;
    private String name;
    private Integer index;

    /**
     * Map info constructor.
     * @param i
     * @param b
     * @param d
     * @param name
     * @param landscape
     */
    public MapInfo(final Integer i, final List<Integer> b, final Difficulty d, final String name, final String landscape) {
        this.index = i;
        this.map = b;
        this.difficulty = d;
        this.name = name;
        this.landscape = GameImages.valueOf(landscape);
    }

    /**
     * Method to get landscape.
     * @return GameImages landscape
     */
    public GameImages getLandscapeData() {
        return this.landscape;
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return this.index;
    }

    /**
     * @return the bricks data
     */
    public List<Integer> getBricksData() {
        return this.map;
    }

    /**
     * @return the difficulty
     */
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }
}
