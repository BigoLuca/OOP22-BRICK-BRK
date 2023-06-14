package brickbreaker.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import brickbreaker.MapInfo;
import brickbreaker.ResourceLoader;
import brickbreaker.common.Difficulty;
import brickbreaker.model.Level;
import brickbreaker.model.factory.WorldFactory;

/**
 * The controller of the levels generation.
 */
public class LevelController {

    private List<MapInfo> mapList;
    private Difficulty defaultDifficulty = Difficulty.RANDOM;
    private Optional<Integer> level = Optional.empty();

    /**
     * LevelController constructor.
     */
    public LevelController() {
        this.mapList = ResourceLoader.getInstance().getMapsInfo();
    }

    private Difficulty getRandomDifficulty() {
        Random randomDiff = new Random();
        return Difficulty.values()[randomDiff.nextInt(Difficulty.values().length)];
    }

    private Difficulty getDifficulty() {
        if (defaultDifficulty.equals(Difficulty.RANDOM)) {
            return this.getRandomDifficulty();
        } else {
            return defaultDifficulty;
        }
    }

    protected Difficulty getSettedDifficulty() {
        return this.defaultDifficulty;
    }

    /**
     * Method to get a level.
     * @return the level generated
     */
    protected Level getLevel() {
        if (this.level.isPresent() && level.get() < this.mapList.size()) {
            return new Level(level.get(), WorldFactory.getInstance().getWorld(this.level.get()));
        }
        return new Level(0, WorldFactory.getInstance().getRandomWorld(this.getDifficulty()));
    }

    /**
     * Method to set the level.
     * @param level
     */
    public void setLevel(final Optional<Integer> level) {
        this.level = level;
    }

    /**
     * @return true if have another level
     */
    public boolean hasNextLevel() {
        return this.level.isPresent() && this.level.get() < this.mapList.size() - 1;
    }

    /**
     * Assign the new level.
     */
    public void nextLevel() {
        if (this.level.isPresent()) {
            this.level = Optional.of(this.level.get() + 1);
        } else {
            this.level = Optional.empty();
        }
    }

    /**
     * Method to the the map level information.
     * @param i
     * @return a MapInfo
     */
    public MapInfo getMapInfo(final Integer i) {
        return this.mapList.get(i);
    }

    /**
     * Method to get the map index.
     * @param name the name of the map
     * @return the index of the map
     */
    public Integer getMapIndex(final String name) {
        Optional<MapInfo> m = this.mapList.stream().filter(map -> map.getName().equals(name)).findFirst();
        return m.get().getIndex();
    }

    /**
     * Method to get the map list lenght.
     * @return the map list lenght
     */
    public Integer getListMapLenght() {
        return this.mapList.size();
    }

    /**
     * Method to set the level difficulty.
     * @param diff the difficulty
     */
    public void setDifficultyLevel(final Difficulty diff) {
        this.defaultDifficulty = diff;
    }

    /**
     * Method to get the level name.
     * @param i the level index
     * @return the level name
     */
    public String getLevelName(final Integer i) {
        return this.mapList.get(i).getName();
    }
}
