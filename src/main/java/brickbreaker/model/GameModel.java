package brickbreaker.model;

import brickbreaker.common.Mode;
import brickbreaker.model.rank.Rank;
import brickbreaker.model.state.GameState;

public interface GameModel {

    boolean getNextMatch();

    Mode getMode();

    Rank getRank();

    void setRank(final Rank rankToSet);

    GameState getGameState();

    public void setGameState(final GameState g);
}
