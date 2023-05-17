package brickbreaker.model;

import brickbreaker.common.Mode;
import brickbreaker.model.rank.GameRank;
import brickbreaker.model.state.GameState;

public abstract class AbstractGameModel implements GameModel {

    private Mode mode;
    private GameRank rank;
    private GameState currentMatch;

    public AbstractGameModel(Mode m, GameRank r) {
        this.mode = m;
        this.rank = r;
    }

    @Override
    public abstract boolean getNextMatch();

    @Override
    public Mode getMode() {
        return this.mode;
    }

    @Override
    public GameRank getRank() {
        return this.rank;
    }

    @Override
    public void setRank(GameRank rankToSet) {
        this.rank = rankToSet;
    }

    @Override
    public GameState getGameState() {
        return this.currentMatch;
    }

    @Override
    public void setGameState(GameState g) {
        this.currentMatch = g;
    }
    
}
