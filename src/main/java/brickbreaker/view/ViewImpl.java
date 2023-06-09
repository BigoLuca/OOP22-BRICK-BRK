package brickbreaker.view;

import javafx.stage.Stage;
import brickbreaker.controllers.GenericController;

/**
 * Implements the {@link View} interface.
 */
public abstract class ViewImpl implements View {

    private Stage currentStage;
    private GenericController currentController;

    public Stage getStage() {
        return this.currentStage;
    }

    public void setStage(final Stage stageToSet) {
        this.currentStage = stageToSet;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void setController(final GenericController controllerToAttach) {
        this.currentController = controllerToAttach;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public GenericController getController() {
        return this.currentController;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public abstract void init();
}
