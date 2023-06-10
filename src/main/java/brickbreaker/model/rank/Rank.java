package brickbreaker.model.rank;

import java.util.Map;

/**
 * Interface for work with rank.
 */
public interface Rank {

    Map<String, Integer> getRank();

    void setRank(Map<String, Integer> r);

    boolean addUser(final String user, final Integer score);
}
