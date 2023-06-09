package brickbreaker.view;

public enum ViewType {

    HOME("HomeView"),

    LEVEL("LevelsMenuView"),

    ENDLESS("EndlessMenuView"),

    RANK("RankView"),

    MATCH("GameView"),

    SETUP("SetUpView");
    
    private static final String DIRECTORY = "viewStyle/";
    private static final String FORMAT = ".fxml";

    private String fileName;

    ViewType (final String s) {
        this.fileName = s;
    }

    public String getPath() {
        return DIRECTORY + this.fileName + FORMAT;
    }
}
