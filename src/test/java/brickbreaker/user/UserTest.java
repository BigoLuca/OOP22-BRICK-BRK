package brickbreaker.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brickbreaker.controllers.UserController;
import brickbreaker.model.user.User;

public class UserTest {

    private UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController();
    }

    @Test
    void testLenghtUser() {
        assertTrue(userController.getUsersName().size() <= 6);
    }

    @Test
    void testAddRemoveUser() {
        int i = userController.getUsersName().size();
        userController.addUser(new User("aaa"));
        assertEquals(userController.getUsersName().size(), i+1);
        userController.removeUser("aaa");
        assertEquals(userController.getUsersName().size(), i);
    }

    @Test
    void testUsereLevelReached() {
        userController.addUser(new User("prova"));
        User u = userController.getUser("prova");
        assertEquals(1, u.getLevelReached());
        userController.removeUser("prova");
    }
}
