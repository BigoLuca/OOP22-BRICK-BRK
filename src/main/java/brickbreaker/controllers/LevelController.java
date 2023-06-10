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
    private Difficulty difficulty = getRandomDifficulty();
    private Optional<Integer> level = Optional.empty();

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

    private Difficulty getRandomDifficulty() {
        Random randomDiff = new Random();
        //System.out.println("Random: " + Difficulty.values()[randomDiff.nextInt(Difficulty.values().length)]);
        return Difficulty.values()[randomDiff.nextInt(Difficulty.values().length)];
    }

    public void setDifficultyLevel(final Difficulty diff) {
        if (diff.equals(Difficulty.RANDOM)) {
            this.difficulty = this.getRandomDifficulty();
        } else {
            this.difficulty = diff;
        }
        System.out.println("Difficulty: " + this.difficulty);
    }

    protected Level getLevel() {
        System.out.println("Level: " + this.level);
        System.out.println("diff: " + this.difficulty);
        if(this.level.isPresent() && level.get() < this.mapList.size()){
            return new Level(level.get(), WorldFactory.getInstance().getWorld(this.level.get()));
        }
        
        return new Level(0, WorldFactory.getInstance().getRandomWorld(this.difficulty));
    }
}
