package brickbreaker.model.world.gameObjects.collision.powerUpApplicator;

import brickbreaker.common.Vector2D;
import brickbreaker.model.factory.GameFactory;
import brickbreaker.model.world.World;

public class MultiBallApplicator implements PowerUpApplicator {

    /**
     * Multi Ball constructor.
     */
    public MultiBallApplicator() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyPowerUp(final World world) {
        Vector2D pos = world.getMainBBox().getP2d();
        world.addBall(GameFactory.getInstance().createBall(pos, new Vector2D(1, -1)));
        world.addBall(GameFactory.getInstance().createBall(pos, new Vector2D(-1, -1)));
    }
}
