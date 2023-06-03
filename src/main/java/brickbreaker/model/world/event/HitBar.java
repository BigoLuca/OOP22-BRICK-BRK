package brickbreaker.model.world.event;

import java.util.function.Function;

import brickbreaker.common.Vector2D;
import brickbreaker.model.Level;
import brickbreaker.model.world.gameObjects.Ball;
import brickbreaker.model.world.gameObjects.Bar;

/**
 * Class for update Bar after collision detection.
 * Implements the {@link HitObjects} interface.
 */
public class HitBar implements HitObjects {

    private Integer ball;

    /**
     * HitBar constructor.
     * @param ball
     */
    public HitBar(final Integer ballIdx) {
        this.ball = ballIdx;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(final Level level) {
        Bar bar = level.getWorld().getBar();
        Function<Integer, Ball> f = ball -> level.getWorld().getBalls().get(ball);

        if (bar.getBBox().getULCorner().getY() > f.apply(ball).getPosition().getY()) {
            f.apply(ball).flipVelOnY();
        }
    }

}
