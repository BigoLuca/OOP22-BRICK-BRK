package brickbreaker.model.rank;

import java.util.Map;

/**
 * Interface for work with rank.
 */
public interface Rank {

    /**
     * Method to get the current rank.
     * @return a map of user names and their ranks
     */
    Map<String, Integer> getRank();

    /**
     * @return the file name of the rank
     */
    String getFileName();

    /**
     * @param playerName
     * @return the score of player name passed
     */
    Integer getPlayerScore(String playerName);

    /**
     * Method to add a new player stats to the rank.
     * @param playerName
     * @param newScore
     */
    void addRank(String playerName, Integer newScore);
}
