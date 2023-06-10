package brickbreaker.model;

import brickbreaker.common.Difficulty;
import brickbreaker.common.Mode;

public interface GameModel {

    Mode getMode();
    
    Level getLevel();
    
    String getNameMap(Integer i);

    Integer getListMapLenght();

    void createRandomLevel(Difficulty difficulty);

    void createLevel(Integer level);

}
