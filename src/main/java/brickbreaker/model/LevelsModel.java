package brickbreaker.model;

import java.util.Iterator;
import java.util.Optional;
import brickbreaker.model.factory.WorldFactory;
import brickbreaker.ResourceLoader;
import brickbreaker.common.Difficulty;
import brickbreaker.model.user.User;
import brickbreaker.model.world.World;
import brickbreaker.model.rank.Rank;

//TODO: Extend Level object (to include a local leaderboard).
//TODO: Elaborate Endless rank.

public class LevelsModel extends AbstractGameModel {

    private World old;
    private Iterator<String> levelsName;

    public LevelsModel(final Rank r, final User u) {
        super(r, u);
        this.old = WorldFactory.getInstance().createFromDifficulty(NULL_WORLD_PLACEHOLDER, Difficulty.EASY);
        this.levelsName = ResourceLoader.getInstance().getMapsNames().iterator();
    }

    @Override
    public Optional<Level> getNextMatch() {
        if (this.levelsName.hasNext()) {
            World n = WorldFactory.getInstance().createFromWorld(this.levelsName.next(), old, false);
            this.old = n;
            return Optional.of(new Level(0, n));
        }

        return Optional.empty();
    }
}
