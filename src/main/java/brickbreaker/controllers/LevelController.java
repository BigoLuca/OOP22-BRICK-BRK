package brickbreaker.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import brickbreaker.ResourceLoader;
import brickbreaker.common.Difficulty;
import brickbreaker.model.Level;
import brickbreaker.model.factory.WorldFactory;

public class LevelController {

    private List<String> mapList;
    private List<Integer> difficulty;

    public LevelController() {
        this.mapList = ResourceLoader.getInstance().getMapsNames();
        this.difficulty = IntStream.rangeClosed(10, 90)
                            .filter(n -> (90 - n) % Math.floorDiv(80, this.getListMapLenght()) == 0)
                            .boxed()
                            .sorted((a, b) -> b - a)
                            .collect(Collectors.toList());
    }

    public String getNameMap(final Integer i) {
        return this.mapList.get(i);
    }

    public Integer getListMapLenght(){
        return this.mapList.size();
    }

    private Difficulty getRandomDifficulty() {
        Random randomDiff = new Random();
        return Difficulty.values()[randomDiff.nextInt(3)];
    }

    public Level getRandomLevel(final Difficulty diff) {
        Difficulty d = diff.equals(Difficulty.RANDOM) ? this.getRandomDifficulty() : diff;
        return new Level(0, WorldFactory.getInstance().getWorld(d));
    }

    public Level getLevel(Integer level) {
        return new Level(level, WorldFactory.getInstance().getWorld(this.getNameMap(level), this.difficulty.get(level)));
    }

}
