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

    public Integer getScore() {
        return this.score;
    }
}
