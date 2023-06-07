package brickbreaker.model.world.gameObjects.collision.powerUpApplicator;

import brickbreaker.model.world.World;

public class NullApplicator implements PowerUpApplicator {

    /**
     * Bar length constructor.
     * @param bonusToSet if increase or decrease the Bar length
     */
    public NullApplicator() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyPowerUp(final World gameWorld) {}
}
