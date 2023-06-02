package brickbreaker.model.user;

import brickbreaker.ResourceLoader;

/**
 * Class representing a user.
 */
public class User {

    private String userName;

    public User(final String nameToSet) {
        this.userName = nameToSet;
    }

    public String getName() {
        return this.userName;
    }

    public Integer getLevelReached() {
        return ResourceLoader.getInstance().getLevelReached(this.userName);
    }

    public void incLevelReached() {
        ResourceLoader.getInstance().incLevelReached(this.userName);
    }
}
