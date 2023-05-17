package brickbreaker.controllers;

import brickbreaker.model.GameModel;
import brickbreaker.view.View;

public interface Controller {

    void setModel(final GameModel modelToAttach);

    GameModel getModel();

    void setView(final View viewToAttach);

    View getView();

    void init();
}