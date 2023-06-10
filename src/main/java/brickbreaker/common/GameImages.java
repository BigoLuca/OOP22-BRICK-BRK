package brickbreaker.common;


import javafx.scene.image.Image;

public enum GameImages {

    FOREST_LANDSCAPE("forest.png"),

    SPACE_LANDSCAPE("background.jpg"),

    NIGHT_SKY("night-sky.jpg"),

    SEA_LANDSCAPE("nickname.png"),

    CITY_LANDSCAPE("City.png"),

    GAME_ICON("nickname.png"),

    EASY_DIFFICULTY("easy.png"),

    MEDIUM_DIFFICULTY("medium.png"),

    HARD_DIFFICULTY("hard.png"),

    MIX_DIFFICULTY("mix.png"),

    NICKNAME_LABEL("nickname.png"),
    
    TYPE_YOUR_NAME_LABEL("typeYourName.png"),

    PICK_A_LEVEL("pickALevel.png"),

    NEXT("next.png"),

    PREVIOUS("previous.png"),

    NOT_LOADED_ITEM("nickname.png"),
    
    TITLE("Title.png"),

    ENDLESS_MODE_CHOICE("endless.png"),

    LEVELS_LABEL("levels.png"),

    LEVELS_MODE_CHOICE("start.png"),

    LEADERBOARD_CHOICE("leaderboards.png"),

    UP_ARROW("upArrow.png"),

    DOWN_ARROW("downArrow.png"),

    RIGHT_ARROW("rightArrow.png"),

    LEFT_ARROW("leftArrow.png"),

    PLAYER_LOST("youLost.png"),
    
    PLAYER_WIN("youWin.png");

    private static final String RES_PATH = "images/";
    private String fileName;
    private Image image;

    GameImages(final String fileName) {
        this.fileName = fileName;
    }

    public void setImage(final Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }

    public String getFilePath() {
        return RES_PATH + this.fileName;
    }
}
