package brickbreaker.controllers;

import brickbreaker.controllers.input.InputController;
import brickbreaker.controllers.listener.ErrorListener;

public abstract class AbstractController {
    
    protected UserController userController;
    protected ErrorListener errListener;
    protected InputController inputController;
    protected LevelController levelController;
    protected RankController rankController;
    
    public AbstractController() {
        this.userController = new UserController();
        this.errListener = new ErrorListener();
        this.inputController = new InputController();
        this.levelController = new LevelController();
    }

    public UserController getUserController() { return this.userController; }

    public InputController getInputController() { return this.inputController; }

    public ErrorListener getErrorListener() { return this.errListener; }

    public LevelController getLevelController() { return this.levelController; }

    public RankController getRankController() { return this.rankController; }

}
