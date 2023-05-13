package brickbreaker.view;

import brickbreaker.controllers.Controller;

/**
 * Implements the {@link View} interface.
 */
public abstract class ViewImpl implements View {

    private Controller currentController;

    /**
     * View constructor.
     * @param controllerToAttach
     */
    public ViewImpl(final Controller controllerToAttach) {
        this.setController(controllerToAttach);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void setController(final Controller controllerToAttach) {
        this.currentController = controllerToAttach;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public Controller getController() {
        return this.currentController;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public abstract void init();
}
