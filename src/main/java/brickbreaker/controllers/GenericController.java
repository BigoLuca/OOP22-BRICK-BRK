package brickbreaker.controllers;

import brickbreaker.controllers.session.Session;
import brickbreaker.model.GameModel;
import brickbreaker.view.View;

public class GenericController implements Controller {

    private GameModel g;
    private View v;
    private Session s;

    @Override
    public void setModel(GameModel model) {
        this.g = model;
    }

    @Override
    public GameModel getModel() {
        return this.g;
    }

    @Override
    public void setView(View view) {
        this.v = view;
    }

    @Override
    public View getView() {
        return this.v;
    }

    public void setSession(final Session currentSession) {
        this.s = currentSession;
    }

    public Session getSession() {
        return this.s;
    }

    public void init() {
        
    }
}
