package brickbreaker.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brickbreaker.common.Vector2D;
import brickbreaker.model.factory.WorldFactory;
import brickbreaker.model.world.World;
import brickbreaker.model.world.gameObjects.Ball;

public class WorldTest {
    
    private World world;
    private Ball ball;

    @BeforeEach
    void setUp() {
        world = WorldFactory.getInstance().getWorld("level03.txt", 70);
        ball = new Ball(new Vector2D(0, 0), new Vector2D(1, 1));
    }

    @Test
    void testIncrementScore() {
        ball.setPosition(new Vector2D(1.5, 0.8));
        world.addBall(ball);
        int sizeBricks = world.getBricks().size();
        world.checkCollision();
        assertEquals(5, world.getScore());
        assertEquals(world.getBricks().size(), sizeBricks - 1);
        assertEquals(world.getBalls().get(1).getSpeed().getY(), -1);
    }

    @Test
    void testLossBall() {
        world.addBall(ball);
        assertEquals(world.getBalls().size(), 2);
        world.getBalls().get(0).setPosition(new Vector2D(20, 30.2));
        world.checkCollision();
        assertEquals(world.getBalls().size(), 1);
    }

    @Test
    void testLossLife() {
        world.getBalls().get(0).setPosition(new Vector2D(20, 30.2));
        world.checkCollision();
        assertEquals(world.getBar().getLife(), 0);
    }

}
