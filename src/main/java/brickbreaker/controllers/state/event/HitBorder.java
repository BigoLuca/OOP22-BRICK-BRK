package brickbreaker.controllers.state.event;

import brickbreaker.common.P2d;
import brickbreaker.model.gameObjects.Ball;
import brickbreaker.model.state.GameState;
import brickbreaker.model.world.World;
import brickbreaker.model.world.WorldImpl.SideCollision;

/**
 * Class for update Ball after collision detection.
 * Implements the {@link HitObjects} interface.
 * 
 * @author Bighini Luca
 */
public class HitBorder implements HitObjects {

    private Ball ball;
    private SideCollision side;
    private Double newPos;

    /**
     * HitBorder constructor.
     * @param ball
     * @param side
     * @param pos
     */
    public HitBorder(final Ball ball, final SideCollision side, final Double pos) {
        this.ball = ball;
        this.side = side;
        this.newPos = pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(final GameState currentGame) {

        switch (this.side) {
            case TOP:
                ball.setPosition(new P2d(ball.getPosition().getX(), newPos + ball.getRadius()));
                ball.flipVelOnY();
                break;
            case BOTTOM:
                World w = currentGame.getWorld();
                w.removeBall(ball);
                if (w.getBalls().size() <= 0) {
                    w.getBar().decLife();
                }
                break;
            case LEFT:
                ball.setPosition(new P2d(newPos + ball.getRadius(), ball.getPosition().getY()));
                ball.flipVelOnX();
                break;
            case RIGHT:
                ball.setPosition(new P2d(newPos - ball.getRadius(), ball.getPosition().getY()));
                ball.flipVelOnX();
                break;
            default:
                break;
        }
    }

}
