package brickbreaker.controllers;

import javafx.animation.AnimationTimer;

public class GameController extends AnimationTimer {

    private final Controller controller;

    public GameController(final Controller controller) {
        this.controller = controller;
    }

    public void startGame() {
        this.start();
    }

    public void pauseGame() {
        this.stop();
    }

    public void stopGame() {
        this.stop();
    }

    @Override
    public void handle(long now) {
        this.controller.processCommands();
        this.controller.updateGame();
        this.controller.render();
    }

}
