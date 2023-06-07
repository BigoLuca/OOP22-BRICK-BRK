package brickbreaker.model;

import brickbreaker.common.Difficulty;

public interface GameModel {

    String getNameMap(Integer i);

    Integer getListMapLenght();

    Level getRandomLevel(Difficulty difficulty);

    Level getLevel(Integer level);

}
