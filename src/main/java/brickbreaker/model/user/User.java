package brickbreaker.model.user;

import brickbreaker.ResourceLoader;

/**
 * Class representing a user.
 */
public class User {

    private String userName;

    /**
     * User constructor.
     * 
     * @param nameToSet the name of the user
     */
    public User(final String nameToSet) {
        this.userName = nameToSet;
    }

    /**
     * Method to get the name of the user.
     * 
     * @return the name of the user
     */
    public String getName() {
        return this.userName;
    }

    /**
     * Method to get the level reached by the user.
     * 
     * @return the level reached
     */
    public Integer getLevelReached() {
        return ResourceLoader.getInstance().getLevelReached(this.userName);
    }

    /**
     * Method to increment the level reached by the user.
     */
    public void incLevelReached() {
        ResourceLoader.getInstance().incLevelReached(this.userName);
    }
}
