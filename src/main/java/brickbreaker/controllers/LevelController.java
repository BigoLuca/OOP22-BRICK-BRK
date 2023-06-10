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
    private Difficulty difficulty;

    public LevelController() {
        this.mapList = ResourceLoader.getInstance().getMapsInfo();
    }

    public String getNameMap(final Integer i) {
        return this.mapList.get(i).getName();
    }

    public Integer getListMapLenght(){
        return this.mapList.size();
    }

    private Difficulty getRandomDifficulty() {
        Random randomDiff = new Random();
        return Difficulty.values()[randomDiff.nextInt(Difficulty.values().length)];
    }

    public void setDifficultyLevel(final Difficulty diff) {
        this.difficulty = diff.equals(Difficulty.RANDOM) ? this.getRandomDifficulty() : diff;
    }

    public Level getLevel(Optional<Integer> level) {
        if(level.isPresent() && level.get() < this.mapList.size()){
            return new Level(level.get(), WorldFactory.getInstance().getWorld(level.get()));
        }
        
        return new Level(0, WorldFactory.getInstance().getRandomWorld(this.difficulty));
    }
}
