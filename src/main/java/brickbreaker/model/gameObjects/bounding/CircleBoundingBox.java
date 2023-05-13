package brickbreaker.model.gameObjects.bounding;

import brickbreaker.common.P2d;

/**
 * Class to model a circle shape.
 * Implements the {@link BoundingBox} interface.
 * 
 * @author Bighini Luca
 */
public class CircleBoundingBox implements BoundingBox {

    private P2d pos;
    private Double radius;

    /**
     * CircleBoundingBox constructor.
     * @param pos
     * @param radius
     */
    public CircleBoundingBox(final P2d pos, final Double radius) {
        this.pos = pos;
        this.radius = radius;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public P2d getP2d() {
        return this.pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setP2d(final P2d pos) {
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
