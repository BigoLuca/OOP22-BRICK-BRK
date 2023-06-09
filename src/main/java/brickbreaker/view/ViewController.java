package brickbreaker.view;

import brickbreaker.controllers.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class ViewController extends Application {

    protected Stage primaryStage;
    protected Controller controller;

    private PlayerScene playerScene;
    private GameScene gameScene;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("BrickBreaker");
        this.primaryStage.setOnCloseRequest(e -> {
            this.controller.stop();
        });
        this.controller = new Controller(this);
        switchToPlayerScene();
    }
    
    public void switchToPlayerScene(){
        this.playerScene = new PlayerScene(this);
        playerScene.show();
    }
    
    public void switchToModeScene(){
        //modeScene.show();
    }
    
    public void switchToLevelMatch(){
        this.gameScene = new GameScene(this);
        controller.playLevel(0);
    }

    public void render(){
        gameScene.update();
    }

}
