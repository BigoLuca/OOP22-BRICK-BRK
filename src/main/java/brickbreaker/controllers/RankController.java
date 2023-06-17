package brickbreaker.controllers;

import java.util.List;

import com.google.gson.reflect.TypeToken;

import brickbreaker.common.Difficulty;
import brickbreaker.common.JsonUtils;
import brickbreaker.model.rank.Rank;

/**
 * The controller of the ranks.
 */
public class RankController {

    private static final String ENDLESS_RANKS_FILE = "ranks/endless.json";
    private static final String LEVEL_RANKS_FILE = "ranks/levels.json";

    private List<Rank> endlessRanks;
    private List<Rank> levelsRanks;

    /**
     * RankController constructor.
     */
    public RankController() {
        this.endlessRanks = JsonUtils.load(new TypeToken<List<Rank>>() {
        }.getType(), ENDLESS_RANKS_FILE);
        this.levelsRanks = JsonUtils.load(new TypeToken<List<Rank>>() {
        }.getType(), LEVEL_RANKS_FILE);
    }

    /**
     * Method to save the ranks.
     */
    public void saveRanks() {
        JsonUtils.save(this.endlessRanks, ENDLESS_RANKS_FILE);
        JsonUtils.save(this.levelsRanks, LEVEL_RANKS_FILE);
    }

    /**
     * Method to get all the endless mode ranks.
     * 
     * @return a List<GameRank> object
     */
    public List<Rank> getEndlessRanks() {
        return this.endlessRanks;
    }

    /**
     * Method to get all the levels mode ranks.
     * 
     * @return a List<GameRank> object
     */
    public List<Rank> getLevelsRanks() {
        return this.levelsRanks;
    }

    /**
     * Method to add a new score to the endless rank.
     * The method add the score in the rank of the difficulty passed.
     * 
     * @param difficulty
     * @param username
     * @param newScore
     */
    public void addScoreInEndlessRank(Difficulty difficulty, String username, Integer newScore) {
        this.endlessRanks.stream().filter(r -> r.getIndex().equals(difficulty.ordinal())).findFirst().orElse(null)
                .addScore(username, newScore);
        JsonUtils.save(this.endlessRanks, ENDLESS_RANKS_FILE);
    }

    /**
     * Method to add a new score to the levels rank.
     * The method add the score in the rank of the difficulty passed.
     * 
     * @param level
     * @param username
     * @param newScore
     */
    public void addScoreInLevelsRank(Integer level, String username, Integer newScore) {
        this.levelsRanks.stream().filter(r -> r.getIndex().equals(level)).findFirst().orElse(null)
                .addScore(username, newScore);
        JsonUtils.save(this.levelsRanks, LEVEL_RANKS_FILE);
    }


    /**
     * Method to get the EndlessRank.
     * 
     * @param index the index of the rank
     * @return a GameRank
     */
    public Rank getEndlessRank(final Difficulty difficulty) {
        return this.endlessRanks.stream().filter(r -> r.getIndex().equals(difficulty.ordinal())).findFirst().orElse(null);
    }

    /**
     * Method to get the size of endless rank.
     * 
     * @return an integer size
     */
    public Integer getEndlessRankQuantity() {
        return this.endlessRanks.size();
    }

    /**
     * Method to get the LevelsRank.
     * 
     * @param index
     * @return a GameRank
     */
    public Rank getLevelsRank(final Integer level) {
        return this.levelsRanks.stream().filter(r -> r.getIndex().equals(level)).findFirst().orElse(null);
    }

    /**
     * Method to get the size of levels rank.
     * 
     * @return an integer size
     */
    public Integer getLevelsRankQuantity() {
        return this.levelsRanks.size();
    }
}
