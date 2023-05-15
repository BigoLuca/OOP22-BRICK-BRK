package brickbreaker.controllers;

import brickbreaker.model.GameState;
import brickbreaker.view.View;

/**
 * Abstract class to pass data between model and view.
 * Implements the {@link Controller} interface.
 * 
 * @author Agostinelli Francesco
 */
public abstract class ControllerImpl implements Controller {

    private GameState model;
    private View view;

    /**
     * {@inheritDoc}
     */
    public abstract void init();

    /**
     * {@inheritDoc}
     */
    public void setModel(final GameState modelToAttach) {
        this.model = modelToAttach;
    }

    /**
     * {@inheritDoc}
     */
    public GameState getModel() {
        return this.model;
    }

    /**
     * {@inheritDoc}
     */
    public void setView(final View viewToAttach) {
        this.view = viewToAttach;
    }

    /**
     * {@inheritDoc}
     */
    public View getView() {
        return this.view;
    }
}
