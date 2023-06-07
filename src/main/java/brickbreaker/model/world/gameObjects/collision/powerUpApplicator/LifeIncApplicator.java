package brickbreaker.model.world.gameObjects.collision.powerUpApplicator;

import brickbreaker.model.world.World;

/**
 * Class to apply life increese powerUp to Bar.
 * Implements the {@link PowerUpApplicator} interface.
 */
public class LifeIncApplicator implements PowerUpApplicator {

    public LifeIncApplicator() {}

    @Override
    public void applyPowerUp(World world) {
        world.getBar().incLife();
    }
    
}
