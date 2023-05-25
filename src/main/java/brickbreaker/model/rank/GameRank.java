package brickbreaker.model.rank;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameRank implements Rank {

    private Integer capacity;
    private List<PlayerStats> rank;

    /**
     * GameRank constructor.
     * @param rankCapacity
     */
    public GameRank(final Integer rankCapacity, final List<PlayerStats> stats) {
        this.rank = stats;
        this.capacity = rankCapacity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PlayerStats> getRank() {
        return this.rank;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addPlayer(final PlayerStats newStats) {

        this.rank.add(newStats);
        Collections.sort(this.rank, Comparator.comparing(PlayerStats::getScore));
        if (this.rank.size() >= capacity) {
            this.rank.remove(this.rank.size() - 1);
        }

        return this.rank.contains(newStats);
    }
    
}
