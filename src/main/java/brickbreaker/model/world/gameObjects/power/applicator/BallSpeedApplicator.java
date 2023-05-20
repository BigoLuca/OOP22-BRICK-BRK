package brickbreaker.model.world.gameObjects.power.applicator;

import brickbreaker.common.V2d;
import brickbreaker.model.world.World;

/**
 * Class to apply speed powerUp to Ball.
 * Implements the {@link PowerUpApplicator} interface.
 * 
 * @author Bighini Luca
 * @author Agostinelli Francesco
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
        V2d acceleration = bonus ? new V2d(DELTA, DELTA) : new V2d(-DELTA, -DELTA);
        gameWorld.getBalls().stream().forEach(b -> b.setSpeed(b.getSpeed().sum(acceleration)));
    }
}
