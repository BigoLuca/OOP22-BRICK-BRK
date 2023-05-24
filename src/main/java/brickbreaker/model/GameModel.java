package brickbreaker.model;

import brickbreaker.common.Mode;
import brickbreaker.model.rank.Rank;
import brickbreaker.model.user.User;

public interface GameModel {

    User getUser();

    Mode getMode();

    Rank getRank();

    void setRank(Rank rankToSet);

    String getNameMap(Integer i);

    Integer getListMapLenght();

}
