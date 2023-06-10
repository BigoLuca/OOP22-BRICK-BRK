package brickbreaker.model;

import java.util.List;
import java.util.Random;
import brickbreaker.MapInfo;
import brickbreaker.ResourceLoader;
import brickbreaker.common.Difficulty;
import brickbreaker.common.Mode;
import brickbreaker.model.factory.WorldFactory;

public class GameModelImpl implements GameModel {

    private List<MapInfo> mapList;
    private Level currentLevel;
    private Mode currentMode;

    public GameModelImpl(final Mode m) {
        this.mapList = ResourceLoader.getInstance().getMapsInfo();
        this.currentMode = m;
    }

    public Mode getMode() {
        return this.currentMode;
    }
    
    public Level getLevel() {
        return this.currentLevel;
    }
    
    @Override
    public String getNameMap(final Integer i) {
        return this.mapList.get(i).getName();
    }

    @Override
    public Integer getListMapLenght(){
        return this.mapList.size();
    }

    private Difficulty getRandomDifficulty() {
        Random randomDiff = new Random();
        return Difficulty.values()[randomDiff.nextInt(3)];
    }

    @Override
    public void createRandomLevel(final Difficulty diff) {
        Difficulty d = diff.equals(Difficulty.RANDOM) ? this.getRandomDifficulty() : diff;
        this.currentLevel = new Level(0, WorldFactory.getInstance().getRandomWorld(d));
    }

    @Override
    public void createLevel(final Integer level) {
        this.currentLevel =  new Level(level, WorldFactory.getInstance().getWorld(level));
    }

}
