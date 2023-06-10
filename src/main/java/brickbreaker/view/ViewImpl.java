package brickbreaker.view;

import javafx.stage.Stage;

import java.util.Optional;

import brickbreaker.common.Mode;
import brickbreaker.controllers.Controller;

/**
 * Implements the {@link View} interface.
 */
public abstract class ViewImpl implements View {

    private Stage currentStage;
    private Controller controller;
    protected Mode mode;

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
    public void setController(final Controller controllerToAttach) {
        this.controller = controllerToAttach;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public Controller getController() {
        return this.controller;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public abstract void init();
}
