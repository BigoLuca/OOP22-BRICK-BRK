package brickbreaker.model;

import java.util.Optional;
import java.util.Random;

import brickbreaker.common.Mode;

public class EndlessModel extends AbstractGameModel {

    //private Optional<Difficulty> chosenDiff;

    public EndlessModel() {
        super(Mode.ENDLESS);
        //this.chosenDiff = chosenDiffToSet;
    }

    /*
    private World getVariousDiffWorld() {
        Random randomDiff = new Random();
        Integer i = randomDiff.nextInt(3);
        return WorldFactory.getInstance().createFromDifficulty(new NullMapName(), Difficulty.values()[i]);
    }
    */

    @Override
    public Optional<Level> getNextMatch() {

        Random randomDiff = new Random();
        Integer diff = randomDiff.nextInt(90 - 10 + 1) + 10; // random between [10,90]
        Integer map = randomDiff.nextInt(this.getListMapLenght());
        
        return Optional.of(new Level(0, this.getNameMap(map), diff));
    }
    
}
