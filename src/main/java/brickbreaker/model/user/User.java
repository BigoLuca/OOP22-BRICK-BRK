package brickbreaker.model.user;

import java.util.HashMap;

public class User {

    private String userName;
    private HashMap<String, Integer> scores;

    public User(final String userToSet) {
        this.userName = userToSet;
        this.scores = new HashMap<>();
    }

    public String getName() {
        return this.userName;
    }

    public void addScores(final String levelName, final Integer scoreToSet) {
        this.scores.put(levelName, scoreToSet);
    }

    public HashMap<String, Integer> getScores() {
        return this.scores;
    }

    public Integer getScoresByLevel(final String levelName) {
        return this.scores.get(levelName);
    }
}
