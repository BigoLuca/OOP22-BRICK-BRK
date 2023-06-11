package brickbreaker.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import brickbreaker.MapInfo;
import brickbreaker.ResourceLoader;
import brickbreaker.common.Difficulty;
import brickbreaker.model.Level;
import brickbreaker.model.factory.WorldFactory;

public class LevelController {

    private List<MapInfo> mapList;
    private Difficulty defaultDifficulty = Difficulty.RANDOM;
    private Optional<Integer> level = Optional.empty();

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
    
    protected Level getLevel() {
        if(this.level.isPresent() && level.get() < this.mapList.size()){
            return new Level(level.get(), WorldFactory.getInstance().getWorld(this.level.get()));
        }
        return new Level(0, WorldFactory.getInstance().getRandomWorld(this.getDifficulty()));
    }

    public LevelController() {
        this.mapList = ResourceLoader.getInstance().getMapsInfo();
    }

    public void setLevel(final Optional<Integer> level) {
        this.level = level;
    }

    public boolean hasNextLevel() {
        return this.level.isPresent() && this.level.get() < this.mapList.size() - 1;
    }

    public void nextLevel() {
        if (this.level.isPresent()) {
            this.level = Optional.of(this.level.get() + 1);
        } else {
            this.level = Optional.empty();
        }
    }

    public String getNameMap(final Integer i) {
        return this.mapList.get(i).getName();
    }

    public Integer getMapIndex(final String name) {
        return this.mapList.stream().filter(map -> map.getName().equals(name)).findFirst().get().getIndex();
    }

    public Integer getListMapLenght(){
        return this.mapList.size();
    }

    public void setDifficultyLevel(final Difficulty diff) {
        this.defaultDifficulty = diff;
    }
}
