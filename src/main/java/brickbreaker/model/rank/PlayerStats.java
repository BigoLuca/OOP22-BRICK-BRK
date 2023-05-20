package brickbreaker.model.rank;

/**
 * Class representing a single player rank.
 * To relete player with rank.
 */
public class PlayerStats {

    private String name;
    private Integer score;

    /**
     * PlayerStats constructor.
     * @param nameToSet
     * @param scoreToSet
     */
    public PlayerStats(final String nameToSet, final Integer scoreToSet) {
        this.name = nameToSet;
        this.score = scoreToSet;
    }

    /**
     * @return the user name
     */
    public String getUser() {
        return this.name;
    }

    /**
     * Method to change the user name.
     * @param userName
     */
    public void setUser(final String userName) {
        this.name = userName;
    }

    /**
     * @return the user score
     */
    public Integer getScore() {
        return this.score;
    }

    /**
     * Method to change the score.
     * @param score
     */
    public void setScore(final Integer score) {
        this.score = score;
    }

    /**
     * Method to increase the score.
     * @param increment
     */
    public void incScore(final Integer increment) {
        this.score += increment;
    }

    /**
     * Method to decreese the score.
     * @param decrement
     */
    public void decScore(final Integer decrement) {
        this.score -= decrement;
    }
}
