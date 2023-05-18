package brickbreaker.model;

import brickbreaker.common.Mode;
import brickbreaker.model.rank.Rank;
import brickbreaker.model.state.GameState;

public abstract class AbstractGameModel implements GameModel {

    private Mode mode;
    private Rank rank;
    private GameState currentMatch;

    public AbstractGameModel(final Mode m, final Rank r) {
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
    public Rank getRank() {
        return this.rank;
    }

    @Override
    public void setRank(Rank rankToSet) {
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
