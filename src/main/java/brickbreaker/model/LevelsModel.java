package brickbreaker.model;

import java.util.Optional;

import brickbreaker.common.Mode;

public class LevelsModel extends AbstractGameModel {

    private Integer levelReached;

    public LevelsModel() {
        super(Mode.LEVEL);
        this.levelReached = 0;  // change with the level of the player
    }

    @Override
    public Optional<Level> getNextMatch() {

        Integer diff = 1; // TODO calc the difficulty
        levelReached++;

        if (levelReached <= this.getListMapLenght()) {
            return Optional.of(new Level(levelReached, this.getNameMap(levelReached), diff));
        } else {
            return Optional.empty();
        }
    }
    
}
