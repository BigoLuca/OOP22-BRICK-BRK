package brickbreaker.model.world.gameObjects.collision.powerUpApplicator;

import brickbreaker.common.Vector2D;
import brickbreaker.model.world.World;

/**
 * Class to apply speed powerUp to Ball.
 * Implements the {@link PowerUpApplicator} interface.
 */
public final class BallSpeedApplicator implements PowerUpApplicator {

    private final Double DELTA = 2.0;
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
    public void applyPowerUp(final World world) {
        Vector2D acceleration = bonus ? new Vector2D(DELTA, DELTA) : new Vector2D(-DELTA, -DELTA);
        //TODO not alwais: una pallisa che si muove verso il basso ha vettore negativo e togliendo la aumenti
        world.getBalls().stream().forEach(b -> b.setSpeed(b.getSpeed().sum(acceleration)));
    }
}
