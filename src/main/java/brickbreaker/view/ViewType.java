package brickbreaker.view;

import brickbreaker.controllers.EndlessController;
import brickbreaker.controllers.GenericController;
import brickbreaker.controllers.HomeController;
import brickbreaker.controllers.LevelControllerImpl;
import brickbreaker.controllers.LevelMenuController;
import brickbreaker.controllers.RankController;
import brickbreaker.controllers.UserController;

public enum ViewType {

    HOME("HomeView", new HomeController()),

    LEVEL("LevelsMenuView", new LevelMenuController()),

    ENDLESS("EndlessMenuView", new EndlessController()),

    RANK("RankView", new RankController()),

    MATCH("GameView", new LevelControllerImpl()),

    SETUP("SetUpView", new UserController());

    private static final String DIRECTORY = "viewStyle/";
    private static final String FORMAT = ".fxml";

    private String fileName;
    private GenericController controller;

    ViewType (final String s, final GenericController c) {
        this.fileName = s;
        this.controller = c;    
    }

    public String getPath() {
        return DIRECTORY + this.fileName + FORMAT;
    }

    public GenericController getController() {
        return this.controller;
    }
}
