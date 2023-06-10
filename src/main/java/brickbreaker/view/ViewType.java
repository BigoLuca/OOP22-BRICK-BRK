package brickbreaker.view;

public enum ViewType {

    HOME("HomeView"),

    LEVEL("LevelsMenuView"),

    DIFFICULTY("DifficultyMenuView"),

    RANK("RankView"),

    MATCH("GameView"),

    SETUP("SetUpView"),

    GAMEOVER("EndGameView");
    
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
