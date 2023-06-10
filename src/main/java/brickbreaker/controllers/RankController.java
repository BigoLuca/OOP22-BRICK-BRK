package brickbreaker.controllers;

import java.util.List;
import java.util.stream.Collectors;

import brickbreaker.ResourceLoader;
import brickbreaker.model.rank.GameRank;

public class RankController {

    private static final String ENDLESS_RANKS = "ranks/endless.json";
    private static final String LEVEL_RANKS = "ranks/levels.json";

    private List<GameRank> endlessRanks;
    private List<GameRank> levelsRanks;

    public void init() {
        //Loading all the global ranks.
        this.endlessRanks = this.loadEndless();
        this.levelsRanks = this.loadLevels();
    }

    private List<GameRank> loadEndless() {
        return ResourceLoader.getInstance().getAllRanks(ENDLESS_RANKS).stream().map(item -> new GameRank(item)).collect(Collectors.toList());
    }

    private List<GameRank> loadLevels() {
        return ResourceLoader.getInstance().getAllRanks(LEVEL_RANKS).stream().map(item -> new GameRank(item)).collect(Collectors.toList());
    }

    public GameRank getEndlessRank(Integer index) {
        return this.endlessRanks.get(index);
    }

    public Integer getEndlessRankQuantity() {
        return this.endlessRanks.size();
    }

    public GameRank getLevelsRank(Integer index)  {
        return this.levelsRanks.get(index);
    }

    public Integer getLevelsRankQuantity() {
        return this.levelsRanks.size();
    }
}
