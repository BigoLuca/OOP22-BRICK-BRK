package brickbreaker.model.state;

import brickbreaker.model.rank.PlayerStats;
import brickbreaker.model.timer.Timer;
import brickbreaker.model.timer.TimerImpl;
import brickbreaker.model.timer.TimerThread;
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

    private PlayerStats userStats;
    private World currentWorld;
    private State state;
    private Timer gameTimer;
    private TimerThread gameTimerThread;

    public GameStateImpl(final World world, final PlayerStats currentUser) {
        this.currentWorld = world;
        this.userStats = currentUser;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public final void init() {

		this.userStats.setScore(0);
        this.gameTimer = new TimerImpl(GameStateImpl.TIME);
        this.gameTimerThread = new TimerThread(this.gameTimer);
        this.state = State.PLAYING;
    }

    public void setStats(final PlayerStats p) {
        this.userStats = p;
    }

    public PlayerStats getStats() {
        return this.userStats;
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
    public final int getScore() {
        return this.userStats.getScore();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public final void incScore(final Integer increment) {
        this.userStats.incScore(increment);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public final void decScore(final Integer decrement) {
        this.userStats.decScore(decrement);
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
