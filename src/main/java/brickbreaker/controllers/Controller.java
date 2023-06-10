package brickbreaker.controllers;

import java.util.Optional;

import brickbreaker.common.State;
import brickbreaker.model.Level;
import brickbreaker.model.user.User;
import brickbreaker.model.world.World;
import brickbreaker.view.ViewSwitcher;

public class Controller extends AbstractController {

    private static final int ELAPSED = 200;

    private final GameController gameController;
    private Level model;
    private boolean endless = false;
    private User user;

    public Controller() {
        super();
        this.gameController = new GameController(this);
        this.model = null;
        this.user = null;
    }

    public void setUser(final String username) {
        this.user = this.userController.getUser(username);
    }

    public void setModel(Optional<Integer> level) {
        if(level.isEmpty()){
            this.endless = true;
        }
        this.model = this.levelController.getLevel(level);
    }

    public Level getModel() {
        return this.model;
    }

     /**
     * This method processes all the commands triggered by the user.
     */
    protected void processCommands() {
        World w = this.model.getWorld();
        w.getBar().updateInput(this.inputController, w.getMainBBox().getBRCorner().getX());
    }

    /**
     * This method updates the current Game.
     * @param elapsed
     */
    protected void updateGame() {
        this.model.updateGame(ELAPSED);
        this.model.getWorld().checkCollision();
        if (this.getModel().getState().equals(State.LOST)) {
            this.stop();
        } else if (this.getModel().getState().equals(State.WIN)) {
            if(this.endless){
                this.model = this.levelController.getLevel(Optional.empty());
            } else {
                this.stop();
            }
        }
    }

    public void play() {
        gameController.startGame();
    }

    public void render() {
        ViewSwitcher.getInstance().render();
    }

    public void stop() {
        this.gameController.stopGame();
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
