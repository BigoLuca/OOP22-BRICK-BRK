package brickbreaker.model.rank;

import java.util.Set;

public interface Rank {

    Set<PlayerStats> getRank();

    boolean addPlayer(final PlayerStats newStats);
}
