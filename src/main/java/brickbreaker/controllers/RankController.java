package brickbreaker.controllers;

import java.util.List;
import java.util.stream.Collectors;

import brickbreaker.ResourceLoader;
import brickbreaker.model.rank.GameRank;

public class RankController {

    private static final String ENDLESS_RANKS = "endless.json";
    private static final String LEVEL_RANKS = "levels.json";

    private List<GameRank> endlessRanks;
    private List<GameRank> levelsRanks;

    public RankController() {
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
        Integer q;

        try {
            q = this.endlessRanks.size();
        } catch(NullPointerException e) {
            q = 0;
        }

        return q;
    }

    public GameRank getLevelsRank(Integer index)  {
        return this.levelsRanks.get(index);
    }

    public Integer getLevelsRankQuantity() {
        Integer q;

        try {
            q = this.levelsRanks.size();
        } catch(NullPointerException e) {
            q = 0;
        }

        return q;
    }
}
