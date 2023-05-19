package brickbreaker.model;

import brickbreaker.model.factory.WorldFactory;
import brickbreaker.model.rank.PlayerStats;
import brickbreaker.model.state.GameState;
import brickbreaker.model.state.GameStateImpl;

/**
 * Class representing a single level.
 */
public class Level {

    private final Integer id;
    private GameState gs;
    private PlayerStats userStats;

    /**
     * Level constructor.
     * @param id
     * @param nameMap
     */
    public Level(final Integer id, final String nameMap, final Integer diff) {
        this.id = id;
        this.gs = new GameStateImpl(WorldFactory.getInstance().getWorld(nameMap, diff));
        this.userStats.setScore(0);
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

    public void setStats(final PlayerStats p) {
        this.userStats = p;
    }

    public PlayerStats getStats() {
        return this.userStats;
    }

    /**
     * This method gets the current points scored by the user.
     * @return An integer value.
     */
    public final int getScore() {
        return this.userStats.getScore();
    }

    /**
     * This method increments the current score by the value
     * specified by the increment parameter.
     * @param increment an integer value which is the increment.
     */
    public final void incScore(final Integer increment) {
        this.userStats.incScore(increment);
    }

    /**
     * This method decrements the current score by the value
     * specified by the decrement parameter.
     * @param decrement an integer value which is the decrement value.
     */
    public final void decScore(final Integer decrement) {
        this.userStats.decScore(decrement);
    }
}
