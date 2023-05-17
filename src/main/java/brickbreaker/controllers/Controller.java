package brickbreaker.controllers;

import brickbreaker.model.GameModel;
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
    void setModel(GameModel modelToAttach);

    /**
     * @return the model as GameState class
     */
    GameModel getModel();

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
