package brickbreaker.controllers.state.event;

import java.util.List;

import brickbreaker.model.GameState;

/**
 * Interface to control the events of the world.
 * 
 * @author Bighini Luca
 * @author Agostinelli Francesco
 */
public interface WorldEventListener {

    /**
     * Add the event passed to the world event list.
     * @param event
     */
    void notifyEvent(HitObjects event);

    /**
     * @return the list of event detected
     */
    List<HitObjects> getWorldEventsList();

    /**
     * Process all the events in the list.
     */
    void processAll();

    /**
     * Set the class GameState.
     * @param currentState
     */
    void setGameState(GameState currentState);

    /**
     * @return the current GameState
     */
    GameState getGameState();
}
