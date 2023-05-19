package brickbreaker.model;

import java.util.Optional;

import brickbreaker.common.Mode;
import brickbreaker.model.rank.Rank;

public interface GameModel {

    Optional<Level> getNextMatch();

    Mode getMode();

    Rank getRank();

    void setRank(Rank rankToSet);

    String getNameMap(Integer i);

    Integer getListMapLenght();

}
