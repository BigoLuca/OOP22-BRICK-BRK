package brickbreaker.model;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import brickbreaker.ResourceLoader;
import brickbreaker.model.rank.GameRank;
import brickbreaker.model.rank.Rank;

public class GameModelImpl implements GameModel {

    private Rank rank;
    private List<String> mapList;
    private List<Integer> difficulty;

    public GameModelImpl(final String fileName) {
        this.rank = new GameRank(fileName);
        this.mapList = ResourceLoader.getInstance().getMapsNames();
        this.difficulty = IntStream.rangeClosed(10, 90)
                            .filter(n -> (90 - n) % Math.floorDiv(80, this.getListMapLenght()) == 0)
                            .boxed()
                            .sorted((a, b) -> b - a)
                            .collect(Collectors.toList());
    }

    @Override
    public Rank getRank() {
        return this.rank;
    }

    @Override
    public String getNameMap(final Integer i) {
        return this.mapList.get(i);
    }

    @Override
    public Integer getListMapLenght(){
        return this.mapList.size();
    }

    @Override
    public Level getNextLevel() {
        Random randomDiff = new Random();
        Integer diff = randomDiff.nextInt(90 - 10 + 1) + 10; // random between [10,90]
        Integer map = randomDiff.nextInt(this.getListMapLenght());
        
        return new Level(0, this.getNameMap(map), diff);
    }

    @Override
    public Level getNextLevel(Integer level) {
        return new Level(level, this.getNameMap(level), this.difficulty.get(level));
    }

}
