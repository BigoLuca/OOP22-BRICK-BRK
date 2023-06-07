package brickbreaker.controllers;

import brickbreaker.controllers.listener.ErrorListener;
import brickbreaker.model.GameModel;
import brickbreaker.model.GameModelImpl;

public class ModelController {
    
    private UserController userController;
    private LevelController levelController;
    private ErrorListener errListener;
    private GameModel model;

    public ModelController() {
        this.userController = new UserController();
        this.errListener = new ErrorListener();
        this.model = new GameModelImpl();
    }

    public UserController getUserController() { return this.userController; }

    public LevelController getLevelController() { return this.levelController; }

    public void setLevelController(LevelController lc) { this.levelController = lc; }

    public ErrorListener getErrorListener() { return this.errListener; }

    public GameModel getModel() { return this.model; }

}
