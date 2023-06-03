package brickbreaker.model.world;

import brickbreaker.model.world.WorldImpl.SideCollision;
import brickbreaker.model.world.gameObjects.Ball;

/**
 * Interface to executed the events of the world.
 */
public interface WorldEventListener {
    
    void process(final World w, final Ball ball, final SideCollision side, final Double newPos);
}
