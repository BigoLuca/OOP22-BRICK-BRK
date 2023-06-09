package brickbreaker.model;

import brickbreaker.common.Difficulty;

public interface GameModel {

    Level getLevel();
    
    String getNameMap(Integer i);

    Integer getListMapLenght();

    void createRandomLevel(Difficulty difficulty);

    void createLevel(Integer level);

}
