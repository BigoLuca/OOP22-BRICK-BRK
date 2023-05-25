package brickbreaker.controllers.state;

import brickbreaker.model.world.World;
import brickbreaker.model.Level;
import brickbreaker.model.rank.PlayerStats;
import brickbreaker.controllers.ControllerImpl;
import brickbreaker.model.state.GameStateImpl.State;
import brickbreaker.controllers.input.InputController;
import brickbreaker.controllers.state.event.WorldEventListener;
import brickbreaker.controllers.state.event.WorldEventListenerImpl;


/**
 * Implements the {@link GamStateController} interface.
 * 
 * @author Agostinelli Francesco
 */
public class LevelControllerImpl extends ControllerImpl implements LevelController, Runnable {

    private static final double PERIOD = 16.6666;

    private WorldEventListener eventListener;
    private InputController inputController;
    private boolean pause;
    private boolean quit;
    private Thread game;
    private Level level;

    /**
     * Game state controller constructor.
     */
    public LevelControllerImpl(final Level l) {
        this.pause = false;
        this.quit = false;
        this.game = new Thread(this);
        this.game.setName("GameLoop");
        this.level = l;
    }

    public LevelControllerImpl() {
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void init() {
        this.eventListener = new WorldEventListenerImpl();
        this.eventListener.setGameState(this.level.getGameState());
        this.level.getGameState().init();
        this.level.getGameState().getWorld().setEventListener(this.eventListener);
        this.game.start();
        this.level.getGameState().getGameTimerThread().start();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void quitGame() {
        this.quit = true;
        synchronized(game) {
            this.game.notify();
        }
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void pauseGame() {
        this.pause = true;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void resumeGame() throws InterruptedException {
        this.pause = false;
        synchronized(game) {
            this.game.notify();
        }
        this.level.getGameState().getGameTimerThread().resumeTimer();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public Integer getScore() {
        return this.level.getScore();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public State getState() {
        return this.level.getGameState().getState();
    }

    /**
     * {@inheritDoc}
     * Contains the loop for each game.
     */
    @Override
    public void run() {
        long last = System.currentTimeMillis();

        while(!quit){
            
            while(this.level.getGameState().getState().equals(State.PLAYING) && !this.pause) {
                long current = System.currentTimeMillis();
                int elapsed = (int) (current - last);
                this.processCommands();
                this.updateGame(elapsed);
                this.render();
                this.waitUntilNextFrame(current);
                last = current;
            }

            if (!this.level.getGameState().getState().equals(State.PLAYING)) {
                this.getModel().getRank().addPlayer(new PlayerStats(this.getModel().getUser().getName(), this.getScore()));
                quitGame();
            } else if (this.pause) {

                synchronized(game){
                    try {
                        System.out.println("Game in pause...");
                        this.level.getGameState().getGameTimerThread().stopTimer();
                        this.game.wait();
                        System.out.println("Resume game event...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        try {
            this.game.interrupt();
            throw new InterruptedException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method processes all the commands triggered by the user.
     */
    private void processCommands() {
        World w = this.level.getGameState().getWorld();
        w.getBar().updateInput(inputController, w.getMainBBox().getBRCorner().getX());
    }

    /**
     * This method updates the current Game.
     * @param elapsed
     */
    private void updateGame(final int elapsed) {
        this.level.getGameState().updateGame(elapsed);
        this.level.getGameState().getWorld().checkCollision();
        this.eventListener.processAll();
    }

    /**
     * This method renders the attached view.
     */
    private void render() {}

    /**
     * This method wait end of the frame time before strting a new cicle.
     * @param currentFrame
     */
    private void waitUntilNextFrame(final long currentFrame) {
        long dt = System.currentTimeMillis() - currentFrame;
        if (dt < LevelControllerImpl.PERIOD) {
            try {
                Thread.sleep((long) PERIOD - dt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
