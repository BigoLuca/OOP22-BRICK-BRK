package brickbreaker.model;

import java.util.List;

import brickbreaker.model.GameStateImpl.State;
import brickbreaker.model.timer.Timer;
import brickbreaker.model.timer.TimerThread;

/**
 * Interface to model the Game State. It defines the state of the game.
 * Initialized the world, keeps track of the score of the game and checkout the endgame.
 * 
 * @author Bighini Luca
 * @author Agostinelli Francesco
 */
public interface GameState {

    /**
     * Initialize the game world and data.
     * @param nameMap
     * @param level
     */
    void init(String nameMap, Integer level);

    /**
     * This method returns the current game world.
     * @return A World object.
     */
    World getWorld();

    /**
     * This method sets the current game state world.
     * @param newGameWorld A World object which will be the new game world.
     */
    void setWorld(World newGameWorld);

    /**
     * This method gets the current points scored by the user.
     * @return An integer value.
     */
    int getScore();

    /**
     * This method increments the current score by the value
     * specified by the increment parameter.
     * @param increment an integer value which is the increment.
     */
    void incScore(Integer increment);

    /**
     * This method decrements the current score by the value
     * specified by the decrement parameter.
     * @param decrement an integer value which is the decrement value.
     */
    void decScore(Integer decrement);

    /**
     * {@inheritDoc}}
     */
    void updateGame(Integer elapsed);

    /**
     * This method returns the current state.
     * @return A State enum object
     */
    State getState();

    /**
     * This method returns the game timer thread.
     * @return a TimerThread object which is the current game state timer thread.
     */
    TimerThread getGameTimerThread();

    /**
     * This method returns the game timer.
     * @return a Timer object which is the current game timer, that contains the remaining time.
     */
    Timer getGameTimer();

    /**
     * @return a list of maps file names 
     */
    List<String> getNamesMap();
}
