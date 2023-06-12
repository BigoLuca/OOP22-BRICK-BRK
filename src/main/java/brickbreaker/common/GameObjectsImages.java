package brickbreaker.common;

import javafx.scene.image.Image;

/**
 * Enum that stores all the images related to the game objects.
 */
public enum GameObjectsImages {

    BLUE_BRICK("blueBrick.png"),

    LIME_BRICK("limeBrick.png"),

    PURPLE_BRICK("purpleBrick.png"),

    RED_BRICK("redBrick.png"),

    ORANGE_BRICK("orangeBrick.png"),

    BRIGHT_BLUE_BRICK("brightBlueBrick.png"),

    YELLOW_BRICK("yellowBrick.png"),

    GREEN_BRICK("greenBrick.png"),

    GREY_BRICK("greyBrick.png"),

    BROWN_BRICK("brownBrick.png"),

    FAST_BALL("fastBall.png"),

    SLOW_BALL("slowBallMalus.png"),

    LONG_BAR("longBarBonus.png"),

    SHORT_BAR("shortBarMalus.png"),

    FIFTY_SCORE_POWER_UP("fiftyScorePP.png"),

    ONE_HUNDRED_SCORE_POWER_UP("oneHundredScorePP.png"),

    LARGE_SCORE_POWER_UP("Large_Score_PP.png"),

    MEGA_SCORE_POWER_UP("megaScorePP.png"),

    FIFTY_SCORE_MALUS("fiftyScoreMalus.png"),

    ONE_HUNDRED_SCORE_MALUS("oneHundredScoreMalus.png"),

    LIFE_ICON("incLife.png"),

    MULTI_BALL("multiBall.png"),

    BALL("ball.png"),

    BAR_ANIMATION_1("bar.png"),

    BAR_ANIMATION_2("barAnimation1.png"),

    BAR_ANIMATION_3("barAnimation2.png");

    private static final String RES_PATH = "images//sprites/";
    private String fileName;
    private Image i;

    GameObjectsImages(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * This method returns the file path of the image.
     * @return A String object which is the file path of the image.
     */
    public String getFilePath() {
        return RES_PATH + this.fileName;
    }

    /**
     * This method sets the Image object related to the current enum value.
     * @param image An Image object which will be binded with the current selected enum value.
     */
    public void setImage(final Image image) {
        this.i = image;
    }

    /**
     * This method gets the Image object related to the current enum value.
     * @return the Image object binded with the enum value.
     */
    public Image getImage() {
        return this.i;
    }
}
