package brickbreaker.model;

import java.util.Optional;

import brickbreaker.common.Difficulty;

public interface GameModel {

    String getNameMap(Integer i);

    Integer getListMapLenght();

    Level getRandomLevel(Optional<Difficulty> difficulty);

    Level getLevel(Integer level);

}
