package brickbreaker.model.rank;

import java.util.SortedSet;
import java.util.TreeSet;

public class GameRank implements Rank{

    private Integer capacity;
    private SortedSet<PlayerStats> rank;

    public GameRank(final Integer rankCapacity) {
        this.rank = new TreeSet<PlayerStats>();
        this.capacity = rankCapacity;
    }

    @Override
    public SortedSet<PlayerStats> getRank() {
        return this.rank;
    }

    @Override
    public boolean addPlayer(final PlayerStats newStats) {

        boolean result = this.rank.add(newStats);
        if (this.rank.size() >= capacity) {
            this.rank.remove(this.rank.last());
        }
        return result;
    }
    
}
