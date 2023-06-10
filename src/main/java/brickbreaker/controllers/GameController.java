package brickbreaker.controllers;

import brickbreaker.common.Chronometer;
import javafx.animation.AnimationTimer;

public class GameController extends AnimationTimer {

    private final Controller controller;
    //private final Chronometer chrono;

    public GameController(final Controller controller) {
        this.controller = controller;
        //this.chrono = new Chronometer();
        //chrono.start();
    }

    public void startGame() {
        this.start();
        //chrono.resumeChrono();
    }

    public void pauseGame() {
        this.stop();
        //chrono.pauseChrono();
    }

    public void stopGame() {
        this.stop();
        //chrono.stopChrono();
    }

    @Override
    public void handle(long now) {
        this.controller.processCommands();
        this.controller.updateGame();
        this.controller.render();
    }

}
