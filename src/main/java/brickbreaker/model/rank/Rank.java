package brickbreaker.model.rank;

import java.util.SortedSet;

public interface Rank {

    SortedSet<PlayerStats> getRank();

    boolean addPlayer(final PlayerStats newStats);
}
