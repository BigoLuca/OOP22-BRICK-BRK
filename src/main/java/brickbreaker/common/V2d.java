package brickbreaker.common;

/**
 * 2-dimensional vector.
 */
public class V2d {

    private Double x;
    private Double y;

    /**
     * P2d constructor.
     * @param x
     * @param y
     */
    public V2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x vector component
     */
    public Double getX() {
        return this.x;
    }

    /**
     * @return the y vector component
     */
    public Double getY() {
        return this.y;
    }

    /**
     * Method to sum the acceleration to the velocity, increasing the velocity vector.
     * @param v
     * @return a new vector
     */
    public V2d sum(final V2d v) {
        return new V2d(x + v.x, y + v.y);
    }

    /**
     * Method to multiply a factor by velocity.
     * @param fact
     * @return a new vector
     */
    public V2d mul(final double fact) {
        return new V2d(x * fact, y * fact);
    }
}
