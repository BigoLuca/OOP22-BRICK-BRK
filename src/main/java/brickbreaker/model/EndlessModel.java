package brickbreaker.model;

import java.util.Random;

import brickbreaker.common.Mode;
import brickbreaker.model.rank.GameRank;
import brickbreaker.model.user.User;

public class EndlessModel extends AbstractGameModel {

    //private Optional<Difficulty> chosenDiff;

    public EndlessModel(final User user) {
        super(Mode.ENDLESS, new GameRank(LENRANK, "endless.json"), user);
        //this.chosenDiff = chosenDiffToSet;
    }

    /*
    private World getVariousDiffWorld() {
        Random randomDiff = new Random();
        Integer i = randomDiff.nextInt(3);
        return WorldFactory.getInstance().createFromDifficulty(new NullMapName(), Difficulty.values()[i]);
    }
    */

    public Level getNextMatch() {

        Random randomDiff = new Random();
        Integer diff = randomDiff.nextInt(90 - 10 + 1) + 10; // random between [10,90]
        Integer map = randomDiff.nextInt(this.getListMapLenght());
        
        return new Level(0, this.getNameMap(map), diff);
    }
    
}
