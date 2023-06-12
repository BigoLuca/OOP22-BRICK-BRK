package brickbreaker.model.world;

import java.util.List;

import brickbreaker.model.world.gameObjects.Ball;
import brickbreaker.model.world.gameObjects.Bar;
import brickbreaker.model.world.gameObjects.Brick;
import brickbreaker.model.world.gameObjects.PowerUp;
import brickbreaker.model.world.gameObjects.bounding.RectBoundingBox;

/**
 * Interface to model the Game World. It defines the Object tha belongs to it: 
 * Balls, Bar, Bricks, PowerUps and the main Bounding Box on the edge of the World.
 * It also defines the Object movement and their collision.
 */
public interface World {

    /**
     * @param ball
     */
    void addBall(Ball ball);

    /**
     * @return the list of ball in play
     */
    List<Ball> getBalls();

    /**
     * @return the bar
     */
    Bar getBar();

    /**
     * @param bar 
     */
    void setBar(Bar bar);

    /**
     * @param bricks
     */
    void addBricks(List<Brick> bricks);

    /**
     * @return the list of live bricks
     */
    List<Brick> getBricks();

    /**
     * @return the list of falling powerUps
     */
    List<PowerUp> getPowerUp();

    /**
     * @return the main Bounding Box
     */
    RectBoundingBox getMainBBox();

    /**
     * Move object in the world at each frame.
     * @param elapsed
     */
    void updateGame(int elapsed);

    /**
     * Comunicate to the Wolrd listener if a collision occurs between two objects.
     */
    void checkCollision();

    /**
     * This method gets the current points scored by the user.
     * @return An integer value.
     */
    Integer getScore();

    /**
     * This method add the value to the score.
     * @param val an integer value
     */
    void addToScore(Integer val);

    /**
     * This method change states of indestructible brick.
     * @param b
     */
    void setDestructibleBrick(boolean b);

}
