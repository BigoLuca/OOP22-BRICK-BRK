package brickbreaker.model.rank;

import java.util.List;

/**
 * Interface for work with rank
 */
public interface Rank {

    /**
     * Method to get the current rank.
     * @return a list of players stats
     */
    List<PlayerStats> getRank();

    /**
     * @param playerName
     * @return the score of player name passed
     */
    Integer getPlayerScore(final String playerName);

    /**
     * Method to add a new players stats to the rank.
     * @param newStats
     * @return true if correct insert and write on file, false otherwise
     */
    void addPlayer(final PlayerStats newStats);
}
