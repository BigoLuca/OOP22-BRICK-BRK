package brickbreaker.model.user;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String userName;
    private Map<Integer, Integer> scores;
    private Integer levelReached;

    public User(final String nameToSet) {
        this.userName = nameToSet;
        this.scores = new HashMap<>();
        this.levelReached = 0;
    }

    public User(final String nameToSet, final Map<Integer, Integer> map, final Integer level) {
        this.userName = nameToSet;
        this.scores = map;
        this.levelReached = level;
    }

    public String getName() {
        return this.userName;
    }

    public void addScores(final Integer levelName, final Integer scoreToSet) {
        this.scores.put(levelName, scoreToSet);
    }

    public Map<Integer, Integer> getScores() {
        return this.scores;
    }

    public Integer getScoresByLevel(final Integer level) {
        return this.scores.get(level);
    }

    public Integer getLevelReached() {
        return this.levelReached;
    }

    public void incLevelReached() {
        this.levelReached++;
    }

    public String toString() {
        return "UserName: " + userName + ", scores: " + scores.toString() + ", levelReached: " + levelReached;
    }
}
