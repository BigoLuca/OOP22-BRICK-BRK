package brickbreaker.model.world.gameObjects;

import brickbreaker.common.P2d;
import brickbreaker.common.TypeObj;
import brickbreaker.common.V2d;
import brickbreaker.model.world.gameObjects.bounding.CircleBoundingBox;

/**
 * Class to model the game object Ball.
 * Extends {@link GameObjectImpl}.
 * 
 * @author Vrabii Alexandru
 */
public class Ball extends GameObjectImpl<CircleBoundingBox> {

    private static final Double RADIUS = 1.0;

    /**
     * Ball constructor.
     * @param center
     * @param vel
     */
    public Ball(final P2d center, final V2d vel) {
        super(1, vel, TypeObj.BALL, new CircleBoundingBox(center, RADIUS));
    }

    /**
     * @return the radius of the Ball
     */
    public Double getRadius() {
        return this.getBBox().getRad();
    }

    /**
     * Method to set the radius of the Ball.
     * @param radius
     */
    public void setRadius(final Double radius) {
        this.getBBox().setRadius(radius);
    }

    /**
     * Method to invert the speed of the ball vertically.
     */
    public void flipVelOnY() {
        this.setSpeed(new V2d(this.getSpeed().getX(), -this.getSpeed().getY()));
    }

    /**
     * Method to invert the speed of the ball horizontally.
     */
    public void flipVelOnX() {
        this.setSpeed(new V2d(-this.getSpeed().getX(), this.getSpeed().getY()));
    }
}
