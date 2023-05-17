package brickbreaker.model.factory;

import brickbreaker.model.gameObjects.power.TypePower;
import brickbreaker.model.gameObjects.power.applicator.BallSpeedApplicator;
import brickbreaker.model.gameObjects.power.applicator.BarLengthApplicator;
import brickbreaker.model.gameObjects.power.applicator.PowerUpApplicator;

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
    public PowerUpApplicator createApplicator(final TypePower type) {
        if (type == TypePower.FASTBALL || type == TypePower.SLOWBALL) {
            return new BallSpeedApplicator(type == TypePower.FASTBALL);
        } else if (type == TypePower.LONGBAR || type == TypePower.SHORTBAR) {
            return new BarLengthApplicator(type == TypePower.LONGBAR);
        } else {
            //TODO: Remove the last else statement, it's only a temporary placeholder for clarity purposes.
            return null;
        }
    }
}
