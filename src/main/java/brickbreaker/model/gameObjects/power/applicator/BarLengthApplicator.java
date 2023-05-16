package brickbreaker.model.gameObjects.power.applicator;

import brickbreaker.model.World;

/**
 * Class to apply lenght powerUp to Bar.
 * Implements the {@link PowerUpApplicator} interface.
 * 
 * @author Bighini Luca
 * @author Agostinelli Francesco
 */
public class BarLengthApplicator implements PowerUpApplicator {

    private static final Double DELTA_BAR_LENGTH = 3.0;
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
