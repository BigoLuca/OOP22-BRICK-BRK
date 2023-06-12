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
    HARD(70, 30, 10),
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
    
    public Integer getBonusPercentage() {
        return this.bP; 
    }

    /**
     * This method returns the maximum life value that every brick could have
     * in this difficulty category.
     * @return
     */
    public Integer getMaxBrickLife() {
        return this.mlP;
    }

    public Integer getBrickPercentage() {
        return this.brickP;
    }
}
