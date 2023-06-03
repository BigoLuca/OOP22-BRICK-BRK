package brickbreaker.controllers;

import brickbreaker.model.world.World;
import brickbreaker.model.Level;
import brickbreaker.controllers.input.InputController;


/**
 * Implements the {@link GamStateController} interface.
 */
public class LevelControllerImpl implements LevelController {

    private static final double PERIOD = 16.6666;

    private InputController inputController;
    private boolean quit;
    private Level level;

    /**
     * Game state controller constructor.
     */
    public LevelControllerImpl(final Level l) {
        this.level = l;
    }

    /**
     * {@inheritDoc}
     */
    public Level getLevel() {
        return this.level;
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
        return this.level.getWorld().getScore();
    }

    /**
     * {@inheritDoc}
     * Contains the loop for each game.
     */
    @Override
    public void gameLoop() {
        this.quit = false;
        long last = System.currentTimeMillis();

        while(!quit){
            
            switch (this.level.getState()) {
                case PLAYING:
                    long current = System.currentTimeMillis();
                    int elapsed = (int) (current - last);
                    this.processCommands();
                    this.updateGame(elapsed);
                    this.render();
                    this.waitUntilNextFrame(current);
                    last = current;
                    break;
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
        World w = this.level.getWorld();
        w.getBar().updateInput(inputController, w.getMainBBox().getBRCorner().getX());
    }

    /**
     * This method updates the current Game.
     * @param elapsed
     */
    private void updateGame(final int elapsed) {
        this.level.updateGame(elapsed);
        this.level.getWorld().checkCollision();
    }

    /**
     * This method renders the attached view.
     */
    private void render() {
        //this.getView().update(this.level.getGameState().getWorld());
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
