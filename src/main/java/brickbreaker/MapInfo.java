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
