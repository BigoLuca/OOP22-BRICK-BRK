package brickbreaker.model;

import java.util.Optional;
import java.util.Random;

import brickbreaker.common.Difficulty;
import brickbreaker.common.Mode;
import brickbreaker.model.factory.NullMapName;
import brickbreaker.model.factory.WorldFactory;
import brickbreaker.model.rank.Rank;
import brickbreaker.model.world.World;

public class EndlessModel extends AbstractGameModel {

    private Optional<Difficulty> chosenDiff;

    public EndlessModel(final Rank r) {
        super(Mode.ENDLESS, r);
    }

    private World getVariousDiffWorld() {
        Random randomDiff = new Random();
        Integer i = randomDiff.nextInt(3);
        return WorldFactory.getInstance().createFromDifficulty(new NullMapName(), Difficulty.values()[i]);
    }

    @Override
    public boolean getNextMatch() {
        World newWorld;
        
        if (this.chosenDiff.isEmpty()) {
            newWorld = this.getVariousDiffWorld();
        } else {
            Difficulty d = this.chosenDiff.get();
            newWorld = WorldFactory.getInstance().createFromDifficulty(new NullMapName(), d);
        }

        this.getGameState().setWorld(newWorld);

        return true;
    }
    
}
