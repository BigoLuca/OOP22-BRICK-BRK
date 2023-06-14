package brickbreaker.model.world.gameObjects.collision;

import brickbreaker.common.Vector2D;
import brickbreaker.model.world.WorldImpl.SideCollision;
import brickbreaker.model.world.gameObjects.Ball;
import brickbreaker.model.world.gameObjects.Bar;
import brickbreaker.model.world.gameObjects.Brick;

/**
 * Interface to executed the collision events of the world.
 */
public class WorldEvent {


    public final Integer SCALE_SPEED = 12;
    /**
     * Process the collision of the ball with the border side.
     * @param ball
     * @param side
     * @param newPos
     */
    public void process(final Ball ball, final SideCollision side, final Double newPos) {

        switch (side) {
            case TOP:
                ball.setPosition(new Vector2D(ball.getPosition().getX(), newPos + ball.getRadius()));
                ball.flipVelOnY();
                break;
            case LEFT:
                ball.setPosition(new Vector2D(newPos + ball.getRadius(), ball.getPosition().getY()));
                ball.flipVelOnX();
                break;
            case RIGHT:
                ball.setPosition(new Vector2D(newPos - ball.getRadius(), ball.getPosition().getY()));
                ball.flipVelOnX();
                break;
            default:
                break;
        }
    }

    /**
     * Process the ball collision with the bar.
     * @param ball
     * @param bar
     */
    public void process(final Ball ball, final Bar bar) {
        Vector2D oldPos = ball.getPosition();
        ball.setPosition(new Vector2D(oldPos.getX(), oldPos.getY() - bar.getHeight() / 2));
        ball.flipVelOnY();
        Double distX = ball.getPosition().orizDist(bar.getPosition()) / (bar.getWidth() / 2);
        Vector2D oldSpeed = ball.getSpeed();
        if (oldSpeed.getX() > 0) {
            ball.setSpeed(new Vector2D(distX * SCALE_SPEED, oldSpeed.getY()));
        } else {
            ball.setSpeed(new Vector2D(distX * SCALE_SPEED, oldSpeed.getY()));
        }
    }

    /**
     * Process the ball collision with and object [brick, bar].
     * Flip the speed of the ball.
     * @param ball
     * @param brick
     */
    public void process(final Ball ball, final Brick brick) {
        Double distY = ball.getPosition().vertDist(brick.getPosition());
        if (Math.abs(distY) > brick.getBBox().getHeight() / 2) {
            if (distY > 0) {
                ball.getPosition().sum(new Vector2D(0, distY));
            } else {
                ball.getPosition().sum(new Vector2D(0, -distY));
            }
            ball.flipVelOnY();
        } else {
            Double distX = ball.getPosition().orizDist(brick.getPosition());
            if (distX > 0) {
                ball.getPosition().sum(new Vector2D(distX, 0));
            } else {
                ball.getPosition().sum(new Vector2D(-distX, 0));
            }
            ball.flipVelOnX();
        }
    }
}
