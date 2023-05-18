package brickbreaker.model.rank;

public class PlayerStats {

    private String name;
    private Integer score;

    public PlayerStats(final String nameToSet, final Integer scoreToSet) {
        this.name = nameToSet;
        this.score = scoreToSet;
    }

    public String getUser() {
        return this.name;
    }

    public void setUser(final String userName) {
        this.name = userName;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(final Integer score) {
        this.score = score;
    }

    public void incScore(final Integer increment) {
        this.score += increment;
    }

    public void decScore(final Integer decrement) {
        this.score -= decrement;
    }
}
