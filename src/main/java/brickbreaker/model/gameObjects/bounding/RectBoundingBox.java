package brickbreaker.model.gameObjects.bounding;

import brickbreaker.common.P2d;

/**
 * Class to model a rectangular shape.
 * Implements the {@link BoundingBox} interface.
 * 
 * @author Bighini Luca
 */
public class RectBoundingBox implements BoundingBox {

    private P2d pos;
    private Double width, height;

    /**
     * RectBoundingBox constructor.
     * @param pos
     * @param width
     * @param height
     */
    public RectBoundingBox(final P2d pos, final Double width, final Double height) {
        this.pos = pos;
        this.width = width;
        this.height = height;
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
     * @return the width
     */
    public Double getWidth() {
        return this.width;
    }

    /**
     * @param width
     * Method to set the width
     */
    public void setWidth(final Double width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public Double getHeight() {
        return this.height;
    }

    /**
     * @return the top-left corner
     */
    public P2d getULCorner() {
        return new P2d(pos.getX() - width / 2, pos.getY() - height / 2);
    }

    /**
     * @return the bottom-right corner
     */
    public P2d getBRCorner() {
        return new P2d(pos.getX() + width / 2, pos.getY() + height / 2);
    }

    /**
     * {@inheritDoc}
     * Detect the collision between a rectangle and another shape: rectangles or circles
     */
    @Override
    public boolean isCollidingWith(final BoundingBox obj) {
        if (obj instanceof RectBoundingBox) {

            RectBoundingBox rect = (RectBoundingBox) obj;
            P2d ul = getULCorner();
            P2d br = getBRCorner();
            P2d pul = new P2d(rect.pos.getX() - rect.width / 2, rect.pos.getY() - rect.height / 2);
            P2d pbr = new P2d(rect.pos.getX() + rect.width / 2, rect.pos.getY() + rect.height / 2);

            return (ul.getX() <= pul.getX()
                    && ul.getY() <= pul.getY() 
                    && br.getX() >= pul.getX() 
                    && br.getY() >= pul.getY()
                    ) || (
                    ul.getX() <= pbr.getX() 
                    && ul.getY() <= pbr.getY() 
                    && br.getX() >= pbr.getX() 
                    && br.getY() >= pbr.getY());

        } else if (obj instanceof CircleBoundingBox) {

            CircleBoundingBox circ = (CircleBoundingBox) obj;
            Double circDistX = Math.abs(circ.getP2d().getX() - pos.getX());
            Double circDistY = Math.abs(circ.getP2d().getY() - pos.getY());

            if (circDistX > (width / 2 + circ.getRad()) || circDistY > (height / 2 + circ.getRad())) {
                return false;
            }

            if (circDistX <= (width / 2) || circDistY <= (height / 2)) {
                return true;
            }

            Double dx = circDistX - width / 2;
            Double dy = circDistY - height / 2;

            return ((dx * dx + dy * dy) <= (circ.getRad() * circ.getRad()));

        }
        return false;
    }

}
