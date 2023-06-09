package brickbreaker.controllers;

import brickbreaker.model.GameModel;
import brickbreaker.view.View;

public interface Controller {

    public void setModel(final GameModel model);

    public GameModel getModel();

    public void setView(final View view);

    public View getView();

    public void init();
}
