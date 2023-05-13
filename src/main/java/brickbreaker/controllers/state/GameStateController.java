package brickbreaker.controllers.state;

import brickbreaker.controllers.Controller;

/**
 * This interface defines the game state controller.
 * 
 * @author Agostinelli Francesco
 */
public interface GameStateController extends Controller {

    /**
     * Quit fro the current game.
     */
    void quitGame();

    /**
     * Pauses the current game.
     */
    void pauseGame();

    /**
     * This method gets the current game score.
     * @return an integer value that represents the score.
     */
    int getScore();
}
