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
        if (this.getModel().getState().equals(State.LOST)) {
            this.stop();
            //this.rankController.addRank(mode, this.model.getId(), user.getName(), (int) ((oldScore + this.model.getWorld().getScore()) / this.chrono.getElepsedTime()));
        } else if (this.getModel().getState().equals(State.WIN)) {
            //if(this.mode.equals(Mode.ENDLESS)){
                this.pause();
                this.oldScore += this.model.getWorld().getScore();
                this.model = this.levelController.getLevel();
                this.model.getWorld().incScore(oldScore);
                this.render();
            //} else {
                //this.stop();
            //}
        }
    }

     /**
     * This method checks if the game is over.
     */
    protected boolean isOver() {
        if (this.model.getState().equals(State.LOST)) {
            
        } else if (this.model.getState().equals(State.WIN)) {
            if(this.mode.equals(Mode.ENDLESS)){
                this.model = this.levelController.getLevel();
                // Errore nel model
                if(this.model == null){
                    System.out.println("Errore nel model");
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    protected void checkState() {
        if (this.isOver()) {
            this.stop();
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


    /*
    public void createEndless(final Difficulty d) {
        final Integer maxIteration = 10;
        Integer totalScore = 0;
        long playTime = 0;

        do {
            haveWin = false;
            Integer it = 0;
            Level level = null;
            do {
                this.getErrorListener().getErrorList().clear();
                level = this.getModel().getRandomLevel(d);
                it++;
            } while (this.getErrorListener().getErrorPresent() && it < maxIteration);

            if (it < maxIteration) {
                this.setLevelController(new LevelControllerImpl(level));
                loopScene.init(getLevelController().getLevel());
                playTime += this.getLevelController().gameLoop();
                totalScore += this.getLevelController().getScore();
                if(this.getLevelController().getLevel().getState().equals(State.WIN)) {
                    haveWin = true;
                } else if (this.getLevelController().getLevel().getState().equals(State.LOST) && this.user != null){
                    new GameRank("endless.json", d.ordinal()).addToRank(user.getName(), (int) (totalScore / playTime));
                }
            } else {
                // mostra errore caricamento
            }
        } while (haveWin);
    }

    public synchronized void createLevels(final Integer id) {
        long playTime = 0;
        this.getErrorListener().getErrorList().clear();
        Level level = this.getModel().getLevel(id);
        if (!this.getErrorListener().getErrorPresent()) {
            this.setLevelController(new LevelControllerImpl(level, loopScene));
            loopScene.init(getLevelController().getLevel());
            playTime = this.getLevelController().gameLoop();
            if(this.getLevelController().getLevel().getState().equals(State.WIN) && this.user != null) {
                new GameRank("levels.json", id).addToRank(user.getName(), (int) (this.getLevelController().getScore() / playTime));
                if (user.getLevelReached() == id) {
                    user.incLevelReached();
                }
            }
        } else {
            // mostra errore caricamento
        }
    } */
}
