package brickbreaker.controllers;

import brickbreaker.model.world.World;
import brickbreaker.model.world.gameObjects.Ball;
import brickbreaker.model.world.gameObjects.Bar;
import brickbreaker.model.world.gameObjects.Brick;
import brickbreaker.model.Level;
import brickbreaker.view.GameView;

import java.util.List;

import brickbreaker.common.State;
import brickbreaker.controllers.input.InputController;


/**
 * Implements the {@link GamStateController} interface.
 */
public class LevelControllerImpl extends GenericController implements LevelController, Runnable {

    private static final double PERIOD = 16.6666;

    private InputController inputController;
    private boolean quit;
    private Thread game;

    public void init() {
        this.game = new Thread(this);
        this.game.setName("game");
        this.game.start();

        this.inputController = new InputController();
        this.getModel().getLevel().setState(State.PLAYING);
    }

    /**
     * {@inheritDoc}
     */
    public Level getLevel() {
        return this.getModel().getLevel();
    }

    public List<Brick> getBricks() {
        return this.getModel().getLevel().getWorld().getBricks();
    }

    public Ball getBall() {
        return this.getModel().getLevel().getWorld().getBalls().get(0);
    }

    public Bar getBar() {
        return this.getModel().getLevel().getWorld().getBar();
    }
    
    /**
     * {@inheritDoc}
     */
    public InputController getInputController() {
        return this.inputController;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public Integer getScore() {
        return this.getModel().getLevel().getWorld().getScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        this.quit = false;
        long last = System.currentTimeMillis();

        while(!quit){
            
            switch (this.getModel().getLevel().getState()) {
                case PLAYING:
                    long current = System.currentTimeMillis();
                    int elapsed = (int) (current - last);
                    this.processCommands();
                    this.updateGame(elapsed);
                    this.render();
                    this.waitUntilNextFrame(current);
                    last = current;
                    break;
                case WAIT:
                case PAUSE:
                    break;
                default:
                    this.quit = true;
                    break;
            }
        }
    }

    /**
     * This method processes all the commands triggered by the user.
     */
    private void processCommands() {
        World w = this.getModel().getLevel().getWorld();
        w.getBar().updateInput(inputController, w.getMainBBox().getBRCorner().getX());
    }

    /**
     * This method updates the current Game.
     * @param elapsed
     */
    private void updateGame(final int elapsed) {
        this.getModel().getLevel().updateGame(elapsed);
        this.getModel().getLevel().getWorld().checkCollision();
    }

    /**
     * This method renders the attached view.
     */
    private void render() {
        ((GameView) this.getView()).render();
    }

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
