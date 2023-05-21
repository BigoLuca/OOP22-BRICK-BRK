package brickbreaker.model.state;

import brickbreaker.model.state.timer.Timer;
import brickbreaker.model.state.timer.TimerImpl;
import brickbreaker.model.state.timer.TimerThread;
import brickbreaker.model.world.World;

/**
 * {@inheritDoc}
 * Implements the {@link GameState} interface.
 * 
 * @author Bighini Luca
 * @author Agostinelli Francesco
 */
public class GameStateImpl implements GameState {

    private static final Integer TIME = 300;

    /** Rapresent the current state of the game. */
    public enum State { PLAYING, WIN, LOST }

    private World currentWorld;
    private State state;
    private Timer gameTimer;
    private TimerThread gameTimerThread;

    public GameStateImpl(final World world) {
        this.currentWorld = world;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public final void init() {

        this.gameTimer = new TimerImpl(GameStateImpl.TIME);
        this.gameTimerThread = new TimerThread(this.gameTimer);
        this.state = State.PLAYING;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public final World getWorld() {
        return this.currentWorld;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public final void setWorld(final World newGameWorld) {
        this.currentWorld = newGameWorld;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public final void updateGame(final Integer elapsed) {
        this.currentWorld.updateGame(elapsed);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public State getState() {
        if (gameTimer.getTime().getTotal() == 0 || this.getWorld().getBar().getLife() <= 0) {
            this.state = State.LOST;
        } else if (this.getWorld().getBricks().size() == 0) {
            this.state = State.WIN;
        }
        return this.state;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public TimerThread getGameTimerThread() {
        return this.gameTimerThread;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public Timer getGameTimer() {
        return this.gameTimer;
    }
}
