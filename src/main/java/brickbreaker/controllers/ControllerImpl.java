package brickbreaker.controllers;

import brickbreaker.model.GameModel;
import brickbreaker.view.View;

public abstract class ControllerImpl implements Controller{

    private GameModel model;
    private View view;

    public abstract void init();

    public void setModel(final GameModel modelToAttach) {
        this.model = modelToAttach;
    }

    public GameModel getModel() {
        return this.model;
    }

    public void setView(final View viewToAttach) {
        this.view = viewToAttach;
    }

    public View getView() {
        return this.view;
    }
}
