package brickbreaker.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brickbreaker.common.State;
import brickbreaker.common.Vector2D;
import brickbreaker.model.Level;
import brickbreaker.model.factory.WorldFactory;

public class StateTest {

    Level level;
    
    @BeforeEach
    void setUp() {
        level = new Level(1, WorldFactory.getInstance().getWorld("level01.txt", 30));
    }

    @Test
    void testWin() {
        assertEquals(level.getState(), State.WAIT);
        level.getWorld().getBricks().clear();
        assertEquals(level.getState(), State.WIN);
    }

    @Test
    void testLost() {
        assertEquals(level.getState(), State.WAIT);
        assertEquals(level.getWorld().getBar().getLife(), 1);
        level.getWorld().getBar().decLife();
        assertEquals(level.getState(), State.LOST);
    }

    @Test
    void testWait() {
        assertEquals(level.getState(), State.WAIT);
        level.setState(State.PLAYING);
        assertEquals(level.getState(), State.PLAYING);
        assertEquals(level.getWorld().getBar().getLife(), 1);
        level.getWorld().getBar().incLife();
        assertEquals(level.getWorld().getBar().getLife(), 2);
        level.getWorld().getBalls().get(0).setPosition(new Vector2D(20, 30.2));
        level.getWorld().checkCollision();
        assertEquals(level.getWorld().getBalls().size(), 0);
        assertEquals(level.getState(), State.WAIT);
    }
}
