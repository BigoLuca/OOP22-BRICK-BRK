package brickbreaker.controllers.state.event;

import java.util.LinkedList;
import java.util.List;

import brickbreaker.model.GameState;

/**
 * Implements the {@link WorldEventListener} interface.
 * 
 * @author Bighini Luca
 * @author Agostinelli Francesco
 */
public class WorldEventListenerImpl implements WorldEventListener {

    private GameState state;
    private List<HitObjects> worldEvents;

    /**
     * World event listener constructor.
     */
    public WorldEventListenerImpl() {
        this.worldEvents = new LinkedList<HitObjects>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvent(final HitObjects event) {
        this.worldEvents.add(event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<HitObjects> getWorldEventsList() {
        return this.worldEvents;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processAll() {
        this.worldEvents.stream().forEach(event -> event.process(state));
        this.worldEvents.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameState(final GameState currentState) {
        this.state = currentState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getGameState() {
        return this.state;
    }

}
