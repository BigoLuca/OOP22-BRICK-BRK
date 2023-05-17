package brickbreaker.model;

import brickbreaker.common.Mode;
import brickbreaker.model.rank.GameRank;
import brickbreaker.model.state.GameState;

public interface GameModel {

    boolean getNextMatch();

    Mode getMode();

    GameRank getRank();

    void setRank(final GameRank rankToSet);

    GameState getGameState();

    public void setGameState(final GameState g);
}
