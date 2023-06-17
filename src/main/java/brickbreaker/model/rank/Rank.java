package brickbreaker.model.rank;

import java.util.Map;

/**
 * Class representing the rank.
 * Implements the {@link Rank} interface.
 */
public class Rank {

    private Integer level;
    private Map<String, Integer> scores;

    /**
     * GameRank constructor.
     * 
     * @param name  the name of the rank
     * @param scores the score of the rank
     */
    public Rank(final String name, final Integer scores) {
        this.scores.put(name, scores);
    }

    /**
     * Method to get the rank.
     * 
     * @return the Map<String, Integer> object representing the rank
     */
    public Map<String, Integer> getRank() {
        return this.scores;
    }

    /**
     * Method to get the level of the rank.
     * 
     * @return the level of the rank
     */
    public Integer getIndex() {
        return this.level;
    }

    /**
     * Method to add a new score to the rank only if better.
     */
    public void addScore(final String name, final Integer score) {
        if (score > this.scores.getOrDefault(name, Integer.MIN_VALUE)) {
            this.scores.put(name, score);
        }
    }
}
