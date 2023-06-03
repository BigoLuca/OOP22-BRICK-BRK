package brickbreaker.model.world.event;

import brickbreaker.common.Vector2D;
import brickbreaker.model.world.World;
import brickbreaker.model.world.WorldImpl.SideCollision;
import brickbreaker.model.world.gameObjects.Ball;

/**
 * Class for update Ball after collision detection.
 * Implements the {@link HitObjects} interface.
 */
public abstract class HitBorder {

    //@Override
    public void process(final World w, final Ball ball, final SideCollision side, final Double newPos) {

        switch (side) {
            case TOP:
                ball.setPosition(new Vector2D(ball.getPosition().getX(), newPos + ball.getRadius()));
                ball.flipVelOnY();
                break;
            case BOTTOM:
                w.removeBall(ball);
                if (w.getBalls().size() <= 0) {
                    w.getBar().decLife();
                }
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

}
