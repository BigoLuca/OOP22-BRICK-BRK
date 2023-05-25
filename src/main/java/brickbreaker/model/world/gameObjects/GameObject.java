package brickbreaker.model.world.gameObjects;

import brickbreaker.common.TypeObj;
import brickbreaker.common.Vector2D;

/**
 * An interface to model the Game Objects. It implements the common methods
 * for each game object: type, life, position, speed and bounding box.
 * 
 * @param <T> the type of bounding box
 */
public interface GameObject<T> {

    /**
     * @return the object's lives
     */
    int getLife();

    /**
     * decrease lives by 1.
     */
    void decLife();

    /**
     * increase lives by 1.
     */
    void incLife();

    /**
     * @return the object's type
     */
    TypeObj getType();

    /**
     * @return the object's position
     */
    Vector2D getPosition();

    /**
     * @param newPosition
     */
    void setPosition(Vector2D newPosition);

    /**
     * @return the object's speed
     */
    Vector2D getSpeed();

    /**
     * @param speed
     */
    void setSpeed(Vector2D speed);

    /**
     * @return the object's bounding box
     */
    T getBBox();
}
