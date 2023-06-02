package brickbreaker.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import brickbreaker.ResourceLoader;
import brickbreaker.model.user.User;

public class UserController {
    
    private List<User> users;

    public UserController() {
        users = new ArrayList<>();
    }

    public List<String> getUsersName() {
        return users.stream().map(User::getName).collect(Collectors.toList());
    }

    public User getUser(final String username) {
        return users.stream().filter(user -> user.getName().equals(username)).findFirst().get();
    }

    public void addUser(final String username) {
        ResourceLoader.getInstance().addUser(username);
        this.users = ResourceLoader.getInstance().getUsers();
    }

    public void removeUser(final String username) {
        ResourceLoader.getInstance().removeUser(username);
        this.users = ResourceLoader.getInstance().getUsers();
    }
}
