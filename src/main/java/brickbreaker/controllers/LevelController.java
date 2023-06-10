package brickbreaker.controllers;

import java.util.List;
import java.util.Random;

import brickbreaker.MapInfo;
import brickbreaker.ResourceLoader;
import brickbreaker.common.Difficulty;
import brickbreaker.model.Level;
import brickbreaker.model.factory.WorldFactory;

public class LevelController {

    private List<MapInfo> mapList;
    private Level currentLevel;

    public LevelController() {
        this.mapList = ResourceLoader.getInstance().getMapsInfo();
    }
    
    protected Level getCurrentLevel() {
        return this.currentLevel;
    }

    public String getNameMap(final Integer i) {
        return this.mapList.get(i).getName();
    }

    public Integer getListMapLenght(){
        return this.mapList.size();
    }

    private Difficulty getRandomDifficulty() {
        Random randomDiff = new Random();
        return Difficulty.values()[randomDiff.nextInt(3)];
    }

    public void setRandomLevel(final Difficulty diff) {
        Difficulty d = diff.equals(Difficulty.RANDOM) ? this.getRandomDifficulty() : diff;
        this.currentLevel = new Level(0, WorldFactory.getInstance().getRandomWorld(d));
    }

    public void setLevel(Integer level) {
        this.currentLevel = new Level(level, WorldFactory.getInstance().getWorld(level));
    }
}
