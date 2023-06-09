package brickbreaker.view;

import brickbreaker.controllers.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class ViewController extends Application {
    
    private static ViewController INSTANCE;
    protected Stage primaryStage;
    protected Controller controller;
    

    private PlayerScene playerScene = new PlayerScene(this);
    private GameScene gameScene = new GameScene(this);

    
    public static ViewController getInstance() {
        if (INSTANCE == null) {
            return new ViewController();
        }
        return INSTANCE;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.controller = new Controller();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("BrickBreaker");
        switchToPlayerScene();
    };

    public void switchToPlayerScene(){
        playerScene.show();
    }

    public void switchToModeScene(){
        //modeScene.show();
    }

    public void switchToLevelMatch(){
        controller.playLevel(0);
    }

    public void render(){
        gameScene.update();
    }

}
