package brickbreaker.controllers;

import java.util.List;
import java.util.stream.Collectors;

import brickbreaker.ResourceLoader;
import brickbreaker.model.user.User;

/**
 * The controller of the user.
 */
public class UserController {

    private static final int MAX_PLAYER = 5;

    /**
     * Empy UserController constructor.
     */
    public UserController() { }

    /**
     * Methdo to get all the name of the user saved.
     * @return the List of name
     */
    public List<String> getUsersName() {
        return ResourceLoader.getInstance().getUsers().stream().map(User::getName).collect(Collectors.toList());
    }

    /**
     * Method to get a user passing the name.
     * @param username
     * @return a User
     */
    public User getUser(final String username) {
        return ResourceLoader.getInstance().getUsers().stream().filter(user -> user.getName().equals(username))
                .findFirst().get();
    }

    /**
     * Method to add a new user to json file.
     * @param newUser
     */
    public void addUser(final User newUser) {
        ResourceLoader.getInstance().addUser(newUser);
    }

    /**
     * Method to remove user and his ranks from json file.
     * @param username
     */
    public void removeUser(final String username) {
        ResourceLoader.getInstance().removeUser(username);
    }

    /**
     * Method to chek the number of user.
     * @return true if less than MAX_PLAYER
     */
    public boolean isMaxUser() {
        return ResourceLoader.getInstance().getUsers().size() < MAX_PLAYER;
    }
}
