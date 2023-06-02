package brickbreaker.model;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import brickbreaker.ResourceLoader;
import brickbreaker.common.Difficulty;
import brickbreaker.model.factory.WorldFactory;

public class GameModelImpl implements GameModel {

    private List<String> mapList;
    private List<Integer> difficulty;

    public GameModelImpl() {
        this.mapList = ResourceLoader.getInstance().getMapsNames();
        this.difficulty = IntStream.rangeClosed(10, 90)
                            .filter(n -> (90 - n) % Math.floorDiv(80, this.getListMapLenght()) == 0)
                            .boxed()
                            .sorted((a, b) -> b - a)
                            .collect(Collectors.toList());
    }

    @Override
    public String getNameMap(final Integer i) {
        return this.mapList.get(i);
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
    public Level getRandomLevel(final Optional<Difficulty> diff) {

        Difficulty d = diff.isPresent() ? diff.get() : this.getRandomDifficulty();
        return new Level(0, WorldFactory.getInstance().getWorld(d));
    }

    @Override
    public Level getLevel(Integer level) {
        return new Level(level, WorldFactory.getInstance().getWorld(this.getNameMap(level), this.difficulty.get(level)));
    }

}
