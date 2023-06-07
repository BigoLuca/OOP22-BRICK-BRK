package brickbreaker.model.world.gameObjects.collision.powerUpApplicator;

import brickbreaker.model.world.World;

public class ScoreApplicator implements PowerUpApplicator {
    
    private final Integer INC = 8;
    private final Integer DEC = 5;
    private boolean bonus;

    /**
     * Score applicator constructor.
     * @param bonusToSet if increase or decrease the score
     */
    public ScoreApplicator(final boolean bonusToSet) {
        this.bonus = bonusToSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyPowerUp(final World world) {
        if (bonus) {
            world.incScore(INC);
        } else {
            world.decScore(DEC);
        }
    }
}
