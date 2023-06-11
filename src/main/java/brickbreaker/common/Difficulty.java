package brickbreaker.common;

/**
 * Enum representing the level difficulty of the game.
 */
public enum Difficulty {

    /** Easy. */
    EASY(30, 70, 5),
    /** Medium. */
    MEDIUM(50, 50, 7),
    /** Hard. */
    HARD(70, 30, 11),
    /** Random. */ //TODO: need to have this order for write ranking.
    RANDOM(0,0,0);

    private final Integer brickP;
    private final Integer bP;
    private final Integer mlP;

    Difficulty(final int brickP, final int bonusPercentage, final int maxBrickLife) {
        this.brickP = brickP;
        this.bP = bonusPercentage;
        this.mlP = maxBrickLife;
    }

    /**
     * This method returns the percentage of the difficulty.
     * @return an integer representing the difficulty
     */
    public Integer getBonusPercentage() {
        return this.bP; 
    }

    public Integer getMaxBrickLife() {
        return this.mlP;
    }

    public Integer getBrickPercentage() {
        return this.brickP;
    }
}
