package brickbreaker.world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brickbreaker.common.TypePower;
import brickbreaker.common.Vector2D;
import brickbreaker.model.world.gameObjects.Ball;
import brickbreaker.model.world.gameObjects.Bar;
import brickbreaker.model.world.gameObjects.Brick;
import brickbreaker.model.world.gameObjects.PowerUp;
import brickbreaker.model.world.gameObjects.collision.WorldEvent;

public class CollisionTest {

    private Brick brick;
    private Ball ball;
    private Bar bar;
    private PowerUp powerUp;
    private WorldEvent event;

    
    @BeforeEach
    void setUp() {
        brick = new Brick(new Vector2D(2, 2), 3);
        ball = new Ball(new Vector2D(0, 0), new Vector2D(1, 1));
        bar = new Bar(new Vector2D(5,5), 3);
        powerUp = new PowerUp(new Vector2D(0, 0), TypePower.NULL);
        event = new WorldEvent();
    }

    @Test
    void testCollisionWithBrick() {
        ball.setPosition(new Vector2D(2, 2.7));
        assertTrue(brick.getBBox().isCollidingWith(ball.getBBox()));
        ball.setPosition(new Vector2D(3.1, 1.6));
        assertTrue(brick.getBBox().isCollidingWith(ball.getBBox()));
        event.process(ball, brick);
        assertEquals(ball.getSpeed().getX(), -1);
        assertEquals(ball.getSpeed().getY(), 1);

    }

    @Test
    void testCollisionWithBar() {
        ball.setPosition(new Vector2D(6, 4.5));
        assertTrue(bar.getBBox().isCollidingWith(ball.getBBox()));
        event.process(ball, bar);
        assertEquals(ball.getSpeed().getX(), 1);
        assertEquals(ball.getSpeed().getY(), -1);
        ball.setPosition(new Vector2D(0, 0));
        assertFalse(bar.getBBox().isCollidingWith(ball.getBBox()));
    }

    @Test
    void testCollisionWithPowerUp() {
        powerUp.setPosition(new Vector2D(6, 4.4));
        assertTrue(powerUp.getBBox().isCollidingWith(bar.getBBox()));
        powerUp.setPosition(new Vector2D(5, 3));
        assertFalse(powerUp.getBBox().isCollidingWith(bar.getBBox()));
    }
}
