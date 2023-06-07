package brickbreaker.model.world.gameObjects.collision.powerUpApplicator;

import brickbreaker.model.world.World;

/**
 * Class to apply lenght powerUp to Bar.
 * Implements the {@link PowerUpApplicator} interface.
 */
public class BarLengthApplicator implements PowerUpApplicator {

    private final Double DELTA_BAR_LENGTH = 1.0;
    private boolean bonus;

    /**
     * Bar length constructor.
     * @param bonusToSet if increase or decrease the Bar length
     */
    public BarLengthApplicator(final boolean bonusToSet) {
        this.bonus = bonusToSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyPowerUp(final World gameWorld) {
        Double delta = bonus ? DELTA_BAR_LENGTH : -DELTA_BAR_LENGTH;

        Double barWidth = gameWorld.getBar().getWidth();
        gameWorld.getBar().setWidth(barWidth + delta);
    }
    
}
