package brickbreaker.common;

/**
 * Class representing a two-dimensional vector
 */
public class Vector2D {

    private Double x;
    private Double y;

    /**
     * P2d constructor.
     * @param x
     * @param y
     */
    public Vector2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x coordinate
     */
    public Double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate
     */
    public Double getY() {
        return this.y;
    }

    /**
     * Method to sum the velocity to the position.
     * @param v
     * @return a new position
     */
    public Vector2D sum(final Vector2D v) {
        return new Vector2D(x + v.getX(), y + v.getY());
    }

    /**
     * Method to multiply a factor by velocity.
     * @param value
     * @return a new vector
     */
    public Vector2D mul(final double value) {
        return new Vector2D(value * this.x, value * this.y);
    }

    /**
     * @param xp
     * @return the horizontal distance between two point
     */
    public Double orizDist(final Vector2D xp) {
        return this.x - xp.getX();
    }

    /**
     * @param yp
     * @return the vertical distance between two point
     */
    public Double vertDist(final Vector2D yp) {
        return this.y - yp.getY();
    }
}
