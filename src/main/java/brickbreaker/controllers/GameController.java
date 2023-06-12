package brickbreaker.controllers;

import javafx.animation.AnimationTimer;

/**
 * Animation timer class for the loop.
 */
public class GameController extends AnimationTimer {

    private final Controller controller;

    /**
     * GameController constructor.
     * @param controller
     */
    public GameController(final Controller controller) {
        this.controller = controller;
    }

    /**
     * Starts the animation timer.
     */
    public void startGame() {
        this.start();
    }

    /**
     * Paused the animation timer.
     */
    public void pauseGame() {
        this.stop();
    }

    /**
     * Stops the animation timer.
     */
    public void stopGame() {
        this.stop();
    }

    /**
     * Game loop.
     * {@inheritDoc}
     */
    @Override
    public void handle(final long now) {
        this.controller.processCommands();
        this.controller.updateGame();
        this.controller.render();
    }

}
