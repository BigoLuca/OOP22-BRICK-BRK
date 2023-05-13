package brickbreaker.controllers.state.event;

import brickbreaker.model.GameState;

/**
 * Interface for Object events.
 * 
 * @author Bighini Luca
 */
public interface HitObjects {

    /**
     * This method processes the collision event between objects and updates the world.
     * @param currentGame
     */
    void process(GameState currentGame);
}
