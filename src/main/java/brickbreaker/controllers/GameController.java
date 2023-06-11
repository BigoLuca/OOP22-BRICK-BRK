package brickbreaker.controllers;

import brickbreaker.common.State;
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
        State s = this.controller.getModel().getState();
        if (s.equals(State.PLAYING)) {
            this.controller.render();
        }
    }

}
