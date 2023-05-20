package brickbreaker.model.rank;

import java.util.List;

public interface Rank {

    List<PlayerStats> getRank();

    boolean addPlayer(final PlayerStats newStats);
}
