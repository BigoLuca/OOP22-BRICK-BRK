package brickbreaker.model.world.gameObjects.collision.powerUpApplicator;

import brickbreaker.common.Vector2D;
import brickbreaker.model.world.World;

/**
 * Class to apply speed powerUp to Ball.
 * Implements the {@link PowerUpApplicator} interface.
 */
public final class BallSpeedApplicator implements PowerUpApplicator {

    private final static Double DELTA = 2.0;
    private boolean bonus;

    /**
     * Ball Speed constructor.
     * @param bonusToSet if increase or decrease the Ball speed
     */
    public BallSpeedApplicator(final boolean bonusToSet) {
        this.bonus = bonusToSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyPowerUp(final World gameWorld) {
        Vector2D acceleration = bonus ? new Vector2D(DELTA, DELTA) : new Vector2D(-DELTA, -DELTA);
        gameWorld.getBalls().stream().forEach(b -> b.setSpeed(b.getSpeed().sum(acceleration)));
    }
}
