package brickbreaker.model.world.gameObjects.bounding;

import brickbreaker.common.Vector2D;

/**
 * Class to model a circle shape.
 * Implements the {@link BoundingBox} interface.
 * 
 * @author Bighini Luca
 */
public class CircleBoundingBox implements BoundingBox {

    private Vector2D pos;
    private Double radius;

    /**
     * CircleBoundingBox constructor.
     * @param pos
     * @param radius
     */
    public CircleBoundingBox(final Vector2D pos, final Double radius) {
        this.pos = pos;
        this.radius = radius;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getP2d() {
        return this.pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setP2d(final Vector2D pos) {
        this.pos = pos;
    }

    /**
     * @return radius of circle shape
     */
    public Double getRad() {
        return this.radius;
    }

    /**
     * @param radius
     * Method to set the radius
     */
    public void setRadius(final Double radius) {
        this.radius = radius;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCollidingWith(final BoundingBox obj) {
        return false;
    }

}
