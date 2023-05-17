package brickbreaker.controllers.state;

import brickbreaker.ResourceLoader;
import brickbreaker.model.world.World;
import brickbreaker.model.rank.GameRank;
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
public class GameStateControllerImpl extends ControllerImpl implements GameStateController, Runnable {

    private static final double PERIOD = 16.6666;

    private WorldEventListener eventListener;
    private InputController inputController;
    private boolean pause;
    private boolean quit;
    private Thread game;

    /**
     * Game state controller constructor.
     */
    public GameStateControllerImpl() {
        this.pause = false;
        this.quit = false;
        this.game = new Thread(this);
        this.game.setName("GameLoop");
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void quitGame() {
        this.getModel().getGameState().getGameTimerThread().stopTimer();
        this.quit = true;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void pauseGame() {
        this.pause = !this.pause;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void init() {
        this.eventListener = new WorldEventListenerImpl();
        this.eventListener.setGameState(getModel().getGameState());
        this.getModel().getGameState().init();
        this.getModel().getGameState().getWorld().setEventListener(this.eventListener);
        this.game.start();
        this.getModel().getGameState().getGameTimerThread().start();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public int getScore() {
        return this.getModel().getGameState().getScore();
    }

    /**
     * {@inheritDoc}
     * Contains the loop for each game.
     */
    @Override
    public void run() {
        long last = System.currentTimeMillis();

        while(!quit){
            
            while(this.getModel().getGameState().getState() == State.PLAYING && !this.pause) {
                long current = System.currentTimeMillis();
                int elapsed = (int) (current - last);
                this.processCommands();
                this.updateGame(elapsed);
                this.render();
                this.waitUntilNextFrame(current);
                last = current;
            }

            State gameState = this.getModel().getGameState().getState();
            Boolean next = false;

            if (gameState.equals(State.WIN)) {
                next = this.getModel().getNextMatch();
            }

            if(gameState.equals(State.LOST) || !next) {
               this.quitGame();
            }

            //TODO:fix
            //Adds the player to rank.
            GameRank r = this.getModel().getRank();
            r.addPlayer(this.getModel().getGameState().getStats());
            ResourceLoader.getInstance().writeRank(r.getRank(), this.getModel().getMode());

            //TODO: Discuss about game being a critical variable and check if the code is correct.
            if(this.pause){

                synchronized(game){
                    try {
                        System.out.println("Game in pause...");
                        this.game.wait();
                        this.getModel().getGameState().getGameTimerThread().stopTimer();
                        System.out.println("Resume game event...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    Thread.sleep(5000);
                    synchronized(game) {
                        this.game.notify();
                    }
                    this.getModel().getGameState().getGameTimerThread().resumeTimer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
        World w = this.getModel().getGameState().getWorld();
        w.getBar().updateInput(inputController, w.getMainBBox().getBRCorner().getX());
    }

    /**
     * This method updates the current Game.
     * @param elapsed
     */
    private void updateGame(final int elapsed) {
        this.getModel().getGameState().updateGame(elapsed);
        this.processEvents();
        this.getModel().getGameState().getWorld().checkCollision();
        this.processEvents();
        // PERCHE SI RICHIAMA DUE VOLTE IL PROCESSEVENT
    }

    /**
     * This method processes all the world events.
     */
    void processEvents() {
        this.eventListener.processAll();
    }

    /**
     * This method renders the attached view.
     */
    private void render() { }

    /**
     * This method wait end of the frame time before strting a new cicle.
     * @param currentFrame
     */
    private void waitUntilNextFrame(final long currentFrame) {
        long dt = System.currentTimeMillis() - currentFrame;
        if (dt < GameStateControllerImpl.PERIOD) {
            try {
                Thread.sleep((long) PERIOD - dt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
