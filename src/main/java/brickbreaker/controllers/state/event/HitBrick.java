package brickbreaker.controllers.state.event;

import brickbreaker.common.Vector2D;
import brickbreaker.model.state.GameState;
import brickbreaker.model.world.gameObjects.Ball;
import brickbreaker.model.world.gameObjects.Brick;

/**
 * Class for update Brick after collision detection.
 * Implements the {@link HitObjects} interface.
 * 
 * @author Bighini Luca
 */
public class HitBrick implements HitObjects {

    private Brick hittedBrick;
    private Ball ball;

    /**
     * HitBrick constructor.
     * @param hitted
     * @param ball
     */
    public HitBrick(final Brick hitted, final Ball ball) {
        this.hittedBrick = hitted;
        this.ball = ball;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(final GameState currentGame) {
        this.hittedBrick.decLife();

        if (this.hittedBrick.getLife() <= 0) {
            currentGame.getWorld().removeBrick(this.hittedBrick);
        }

        Vector2D ul = hittedBrick.getBBox().getULCorner();
        Vector2D br = hittedBrick.getBBox().getBRCorner();
        Vector2D bpoint = ball.getPosition();

        if (ul.getY() > bpoint.getY()) {
            Double uly = Math.abs(ul.vertDist(bpoint));
            if ((ul.getX() > bpoint.getX() && uly < Math.abs(ul.orizDist(bpoint)))
                || (br.getX() < bpoint.getX() && uly < Math.abs(br.orizDist(bpoint)))
            ) {
                ball.flipVelOnX();
            } else {
                ball.flipVelOnY();
            }
        } else if (br.getY() < bpoint.getY()) {
            Double bry = Math.abs(br.vertDist(bpoint));
            if ((ul.getX() > bpoint.getX() && bry < Math.abs(ul.orizDist(bpoint)))
                || (br.getX() < bpoint.getX() && bry < Math.abs(br.orizDist(bpoint)))
            ) {
                ball.flipVelOnX();
            } else {
                ball.flipVelOnY();
            }
        } else {
            ball.flipVelOnX();
        }
    }

}
