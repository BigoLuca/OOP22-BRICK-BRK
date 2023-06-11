package brickbreaker.model.world.gameObjects.collision.powerUpApplicator;

import brickbreaker.model.world.World;

/**
 * Class to apply speed powerUp to Ball.
 * Implements the {@link PowerUpApplicator} interface.
 */
public final class BallSpeedApplicator implements PowerUpApplicator {

    private final Double DELTA = 3.0;
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
        Double acceleration = bonus ? 1/DELTA : DELTA;
        world.getBalls().stream().forEach(b -> b.setSpeed(b.getSpeed().mul(acceleration)));
    }
}
