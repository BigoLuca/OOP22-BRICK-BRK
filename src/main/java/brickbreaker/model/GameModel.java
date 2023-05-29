package brickbreaker.model;

import brickbreaker.model.rank.Rank;

public interface GameModel {

    String getNameMap(Integer i);

    Integer getListMapLenght();

    Rank getRank();

    Level getNextLevel();

    Level getNextLevel(Integer level);

}
