package brickbreaker.controllers;

import brickbreaker.model.GameState;
import brickbreaker.view.View;

/**
 * This interface defines the game controller: the interaction between model and view.
 * 
 * @author Agostinelli Francesco
 */
public interface Controller {

    /**
     * Method to set the Model.
     * @param modelToAttach
     */
    void setModel(GameState modelToAttach);

    /**
     * @return the model as GameState class
     */
    GameState getModel();

    /**
     * Method to set the View.
     * @param viewToAttach
     */
    void setView(View viewToAttach);

    /**
     * @return the view
     */
    View getView();

    /**
     * Abstract method to initialize the game.
     */
    void init();
}
