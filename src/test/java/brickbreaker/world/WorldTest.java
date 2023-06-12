package brickbreaker.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brickbreaker.common.TypePower;
import brickbreaker.common.Vector2D;
import brickbreaker.model.factory.ApplicatorFactory;
import brickbreaker.model.factory.WorldFactory;
import brickbreaker.model.world.World;
import brickbreaker.model.world.gameObjects.Ball;
import brickbreaker.model.world.gameObjects.PowerUp;

public class WorldTest {
    
    private World world;
    private Ball ball;

    @BeforeEach
    void setUp() {
        world = WorldFactory.getInstance().getWorld(1);
        ball = new Ball(new Vector2D(0, 0), new Vector2D(1, 1));
    }

    @Test
    void testIncrementScore() {
        ball.setPosition(new Vector2D(100, 120));
        world.addBall(ball);
        int sizeBricks = world.getBricks().size();
        world.checkCollision();
        assertEquals(100, world.getScore());
        assertEquals(sizeBricks - 1, world.getBricks().size());
    }

    @Test
    void testLossBall() {
        world.addBall(ball);
        assertEquals(2, world.getBalls().size());
        world.getBalls().get(0).setPosition(new Vector2D(WorldFactory.BOUNDARIES_SIZE / 2, WorldFactory.BOUNDARIES_SIZE - 1));
        world.checkCollision();
        assertEquals(1, world.getBalls().size());
    }

    @Test
    void testLossLife() {
        Integer life = world.getBar().getLife();
        world.getBalls().get(0).setPosition(new Vector2D(WorldFactory.BOUNDARIES_SIZE / 2, WorldFactory.BOUNDARIES_SIZE - 1));
        world.checkCollision();
        assertEquals(life - 1, world.getBar().getLife());
    }

    @Test
    void testBigBallPowerUp() {
        assertEquals(Ball.RADIUS, world.getBalls().get(0).getRadius());
        PowerUp p = new PowerUp(new Vector2D(0, 0), TypePower.BIGBALL);
        ApplicatorFactory.getInstance().createApplicator(p.getPowerUp(), true).applyPowerUp(world);
        assertEquals(Ball.RADIUS + (Ball.RADIUS / 3), world.getBalls().get(0).getRadius());
    }
}
