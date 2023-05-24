package brickbreaker.model;

import brickbreaker.common.Mode;
import brickbreaker.model.rank.Rank;

public interface GameModel {

    Mode getMode();

    Rank getRank();

    void setRank(Rank rankToSet);

    String getNameMap(Integer i);

    Integer getListMapLenght();

}
