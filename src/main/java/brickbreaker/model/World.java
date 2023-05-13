package brickbreaker.model;

import java.util.List;

import brickbreaker.controllers.state.event.WorldEventListener;
import brickbreaker.model.gameObjects.Ball;
import brickbreaker.model.gameObjects.Bar;
import brickbreaker.model.gameObjects.Brick;
import brickbreaker.model.gameObjects.bounding.RectBoundingBox;

/**
 * Interface to model the Game World. It defines the Object tha belongs to it: 
 * Balls, Bar, Bricks, PowerUps and the main Bounding Box on the edge of the World.
 * It also defines the Object movement and their collision.
 * 
 * @author Bighini Luca
 * @author Agostinelli Francesco
 */
public interface World {

    /**
     * Set the World Object collision listener.
     * @param listener
     */
    void setEventListener(WorldEventListener listener);

    /**
     * @param ball
     */
    void addBall(Ball ball);

    /**
     * @param ball
     */
    void removeBall(Ball ball);

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
     * Remove the brick hitted and create a power Up in its place.
     * @param brick
     */
    void removeBrick(Brick brick);

    /**
     * @return the list of live bricks
     */
    List<Brick> getBricks();

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
}
