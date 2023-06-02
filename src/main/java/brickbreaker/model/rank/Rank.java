package brickbreaker.model.rank;

/**
 * Interface for work with rank.
 */
public interface Rank {

    /**
     * Method to add a new player stats to the rank.
     * @param playerName
     * @param newScore
     */
    void addRank(String playerName, Integer newScore);
}
