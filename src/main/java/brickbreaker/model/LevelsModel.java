package brickbreaker.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import brickbreaker.common.Mode;
import brickbreaker.model.rank.GameRank;
import brickbreaker.model.user.User;

public class LevelsModel extends AbstractGameModel {

    private List<Integer> difficulty;
    //TODO private List<GameRank> levelRanks;

    public LevelsModel(final User user) {
        super(Mode.LEVEL, new GameRank("globalLevel.json"), user);
        Integer step = Math.floorDiv(80, this.getListMapLenght());
        difficulty = IntStream.rangeClosed(10, 90)
                        .filter(n -> (90 - n) % step == 0).boxed()
                        .sorted((a, b) -> b - a).collect(Collectors.toList());
    }

    public Level getLevel(Integer level) {
        return new Level(level, this.getNameMap(level), this.difficulty.get(level));
    }
    
}
