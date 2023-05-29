package brickbreaker.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import brickbreaker.ResourceLoader;
import brickbreaker.model.user.User;

public class UserController {
    
    private List<User> users;

    public List<String> getUsersName() {
        return users.stream().map(User::getName).collect(Collectors.toList());
    }

    public Map<String, Integer> getUsersScoreLevel(final Integer level) {
        return users.stream().collect(Collectors.toMap(User::getName, user -> user.getScoresByLevel(level)));
    }

    public User getUser(final String username) {
        this.users = ResourceLoader.getInstance().getUsers();
        return users.stream().filter(user -> user.getName().equals(username)).findFirst().get();
    }

    public void addUser(final String username) {
        ResourceLoader.getInstance().addUser(new User(username));
        this.users = ResourceLoader.getInstance().getUsers();
    }

    public void removeUser(final String username) {
        ResourceLoader.getInstance().removeUser(username);
        this.users = ResourceLoader.getInstance().getUsers();
    }
}
