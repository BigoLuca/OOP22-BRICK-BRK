package brickbreaker.controllers;

import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import brickbreaker.common.Difficulty;
import brickbreaker.common.State;
import brickbreaker.common.Vector2D;
import brickbreaker.controllers.state.LevelControllerImpl;
import brickbreaker.model.Level;
import brickbreaker.model.rank.GameRank;
import brickbreaker.model.rank.Rank;
import brickbreaker.model.user.User;
import brickbreaker.view.GameScene;

public class Controller extends ModelController {

    private GameScene loopScene;
    private boolean haveWin;

    private User user = new User("Pippo");

    public Controller(final GameScene gameWindow) {
        super();
        this.loopScene = gameWindow;
    }

    public void setUser(final String username) {
        this.user = this.getUserController().getUser(username);
    }

    public void createEndless(final Difficulty d) {
        Rank r = new GameRank("endless.json");
        final Integer maxIteration = 10;
        Integer totalScore = 0;

        do {
            haveWin = false;
            Integer it = 0;
            Level level = null;
            do {
                this.getErrorListener().getErrorList().clear();
                level = this.getModel().getRandomLevel(Optional.of(d));
                it++;
            } while (this.getErrorListener().getErrorPresent() && it < maxIteration);

            if (it < maxIteration) {
                LevelControllerImpl levCon = new LevelControllerImpl(level);
                this.setLevelController(levCon);
                loopScene.init();
                this.getLevelController().gameLoop();
                totalScore += levCon.getScore();
                if(levCon.getLevel().getState().equals(State.WIN)) {
                    haveWin = true;
                } else if (levCon.getLevel().getState().equals(State.LOST)){
                    r.addRank(user.getName(), totalScore);
                }
            } else {
                // mostra errore caricamento
            }
        } while (haveWin);
    }

    public synchronized void createLevel(final Integer id) {
        //lock.lock();
        Rank r = new GameRank("levels.json");
        modelController.getErrorListener().getErrorList().clear();
        Level level = modelController.getModel().getLevel(id);

        if(!modelController.getErrorListener().getErrorPresent()){
            LevelControllerImpl levCon = new LevelControllerImpl(level);
            modelController.setLevelController(levCon);
            gameLoop = new Thread(levCon);

            /*
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            */

            gameLoop.start();
            // this.viewController.showLevel(level).show(); + while fino a quando non clicca play + ritorna l'angolo della pallina
            try {
                // Main thread wait the execution of "gameLoop" thread
                gameLoop.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(levCon.getState().equals(State.WIN)) { 
                r.addRank(
                    user.getName(),
                    r.getPlayerScore(user.getName()) + levCon.getScore());
                //set level score
            }
        } else {
            // mostra errore caricamento
        }
    }
}
