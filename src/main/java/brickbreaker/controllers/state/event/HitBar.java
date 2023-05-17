package brickbreaker.controllers.state.event;

import brickbreaker.common.V2d;
import brickbreaker.model.gameObjects.Ball;
import brickbreaker.model.gameObjects.Bar;
import brickbreaker.model.state.GameState;

/**
 * Class for update Bar after collision detection.
 * Implements the {@link HitObjects} interface.
 * 
 * @author Bighini Luca
 */
public class HitBar implements HitObjects {

    private Ball ball;

    /**
     * HitBar constructor.
     * @param ball
     */
    public HitBar(final Ball ball) {
        this.ball = ball;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(final GameState currentGame) {
        Bar bar = currentGame.getWorld().getBar();

        if (bar.getBBox().getULCorner().getY() > ball.getPosition().getY()) {
            ball.flipVelOnY();
            ball.setSpeed(new V2d(bar.getPosition().orizDist(ball.getPosition()), ball.getSpeed().getY()));
        }
    }

}
