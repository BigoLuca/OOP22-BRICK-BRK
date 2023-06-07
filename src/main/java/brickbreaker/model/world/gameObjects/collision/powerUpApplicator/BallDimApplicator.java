package brickbreaker.model.world.gameObjects.collision.powerUpApplicator;

import brickbreaker.model.world.World;

public class BallDimApplicator implements PowerUpApplicator {
    
    private final Double DELTA = 0.13;
    private boolean bonus;

    /**
     * Ball Speed constructor.
     * @param bonusToSet if increase or decrease the Ball speed
     */
    public BallDimApplicator(final boolean bonusToSet) {
        this.bonus = bonusToSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyPowerUp(final World gameWorld) {
        Double d = bonus ? DELTA : -DELTA;
        gameWorld.getBalls().stream().forEach(b -> b.setRadius(b.getRadius() + d));
    }
}
