package brickbreaker.model.rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import brickbreaker.ResourceLoader;

public class GameRank implements Rank {

    private Integer capacity;
    private List<PlayerStats> rank;
    private String fileName;


    public GameRank(final Integer rankCapacity) {
        this.rank = new ArrayList<PlayerStats>();
        this.capacity = rankCapacity;
    }

    public GameRank(final Integer rankCapacity, final String fileName) {
        this.rank = ResourceLoader.getInstance().getRank(fileName);
        this.capacity = rankCapacity;
        this.fileName = fileName;
    }
    
    @Override
    public List<PlayerStats> getRank() {
        return this.rank;
    }

    @Override
    public boolean addPlayer(final PlayerStats newStats) {

        this.rank.add(newStats);
        Collections.sort(this.rank, Comparator.comparing(PlayerStats::getScore));
        if (this.rank.size() >= capacity) {
            this.rank.remove(this.rank.size() - 1);
        }
        return ResourceLoader.getInstance().writeRank(this.rank, this.fileName);
    }
    
}
