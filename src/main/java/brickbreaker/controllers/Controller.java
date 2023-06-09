package brickbreaker.controllers;

import brickbreaker.model.Level;
import brickbreaker.model.user.User;
import brickbreaker.model.world.World;
import brickbreaker.view.ViewController;

public class Controller extends AbstractController {

    private static final int ELAPSED = 16;

    private final GameController gameController;
    private Level model;
    private ViewController view;
    private User user;

    public Controller(final ViewController v) {
        super();
        this.gameController = new GameController(this);
        this.model = null;
        this.user = null;
        this.view = v;
    }

    public void setUser(final String username) {
        this.user = this.userController.getUser(username);
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
    }

    public void playLevel(final Integer id) {
        this.errListener.getErrorList().clear();
        this.model = this.levelController.getLevel(id);
        if(errListener.getErrorPresent()){
            // mostra errore caricamento
        } else {
            gameController.startGame();
        }

    }

    public void render() {
        this.view.render();
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
