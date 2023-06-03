package brickbreaker.model.world.gameObjects.collision;

import brickbreaker.common.Vector2D;
import brickbreaker.model.world.WorldImpl.SideCollision;
import brickbreaker.model.world.gameObjects.Ball;
import brickbreaker.model.world.gameObjects.GameObject;
import brickbreaker.model.world.gameObjects.bounding.RectBoundingBox;

/**
 * Interface to executed the collision events of the world.
 */
public class WorldEvent {

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
     * Process the ball collision with and object [brick, bar].
     * Flip the speed of the ball.
     * @param ball
     * @param obj
     */
    public void process(final Ball ball, final GameObject<RectBoundingBox> obj) {
        
        switch (side(ball.getPosition(), obj.getBBox())) {
            case LEFT:
            case RIGHT:
                ball.flipVelOnX();
                break;
            case TOP:
            case BOTTOM:
                ball.flipVelOnY();
                break;
            default:
                break;
        }
    }

    private SideCollision side(final Vector2D ballPos, RectBoundingBox bb) {
        Vector2D ul = bb.getULCorner();
        Vector2D br = bb.getBRCorner();

        if (ul.getY() > ballPos.getY()) {
            Double uly = Math.abs(ul.vertDist(ballPos));
            if ((ul.getX() > ballPos.getX() && uly < Math.abs(ul.orizDist(ballPos)))
                || (br.getX() < ballPos.getX() && uly < Math.abs(br.orizDist(ballPos)))
            ) {
                return SideCollision.LEFT;
            } else {
                return SideCollision.TOP;
            }
        } else if (br.getY() < ballPos.getY()) {
            Double bry = Math.abs(br.vertDist(ballPos));
            if ((ul.getX() > ballPos.getX() && bry < Math.abs(ul.orizDist(ballPos)))
                || (br.getX() < ballPos.getX() && bry < Math.abs(br.orizDist(ballPos)))
            ) {
                return SideCollision.RIGHT;
            } else {
                return SideCollision.BOTTOM;
            }
        } else {
            return SideCollision.LEFT;
        }
    }
}
