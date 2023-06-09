package brickbreaker.view;

import brickbreaker.controllers.GenericController;
import javafx.stage.Stage;

/**
 * This is a generic view implementation.
 */
public interface View {

    /**
     * This method initializes the view.
     */
    void init();

    /**
     * This method gets the current attached controller.
     * @return the current attached Controller object.
     */
    GenericController getController();

    /**
     * This method sets the view's controller to the one passed as parameter.
     * @param controllerToAttach a Controller object which will be attached to
     * the current view.
     */
    void setController(GenericController controllerToAttach);

    /**
     * This method returns the current stage.
     * @return a stage object
     */
    Stage getStage();

    /**
     * This method sets the view's stage to the one passed as parameter.
     * @param stageToSet
     */
    void setStage(Stage stageToSet);
}
