package brickbreaker.model.rank;

import java.util.Map;

/**
 * Interface for work with rank.
 */
public interface Rank {

    /**
     * Method to get the current rank.
     */
    Map<String, Integer> getRank();


    void setRank(Map<String, Integer> r);
    
    /**
     * Method to add a new player stats to the rank.
     * @param playerName
     * @param newScore
     */
    boolean addUser(String playerName, Integer newScore);
}
