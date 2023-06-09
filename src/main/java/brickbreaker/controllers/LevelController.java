package brickbreaker.controllers;

import brickbreaker.controllers.input.InputController;
import brickbreaker.model.Level;

/**
 * This interface defines the game state controller.
 */
public interface LevelController extends Controller {

    /*
     * Get the current level
     * @return the current level
     */
    Level getLevel();

    /**
     * @return the input controller
     */
    InputController getInputController();

    /**
     * This method gets the current game score.
     * @return an integer value that represents the score.
     */
    Integer getScore();
}
