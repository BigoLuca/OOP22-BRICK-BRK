package brickbreaker.model.factory;

import brickbreaker.common.TypePower;
import brickbreaker.model.world.gameObjects.collision.powerUpApplicator.BallSpeedApplicator;
import brickbreaker.model.world.gameObjects.collision.powerUpApplicator.BarLengthApplicator;
import brickbreaker.model.world.gameObjects.collision.powerUpApplicator.NullApplicator;
import brickbreaker.model.world.gameObjects.collision.powerUpApplicator.PowerUpApplicator;

/**
 * Class to apply powerUp.
 */
public class ApplicatorFactory {

    private static ApplicatorFactory instance;

    /**
     * @return the instance of ApplicatorFactory if it not exists yet.
     */
    public static ApplicatorFactory getInstance() {
        if (instance == null) {
            instance = new ApplicatorFactory();
        }

        return instance;
    }

    /**
     * Method to apply powerUp that collides with bar. Create a specific class but return the interface.
     * @param type
     * @return a PowerUpApplicator
     */
    public PowerUpApplicator createApplicator(final TypePower power, final boolean type) {
        
        switch (power) {
            case FASTBALL:
            case SLOWBALL:
                return new BallSpeedApplicator(type);
            case LONGBAR:
            case SHORTBAR:
                return new BarLengthApplicator(type);
            default:
                return new NullApplicator();
        }
    }
}
