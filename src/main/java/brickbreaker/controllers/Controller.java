package brickbreaker.controllers;

import brickbreaker.common.Chronometer;
import brickbreaker.common.Mode;
import brickbreaker.common.State;
import brickbreaker.model.Level;
import brickbreaker.model.user.User;
import brickbreaker.model.world.World;
import brickbreaker.view.GameView;

/**
 * The application controller.
 */
public class Controller extends AbstractController {

    private final Double ELAPSED = 200.0;
    private final Integer DEC_SCORE_TIMER = 2;

    private final GameController gameController;
    private GameView gameView;
    private Level model;
    private Mode mode;
    private User user;

    private final Chronometer chrono;
    private Integer oldScore;

    /**
     * Controller constructor.
     */
    public Controller() {
        super();
        this.gameController = new GameController(this);
        this.model = null;
        this.user = null;
        this.oldScore = 0;
        this.chrono = new Chronometer();
    }

    /**
     * Method to set the mode after the user input.
     * @param mode
     */
    public void setMode(final Mode mode) {
        this.mode = mode;
    }

    /**
     * Method to set the user.
     * @param username
     */
    public void setUser(final String username) {
        this.user = this.getUserController().getUser(username);
    }

    /**
     * Method to set the GameView.
     * @param gameView
     */
    public void setGameView(final GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Method to set the model.
     */
    public void setModel() {
        this.model = this.getLevelController().getLevel();
    }

    /**
     * Methdo to get the model.
     * @return an instance of the model.
     */
    public Level getModel() {
        return this.model;
    }

     /**
     * This method processes all the commands triggered by the user.
     */
    protected void processCommands() {
        World w = this.model.getWorld();
        w.getBar().updateInput(ELAPSED, this.getInputController(), w.getMainBBox().getBRCorner().getX());
    }

    /**
     * This method updates the current Game.
     */
    protected void updateGame() {
        this.model.updateGame(ELAPSED.intValue());
        this.model.getWorld().checkCollision();
        this.checkState();
    }

    /**
     * This method check the state after the update.
     */
    private void checkState() {
        State s = this.getModel().getState();
        if (s.equals(State.WAIT)) {
            this.pause();
            this.render();
        } else if (this.getModel().getState().equals(State.LOST)) {
            this.stop();
            this.getRankController().addRank(
                mode, this.model.getId(), user.getName(), this.getScore());
        } else if (this.getModel().getState().equals(State.WIN)) {
            if (this.mode.equals(Mode.ENDLESS)) {
                this.pause();
                Integer barLife = this.model.getWorld().getBar().getLife();
                this.oldScore += this.model.getWorld().getScore();
                this.model = this.getLevelController().getLevel();
                this.model.getWorld().incScore(oldScore);
                this.model.getWorld().getBar().setLife(barLife);
                this.render();
            }
        }
    }

    public Integer getScore() {
        return Math.max(0, this.model.getWorld().getScore() - DEC_SCORE_TIMER * this.chrono.getElepsedTime());
    }

    /**
     * Method to start or resume the game.
     */
    private void play() {
        this.model.setState(State.PLAYING);
         chrono.startChrono();
        gameController.startGame();
    }

    /**
     * Method to pause the game.
     */
    private void pause() {
        this.model.setState(State.WAIT);
        chrono.pauseChrono();
        gameController.pauseGame();
    }

    /**
     * Method to start and pause the game.
     */
    public void toggle() {
        if (this.getModel().getState().equals(State.PLAYING)) {
            this.pause();
        } else {
            this.play();
        }
    }

    /**
     * Method to stop the game.
     */
    public void stop() {
        chrono.stopChrono();
        this.gameController.stopGame();
        this.gameView.isOver();
    }

    /**
     * Method to render the world evrey frames.
     */
    public void render() {
        gameView.render(this.getScore().toString());
    }
}
