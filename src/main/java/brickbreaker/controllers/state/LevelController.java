package brickbreaker.controllers.state;

import brickbreaker.controllers.Controller;
import brickbreaker.model.state.GameStateImpl.State;

/**
 * This interface defines the game state controller.
 * 
 * @author Agostinelli Francesco
 */
public interface LevelController extends Controller {

    /**
     * Quit fro the current game.
     */
    void quitGame();

    /**
     * Pauses the current game.
     */
    void pauseGame();

    /**
     * Resumes the current game.
     * @throws InterruptedException
     */
    void resumeGame() throws InterruptedException;

    /**
     * This method gets the current game score.
     * @return an integer value that represents the score.
     */
    Integer getScore();

    /**
     * Method to chek the exit state if win or lost.
     * @return the current game state.
     */
    State getState();
}
