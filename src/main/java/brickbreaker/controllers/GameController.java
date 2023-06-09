package brickbreaker.controllers;

import javax.swing.text.View;

import brickbreaker.common.Chronometer;
import brickbreaker.view.ViewController;
import javafx.animation.AnimationTimer;

public class GameController extends AnimationTimer {

    private final Controller controller;
    private boolean isRunning;
    private final Chronometer chrono;

    public GameController(final Controller controller) {
        this.controller = controller;
        this.isRunning = false;
        this.chrono = new Chronometer();
        chrono.start();
    }

    public void startGame() {
        this.isRunning = true;
        this.start();
        chrono.resumeChrono();
    }

    public void pauseGame() {
        this.isRunning = false;
        this.stop();
        chrono.pauseChrono();
    }

    public void stopGame() {
        this.isRunning = false;
        this.stop();
        chrono.stopChrono();
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    @Override
    public void handle(long now) {
        this.controller.processCommands();
        this.controller.updateGame();
        ViewController.getInstance().render();
    }

}
