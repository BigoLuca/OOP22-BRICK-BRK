package brickbreaker.common;

import javafx.scene.image.Image;

public enum GameImages {

    FOREST_LANDSCAPE("forest.png"),

    SPACE_LANDSCAPE("space.png"),

    NIGHT_SKY("night-sky.png"),

    SEA_LANDSCAPE("sea.png"),

    CITY_LANDSCAPE("city.png"),

    GAME_ICON("icon.png"),

    EASY_DIFFICULTY("easy.png"),

    MEDIUM_DIFFICULTY("medium.png"),

    HARD_DIFFICULTY("hard.png"),

    MIX_DIFFICULTY("mix.png"),

    NICKNAME_LABEL("nickname.png"),
    
    TYPE_YOUR_NAME_LABEL("typeYourName.png"),

    GLOBAL_LABEL("toChange.png"),

    LOCAL_LABEL("toChange.png"),

    NEXT("toChange.png"),

    PREVIOUS("toChange.png");

    private static final String RES_PATH = "Images/";
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
