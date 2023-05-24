package brickbreaker.model.rank;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import brickbreaker.ResourceLoader;

public class GameRank implements Rank {

    private Integer capacity;
    private List<PlayerStats> rank;
    private String fileName;

    /**
     * GameRank constructor.
     * @param rankCapacity
     */
    public GameRank(final Integer rankCapacity, final String fileName) {
        this.rank = ResourceLoader.getInstance().getRank(fileName);
        this.capacity = rankCapacity;
        this.fileName = fileName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PlayerStats> getRank() {
        return this.rank;
    }

    public Integer getPlayerScore(final String playerName) {
        Iterator<PlayerStats> i = this.rank.iterator();
        while (i.hasNext()) {
            PlayerStats p = i.next();
            if (p.getName().equals(playerName)) {
                return p.getScore();
            }
        }
        return 0;
    }

    //TODO update if already present
    /**
     * {@inheritDoc}
     */
    @Override
    public void addPlayer(final PlayerStats newStats) {

        this.rank.add(newStats);
        Collections.sort(this.rank, Comparator.comparing(PlayerStats::getScore));
        if (this.rank.size() >= capacity) {
            this.rank.remove(this.rank.size() - 1);
        }
        ResourceLoader.getInstance().writeRank(this.rank, this.fileName);
    }
    
}
