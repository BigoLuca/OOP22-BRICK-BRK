package brickbreaker.model.gameObjects;

import brickbreaker.common.P2d;
import brickbreaker.common.TypeObj;
import brickbreaker.common.V2d;

/**
 * An interface to model the Game Objects. It implements the common methods
 * for each game object: type, life, position, speed and bounding box.
 * 
 * @author Bighini Luca
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
    P2d getPosition();

    /**
     * @param newPosition
     */
    void setPosition(P2d newPosition);

    /**
     * @return the object's speed
     */
    V2d getSpeed();

    /**
     * @param speed
     */
    void setSpeed(V2d speed);

    /**
     * @return the object's bounding box
     */
    T getBBox();
}
