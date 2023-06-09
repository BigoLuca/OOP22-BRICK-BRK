package brickbreaker.controllers;

import java.util.List;
import java.util.stream.Collectors;

import brickbreaker.ResourceLoader;
import brickbreaker.model.user.User;

public class UserController {

    private static final int MAX_PLAYER = 5;

    public UserController() {
    }

    public List<String> getUsersName() {
        return ResourceLoader.getInstance().getUsers().stream().map(User::getName).collect(Collectors.toList());
    }

    public User getUser(final String username) {
        return ResourceLoader.getInstance().getUsers().stream().filter(user -> user.getName().equals(username))
                .findFirst().get();
    }

    public void addUser(final User newUser) {
        ResourceLoader.getInstance().addUser(newUser);
    }

    public void removeUser(final String username) {
        ResourceLoader.getInstance().removeUser(username);
    }

    public boolean isMaxUser() {
        return ResourceLoader.getInstance().getUsers().size() < UserController.MAX_PLAYER;
    }
}
