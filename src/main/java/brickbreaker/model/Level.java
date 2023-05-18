package brickbreaker.model;

import brickbreaker.model.rank.GameRank;
import brickbreaker.model.rank.Rank;

/**
 * Class representing a single level.
 */
public class Level {

    private final Integer id;
    private final String nameMap;
    private Rank rank;

    /**
     * Level constructor.
     * @param id
     * @param nameMap
     */
    public Level(final Integer id, final String nameMap) {
        this.id = id;
        this.nameMap = nameMap;
        // TODO change GameRank
        this.rank = new GameRank(10);
    }

    /**
     * @return the level number
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * @return the level map name
     */
    public String getNameMap() {
        return this.nameMap;
    }

    /**
     * @return the level rank
     */
    public Rank getRank() {
        return this.rank;
    }
}
