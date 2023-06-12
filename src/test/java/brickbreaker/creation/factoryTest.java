package brickbreaker.creation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brickbreaker.common.Difficulty;
import brickbreaker.common.Vector2D;
import brickbreaker.model.factory.GameFactory;
import brickbreaker.model.factory.WorldFactory;
import brickbreaker.model.world.World;
import brickbreaker.model.world.gameObjects.Ball;
import brickbreaker.model.world.gameObjects.Bar;
import brickbreaker.model.world.gameObjects.Brick;

public class factoryTest {
    
    private GameFactory g;
    private WorldFactory w;

    @BeforeEach
    void setUp() {
        g = GameFactory.getInstance();
        w = WorldFactory.getInstance();
    }

    @Test
    void testCreateBall() {
        assertTrue(g.createBall(new Vector2D(0, 0), new Vector2D(0, 0)) instanceof Ball);
    }

    @Test
    void testCreateBricks() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(1);
        list.add(1);
        List<Brick> b = g.createBricks(list, 1, 3);
        assertTrue(b.get(1) instanceof Brick);
        assertEquals(3, b.size());
    }

    @Test
    void testCreateBar() {
        assertTrue(g.createBar(new Vector2D(0, 0)) instanceof Bar);
    }

    @Test
    void testCreateWorld() {
        assertTrue(w.getWorld(1) instanceof World);
        assertTrue(w.getRandomWorld(Difficulty.EASY) instanceof World);
    }
}
