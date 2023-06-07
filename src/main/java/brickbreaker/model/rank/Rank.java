package brickbreaker.model.rank;

import java.util.Map;

/**
 * Interface for work with rank.
 */
public interface Rank {

    /**
     * Method to get the current rank.
     * @param fileName
     * @param level
     * @return a map of user names and their ranks
     */
    Map<String, Integer> getRank(String fileName, Integer level);

    /**
     * Method to add a new player stats to the rank.
     * @param playerName
     * @param newScore
     */
    void addToRank(String playerName, Integer newScore);
}
