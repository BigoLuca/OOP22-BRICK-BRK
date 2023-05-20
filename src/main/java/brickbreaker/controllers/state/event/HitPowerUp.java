package brickbreaker.controllers.state.event;

import brickbreaker.model.state.GameState;
import brickbreaker.model.world.gameObjects.power.PowerUp;
import brickbreaker.model.world.gameObjects.power.applicator.PowerUpApplicator;

/**
 * Class for update PowerUp after collision detection.
 * Implements the {@link HitObjects} interface.
 * 
 * @author Bighini Luca
 */
public class HitPowerUp implements HitObjects {

    private final PowerUpApplicator applicator;
    private final PowerUp picked;

    /**
     * HitPowerUp constructor.
     * @param p
     * @param a
     */
    public HitPowerUp(final PowerUp p, final PowerUpApplicator a) {
        this.picked = p;
        this.applicator = a;
    }

    /**
     * @return the picked PowerUp
     */
    public PowerUp getPickedPowerUp() {
        return this.picked;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(final GameState currentGame) {
        this.applicator.applyPowerUp(currentGame.getWorld());
    }

}
