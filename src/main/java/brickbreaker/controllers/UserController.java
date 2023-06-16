package brickbreaker.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.reflect.TypeToken;

import brickbreaker.ResourceLoader;
import brickbreaker.common.LoadJson;
import brickbreaker.model.user.User;

/**
 * The controller of the user.
 */
public class UserController {

    private static final int MAX_PLAYER = 5;
    private final List<User> users;

    /**
     * Empy UserController constructor.
     */
    public UserController() {
        this.users = LoadJson.load(new TypeToken<List<User>>(){}.getType(), "users/user.json");
    }

    /**
     * Methdo to get all the name of the user saved.
     * @return the List of name
     */
    public List<String> getUsersName() {
        return this.users.stream().map(User::getName).collect(Collectors.toList());
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
        this.users.add(newUser);
        LoadJson.write(newUser, "users/user.json");
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
