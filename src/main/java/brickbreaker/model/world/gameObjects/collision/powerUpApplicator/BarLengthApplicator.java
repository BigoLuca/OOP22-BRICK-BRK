package brickbreaker.model.world.gameObjects.collision.powerUpApplicator;

import brickbreaker.model.world.World;

/**
 * Class to apply lenght powerUp to Bar.
 * Implements the {@link PowerUpApplicator} interface.
 */
public class BarLengthApplicator implements PowerUpApplicator {

    private final Double DELTA = 1.0;
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
    public void applyPowerUp(final World world) {
        Double delta = bonus ? DELTA : -DELTA;

        Double barWidth = world.getBar().getWidth();
        world.getBar().setWidth(barWidth + delta);
    }
    
}
