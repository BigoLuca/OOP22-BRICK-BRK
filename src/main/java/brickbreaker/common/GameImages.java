package brickbreaker.common;

import javafx.scene.image.Image;

public enum GameImages {

    FOREST_LANDSCAPE("forest.png"),

    SPACE_LANDSCAPE("space.png"),

    SEA_LANDSCAPE("sea.png"),

    CITY_LANDSCAPE("city.png"),

    GAME_ICON("icon.png");

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
