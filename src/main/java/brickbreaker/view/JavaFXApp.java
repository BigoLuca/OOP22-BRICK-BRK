package brickbreaker.view;

import brickbreaker.controllers.GameController;
import javafx.application.Application;
import javafx.stage.Stage;
import brickbreaker.view.PlayerScene;

/**
 * Sample JavaFX application.
 */
public class JavaFXApp extends Application {

    //LevelScene LevelScene = new LevelScene(controller.getModel().getListMapLenght());
    protected Stage primaryStage;
    protected GameController controller;

    private GameScene gameWindow;
    private PlayerScene playerScene = new PlayerScene(this);
    private ModeScene modeScene = new ModeScene(this);

    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.gameWindow = new GameScene(this.primaryStage);
        this.controller = new GameController(gameWindow);

        switchToPlayerScene();
    }

    public void switchToPlayerScene(){
        playerScene.show();
    }

    public void switchToModeScene(){
        modeScene.show();
    }
}
