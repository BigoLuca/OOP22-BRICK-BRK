package brickbreaker.model.state;

import brickbreaker.model.state.GameStateImpl.State;
import brickbreaker.model.state.timer.Timer;
import brickbreaker.model.state.timer.TimerThread;
import brickbreaker.model.world.World;

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
     */
    void init();
    
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
}
