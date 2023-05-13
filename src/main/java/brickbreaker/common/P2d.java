package brickbreaker.common;

// TODO unify with V2d
/**
 * 2-dimensional point.
 */
public class P2d {

    private Double x;
    private Double y;

    /**
     * P2d constructor.
     * @param x
     * @param y
     */
    public P2d(final double x, final double y) {
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
    public P2d sum(final V2d v) {
        return new P2d(x + v.getX(), y + v.getY());
    }

    /**
     * @param xp
     * @return the horizontal distance between two point
     */
    public Double orizDist(final P2d xp) {
        return this.x - xp.getX();
    }

    /**
     * @param yp
     * @return the vertical distance between two point
     */
    public Double vertDist(final P2d yp) {
        return this.y - yp.getY();
    }

}
