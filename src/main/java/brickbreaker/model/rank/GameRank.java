package brickbreaker.model.rank;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class GameRank implements Rank {

    private Integer capacity;
    private Set<PlayerStats> rank;

    public GameRank(final Integer rankCapacity) {
        this.rank = new TreeSet<PlayerStats>(new RankComparator());
        this.capacity = rankCapacity;
    }

    public GameRank(final TreeSet<PlayerStats> rankToSet, final Integer rankCapacity) {
        this.rank = rankToSet;
        this.capacity = rankCapacity;
    }
    
    @Override
    public Set<PlayerStats> getRank() {
        return this.rank;
    }

    @Override
    public boolean addPlayer(final PlayerStats newStats) {

        boolean result = this.rank.add(newStats);
        if (this.rank.size() >= capacity) {
            ((TreeSet<PlayerStats>) this.rank).pollLast();
        }
        return result;
    }

    class RankComparator implements Comparator<PlayerStats> {
        @Override
        public int compare(PlayerStats p1, PlayerStats p2) {
            return Integer.compare(p1.getScore(), p2.getScore());
        }
    }
    
}
