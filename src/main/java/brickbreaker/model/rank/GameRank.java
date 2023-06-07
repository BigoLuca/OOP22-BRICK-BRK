package brickbreaker.model.rank;

import brickbreaker.ResourceLoader;

/**
 * Class representing the rank.
 * Implements the {@link Rank} interface.
 */
public class GameRank implements Rank {

    private String filename;
    private Integer level;

    /**
     * GameRank constructor.
     */
    public GameRank(final String fileName, final Integer level) {
        this.filename = fileName;
        this.level = level;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addToRank(final String playerName, final Integer newScore) {
        Integer diff = ResourceLoader.getInstance().writeRank(this.filename, level, playerName, newScore);
        if (diff > 0 && this.filename.equals("levels.json")) {
            Integer i = 0;
            try {
                i = ResourceLoader.getInstance().getRank(filename, 0).get(playerName);
            } catch (Exception e) {}
            ResourceLoader.getInstance().writeRank(filename, 0, playerName, diff + i);
        }
    }
    
}
