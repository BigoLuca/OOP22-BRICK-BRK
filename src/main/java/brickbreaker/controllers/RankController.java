package brickbreaker.controllers;

import java.util.List;
import java.util.stream.Collectors;

import brickbreaker.ResourceLoader;
import brickbreaker.common.Mode;
import brickbreaker.model.rank.GameRank;

/**
 * The controller of the ranks.
 */
public class RankController {

    private final String ENDLESS_RANKS = "endless.json";
    private final String LEVEL_RANKS = "levels.json";

    private List<GameRank> endlessRanks;
    private List<GameRank> levelsRanks;

    /**
     * RankController constructor.
     */
    public RankController() {
        //Loading all the global ranks.
        this.endlessRanks = this.loadEndless();
        this.levelsRanks = this.loadLevels();
    }

    /**
     * This method loads all the endless mode ranks from Json file, which function as database,
     * and creates a List<GameRank> object.
     * @return a List<GameRank> object that contains all the endless mode ranks.
     */
    private List<GameRank> loadEndless() {
        return ResourceLoader.getInstance().getAllRanks(ENDLESS_RANKS).stream().map(item -> new GameRank(item)).collect(Collectors.toList());
    }

    /**
     * This method loads all the levels mode ranks from Json file, which function as database,
     * and creates a List<GameRank> object.
     * @return
     */
    private List<GameRank> loadLevels() {
        return ResourceLoader.getInstance().getAllRanks(LEVEL_RANKS).stream().map(item -> new GameRank(item)).collect(Collectors.toList());
    }

    /**
     * Method to add on json file the new score of the user.
     * @param mode
     * @param level
     * @param username
     * @param newScore
     */
    protected void addRank(Mode mode, Integer level, String username, Integer newScore) {
        if (mode.equals(Mode.ENDLESS)) {
            ResourceLoader.getInstance().writeRank(ENDLESS_RANKS, level, username, newScore);
        } else {
            ResourceLoader.getInstance().writeRank(LEVEL_RANKS, level, username, newScore);
        }
    }

    /**
     * Method to get the EndlessRank.
     * @param index
     * @return a GameRank
     */
    public GameRank getEndlessRank(Integer index) {
        return this.endlessRanks.get(index);
    }

    /**
     * Method to get the size of endless rank.
     * @return an integer size
     */
    public Integer getEndlessRankQuantity() {
        Integer q;

        try {
            q = this.endlessRanks.size();
        } catch(NullPointerException e) {
            q = 0;
        }

        return q;
    }

    /**
     * Method to get the LevelsRank.
     * @param index
     * @return a GameRank
     */
    public GameRank getLevelsRank(Integer index)  {
        return this.levelsRanks.get(index);
    }

    /**
     * Method to get the size of endless rank.
     * @return an integer size
     */
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
