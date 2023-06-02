package brickbreaker.model;

import brickbreaker.common.State;
import brickbreaker.common.Vector2D;
import brickbreaker.model.factory.GameFactory;
import brickbreaker.model.world.World;
import brickbreaker.model.world.gameObjects.Ball;

/**
 * Class representing a single level.
 */
public class Level {

    private final Integer id;
    private World world;
    private State state;
    private Integer score;

    /**
     * Level constructor.
     * @param id
     * @param nameMap
     */
    public Level(final Integer id, final World w) {
        this.id = id;
        this.world = w;
        this.score = 0;
        this.state = State.PAUSE;
    }

    /**
     * @return the level number
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * This method returns the current game world.
     * @return A World object
     */
    public World getWorld() {
        return this.world;
    }

    /*
     * This method updates all world objects of a frame.
     */
    public void updateGame(final Integer elapsed) {
        this.world.updateGame(elapsed);
    }

    /**
     * This method returns the current state.
     * @return A State enum object
     */
    public State getState() {
        if (this.world.getBar().getLife() <= 0) {
            this.state = State.LOST;
        } else if (this.world.getBalls().size() <= 0) {
            
            //set the new ball il the center of bar
            Double h = this.world.getBar().getHeight() + Ball.RADIUS / 2;
            Vector2D barPos = this.world.getBar().getPosition();
            this.world.addBall(GameFactory.getInstance().createBall(
                new Vector2D(barPos.getX(), barPos.getY() + h), 
                new Vector2D(0, 0)));
            this.state = State.WAIT;
        } else if (this.getWorld().getBricks().size() == 0) {
            this.state = State.WIN;
        }
        return this.state;
    }



    /**
     * This method sets the state passed.
     * @param s
     */
    public void setState(final State s) {
        this.state = s;
    }

    /**
     * This method gets the current points scored by the user.
     * @return An integer value.
     */
    public final int getScore() {
        return this.score;
    }

    /**
     * This method increments the current score by the value
     * specified by the increment parameter.
     * @param increment an integer value which is the increment.
     */
    public final void incScore(final Integer increment) {
        this.score += increment;
    }

    /**
     * This method decrements the current score by the value
     * specified by the decrement parameter.
     * @param decrement an integer value which is the decrement value.
     */
    public final void decScore(final Integer decrement) {
        this.score -= decrement;
    }
}
