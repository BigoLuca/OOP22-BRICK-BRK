package brickbreaker.common;

/**
 * Enum representing the level difficulty of the game.
 */
public enum Difficulty {

    /** Easy. */
    EASY(20, 30, 70, 0),
    /** Medium. */
    MEDIUM(30, 50, 50, 30),
    /** Hard. */
    HARD(50, 70, 30, 50),
    /** Random. */ //TODO: need to have this order for write ranking.
    RANDOM(0,0,0,0);

    private final Integer min;
    private final Integer max;
    private final Integer bP;
    private final Integer mlP;

    Difficulty(final int minBricks, final int maxBricks, final int bonusPercentage, final int moreLifePercentage) {
        this.min = minBricks;
        this.max = maxBricks;
        this.bP = bonusPercentage;
        this.mlP = moreLifePercentage;
    }

    /**
     * This method returns the percentage of the difficulty.
     * @return an integer representing the difficulty
     */
    public Integer getBonusPercentage(final Integer bricksQuantity) {
        return (bricksQuantity / 100) * this.bP; 
    }

    public Integer getMoreLifePercentage(final Integer bricksQuantity) {
        return (bricksQuantity / 100) * this.mlP;
    }

    public Integer getMinimumBricksQuantity() {
        return this.min;
    }

    public Integer getMaximumBricksQuantity() {
        return this.max;
    }
}
