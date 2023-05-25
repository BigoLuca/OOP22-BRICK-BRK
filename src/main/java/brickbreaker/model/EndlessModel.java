package brickbreaker.model;

import java.util.Optional;
import java.util.Random;
import brickbreaker.common.Difficulty;
import brickbreaker.common.Mode;
import brickbreaker.model.factory.WorldFactory;
import brickbreaker.model.rank.Rank;
import brickbreaker.model.state.GameStateImpl.State;
import brickbreaker.model.user.User;
import brickbreaker.model.world.World;

public class EndlessModel extends AbstractGameModel {

    private Optional<Difficulty> chosen;
    private Level current;

    public EndlessModel(final Mode m, final Rank r, final User u, final Optional<Difficulty> diffToSet) {
        super(m, r, u);
        this.chosen = diffToSet;
        //TODO: Create a won initial level.
    }

    private Difficulty getRandomDifficulty() {
        Random r = new Random();
        return Difficulty.values()[r.nextInt(3)];
    }

    @Override
    public Optional<Level> getNextMatch() {
        if (this.current.getGameState().getState() == State.LOST) {
            return Optional.empty();
        } else {
            Difficulty d = this.chosen.isPresent() ? this.chosen.get() : this.getRandomDifficulty();
            World w = WorldFactory.getInstance().createFromDifficulty(NULL_WORLD_PLACEHOLDER, d); 
        }
    }
}
