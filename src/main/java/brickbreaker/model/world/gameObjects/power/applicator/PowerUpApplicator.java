package brickbreaker.model.gameObjects.power.applicator;

import brickbreaker.model.world.World;

/**
 * Interface for all powerUp to modify the world.
 * 
 * @author Agostinelli Francesco
 */
public interface PowerUpApplicator {

    /**
     * Method to modify the world objects with active powerUps.
     * @param gameWorld
     */
    void applyPowerUp(World gameWorld);
}
