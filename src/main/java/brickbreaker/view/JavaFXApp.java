package brickbreaker.view;

import brickbreaker.common.Difficulty;
import brickbreaker.controllers.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import brickbreaker.view.PlayerScene;

/**
 * Sample JavaFX application.
 */
public class JavaFXApp extends Application {

    //LevelScene LevelScene = new LevelScene(controller.getModel().getListMapLenght());
    protected Stage primaryStage;
    protected Controller controller;

    private GameScene gameWindow;
    private PlayerScene playerScene = new PlayerScene(this);
    private ModeScene modeScene = new ModeScene(this);

    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.gameWindow = new GameScene(this.primaryStage);
        this.controller = new Controller(gameWindow);

        switchToPlayerScene();
    }

    public void switchToPlayerScene(){
        playerScene.show();
    }

    public void switchToModeScene(){
        modeScene.show();
    }

    public void switchToLevelMatch(){
        //controller.createLevels(0);
    }
}
