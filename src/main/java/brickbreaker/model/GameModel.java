package brickbreaker.model;

import java.util.List;
import java.util.Optional;
import brickbreaker.common.Mode;
import brickbreaker.model.rank.Rank;
import brickbreaker.model.user.User;

public interface GameModel {

    Optional<Level> getNextMatch();

    User getUser();

    Mode getMode();

    Rank getRank();

    void setRank(Rank rankToSet);

    String getNameMap(Integer i);

    List<String> getListMapLenght();

}
