package brickbreaker.common;

/**
 * Enum representing the level difficulty of the game.
 */
public enum Difficulty {

    /** Easy. */
    EASY(70),
    /** Medium. */
    MEDIUM(50),
    /** Hard. */
    HARD(30),
    /** Random */
    RANDOM(0);

    private final Integer percentage;

    Difficulty(final Integer value) {
        this.percentage = value;
    }

    /**
     * This method returns the percentage of the difficulty.
     * @return an integer representing the difficulty
     */
    public Integer getBonusPercentage() {
        return this.percentage;
    }
}
