package brickbreaker.model;

import brickbreaker.model.state.GameState;
import brickbreaker.model.state.GameStateImpl;
import brickbreaker.model.world.World;

/**
 * Class representing a single level.
 */
public class Level {

    private final Integer id;
    private GameState gs;
    private Integer score;

    /**
     * Level constructor.
     * @param id
     * @param nameMap
     */
    public Level(final Integer id, final World levelWorld) {
        this.id = id;
        this.gs = new GameStateImpl(levelWorld);
        this.score = 0;
    }

    /**
     * @return the level number
     */
    public Integer getId() {
        return this.id;
    }

    public GameState getGameState() {
        return this.gs;
    }

    public void setGameState(GameState g) {
        this.gs = g;
    }

    /**
     * This method gets the current points scored by the user.
     * @return An integer value.
     */
    public final int getScore() {
        return this.score;
    }

    /**
     * This method increments the current score by the value
     * specified by the increment parameter.
     * @param increment an integer value which is the increment.
     */
    public final void incScore(final Integer increment) {
        this.score += increment;
    }

    /**
     * This method decrements the current score by the value
     * specified by the decrement parameter.
     * @param decrement an integer value which is the decrement value.
     */
    public final void decScore(final Integer decrement) {
        this.score -= decrement;
    }
}
