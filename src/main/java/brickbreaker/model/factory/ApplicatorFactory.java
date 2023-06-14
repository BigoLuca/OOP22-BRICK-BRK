package brickbreaker.model.factory;

import brickbreaker.common.TypePower;
import brickbreaker.model.world.gameObjects.collision.powerUpApplicator.*;

/**
 * Class to apply powerUp.
 */
public class ApplicatorFactory {

    /**
     * Applicator factory constructor.
     */
    public ApplicatorFactory() { }

    /**
     * Method to apply powerUp that collides with bar. Create a specific class but return the interface.
     * @param power
     * @param type
     * @return a PowerUpApplicator
     */
    public PowerUpApplicator createApplicator(final TypePower power, final boolean type) {

        switch (power) {
            case FASTBALL:
            case SLOWBALL:
                return new BallSpeedApplicator(type);
            case SMALLBALL:
            case BIGBALL:
                return new BallDimApplicator(type);
            case MULTIBALL:
                return new MultiBallApplicator();
            case LONGBAR:
            case SHORTBAR:
                return new BarLengthApplicator(type);
            case INDBRICK:
                return new DestructibleBrickApplicator(type);
            case INC_50:
                return new ScoreApplicator(50);
            case INC_100:
            return new ScoreApplicator(100);
            case INC_250:
                return new ScoreApplicator(250);
            case INC_500:
            return new ScoreApplicator(500);
            case DEC_50:
            return new ScoreApplicator(-50);
            case DEC_100:
            return new ScoreApplicator(-100);
            case LIFE_INC:
                return new LifeIncApplicator();
            default:
                return new NullApplicator();
        }
    }
}
