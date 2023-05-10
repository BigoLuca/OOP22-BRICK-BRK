package brickbreaker.model.gameObjects.bounding;

import brickbreaker.common.P2d;

/**
 * @author Bighini Luca
 * An Interface to model the objects bounding 
 */
public interface BoundingBox {

    /**
     * @return a point 2D
     */
    P2d getP2d();

    /**
     * @param pos
     */
    void setP2d(P2d pos);

    /**
     * @param obj bounding box object
     * @return if the two objects is colliding
     */
    boolean isCollidingWith(BoundingBox obj);

}
