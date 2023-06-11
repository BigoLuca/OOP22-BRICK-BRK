package brickbreaker.controllers;

import brickbreaker.common.Chronometer;
import brickbreaker.common.Mode;
import brickbreaker.common.State;
import brickbreaker.model.Level;
import brickbreaker.model.user.User;
import brickbreaker.model.world.World;
import brickbreaker.view.GameView;

public class Controller extends AbstractController {

    private static final Double ELAPSED = 200.0;

    private final GameController gameController;
    private GameView gameView;
    private Level model;
    private Mode mode;
    private User user;

    private final Chronometer chrono;
    private Integer oldScore;

    public Controller() {
        super();
        this.gameController = new GameController(this);
        this.model = null;
        this.user = null;
        this.oldScore = 0;
        this.chrono = new Chronometer();
        chrono.start();
    }

    public void setMode (final Mode mode) {
        this.mode = mode;
    }
    

    public void setUser(final String username) {
        this.user = this.userController.getUser(username);
    }

    public void setGameView(final GameView gameView) {
        this.gameView = gameView;
    }

    public void setModel() {
        this.model = this.levelController.getLevel();
    }

    public Level getModel() {
        return this.model;
    }

     /**
     * This method processes all the commands triggered by the user.
     */
    protected void processCommands() {
        World w = this.model.getWorld();
        w.getBar().updateInput(ELAPSED, this.inputController, w.getMainBBox().getBRCorner().getX());
    }

    /**
     * This method updates the current Game.
     * @param elapsed
     */
    protected void updateGame() {
        this.model.updateGame(ELAPSED.intValue());
        this.model.getWorld().checkCollision();
        this.checkState();
    }

    private void checkState() {
        State s = this.getModel().getState();
        if (s.equals(State.WAIT)) {
            this.pause();
            this.render();
        } else if (this.getModel().getState().equals(State.LOST)) {
            this.stop();
            this.rankController.addRank(mode, this.model.getId(), user.getName(), (int) ((oldScore + this.model.getWorld().getScore()) / this.chrono.getElepsedTime()));
        } else if (this.getModel().getState().equals(State.WIN)) {
            if(this.mode.equals(Mode.ENDLESS)){
                this.pause();
                Integer barLife = this.model.getWorld().getBar().getLife();
                this.oldScore += this.model.getWorld().getScore();
                this.model = this.levelController.getLevel();
                this.model.getWorld().incScore(oldScore);
                this.model.getWorld().getBar().setLife(barLife);
                this.render();
            }
        }
    }

    public void play() {
        this.model.setState(State.PLAYING);
        chrono.resumeChrono();
        gameController.startGame();
    }

    public void pause() {
        this.model.setState(State.WAIT);
        chrono.pauseChrono();
        gameController.pauseGame();
    }

    public void toggle() {{}
        if (this.getModel().getState().equals(State.PLAYING)) {
            this.pause();
        } else {
            this.play();
        }
    }

    public void stop() {
        chrono.stopChrono();
        this.gameController.stopGame();
        this.gameView.isOver();
    }
    
    public void render() {
        gameView.render();
    }
}
