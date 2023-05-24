package brickbreaker.model.rank;

import java.util.Map;
import brickbreaker.ResourceLoader;

public class GameRank implements Rank {

    private Map<String, Integer> rank;
    private String fileName;

    /**
     * GameRank constructor.
     * @param rankCapacity
     */
    public GameRank(final String fileName) {
        this.rank = ResourceLoader.getInstance().getRank(fileName);
        this.fileName = fileName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Integer> getRank() {
        return this.rank;
    }

    /**
     * {@inheritDoc}
     */
    public Integer getPlayerScore(final String username) {
        return this.rank.get(username);
    }

    //TODO update if already present
    /**
     * {@inheritDoc}
     */
    @Override
    public void addRank(final String playerName, final Integer newScore) {

        Boolean c = this.rank.containsKey(playerName);

        if (!c || (c && this.getPlayerScore(playerName) < newScore)) {
            this.rank.put(playerName, newScore);
            ResourceLoader.getInstance().writeRank(this.rank, this.fileName);
            this.rank = ResourceLoader.getInstance().getRank(fileName);
        }

    }
    
}
