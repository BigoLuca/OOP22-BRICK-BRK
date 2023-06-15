package brickbreaker.model.rank;

import java.util.Map;

/**
 * Interface for work with rank.
 */
public interface Rank {

    /**
     * Method to get the current rank.
     * 
     * @return the current rank
     */
    Map<String, Integer> getRank();

    /**
     * Method to set the current rank.
     * 
     * @param r
     */
    void setRank(Map<String, Integer> r);

    /**
     * Method to add a new player stats to the rank.
     * 
     * @param playerName
     * @param newScore
     * @return after the add, if the player is in the rank
     */
    boolean addUser(String playerName, Integer newScore);
}
