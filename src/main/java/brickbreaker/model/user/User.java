package brickbreaker.model.user;

/**
 * Class representing a user.
 */
public class User {

    private String userName;
    private Integer levelReached;

    public User(final String nameToSet, final Integer levelReached) {
        this.userName = nameToSet;
        this.levelReached = levelReached;
    }

    public String getName() {
        return this.userName;
    }

    public Integer getLevelReached() {
        return this.levelReached;
    }

    public void incLevelReached() {
        this.levelReached++;
    }
}
