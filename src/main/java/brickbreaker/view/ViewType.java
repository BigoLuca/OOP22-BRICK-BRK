package brickbreaker.view;

import brickbreaker.controllers.Controller;
import brickbreaker.controllers.state.LevelControllerImpl;

public enum ViewType {
    HOME("HomeView", new HomeController()),

    RANK("RankView", new RankController()),

    MATCH("MatchView", new LevelControllerImpl());

    private static final String DIRECTORY = "viewStyle/";
    private static final String FORMAT = ".fxml";

    private String fileName;
    private Controller controller;

    ViewType (final String s, final Controller c) {
        this.fileName = s;
        this.controller = c;    
    }

    public String getPath() {
        return DIRECTORY + this.fileName + FORMAT;
    }

    public Controller getController() {
        return this.controller;
    }
}
