package brickbreaker.controllers;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import brickbreaker.common.State;
import brickbreaker.controllers.state.LevelControllerImpl;
import brickbreaker.model.Level;
import brickbreaker.model.rank.GameRank;
import brickbreaker.model.rank.Rank;
import brickbreaker.model.user.User;

public class Controller {

    private ModelController modelController;
    //private ViewController viewController;
    private boolean haveWin;
    private Thread gameLoop;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private User user = new User("Pippo");

    public Controller() {
        this.modelController = new ModelController();
    }

    public void setUser(final String username) {
        this.user = this.modelController.getUserController().getUser(username);
    }

    public void createEndless() {
        lock.lock();
        Rank r = new GameRank("endless.json");
        final Integer maxIteration = 10;
        Integer totalScore = 0;

        do {
            haveWin = false;
            Integer it = 0;
            Level level = null;
            do {
                modelController.getErrorListener().getErrorList().clear();
                level = modelController.getModel().getRandomLevel();
                it++;
            } while (modelController.getErrorListener().getErrorPresent() && it < maxIteration);

            if (it < maxIteration) {
                LevelControllerImpl levCon = new LevelControllerImpl(level);
                modelController.setLevelController(levCon);
                gameLoop = new Thread(levCon);

                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                //dai il comando alla view:  
                // this.viewController.showLevel(level).show(); + while fino a quando non clicca play + ritorna l'angolo della pallina
                System.out.println("parto con il GameLoop");
                
                gameLoop.start();
                try {
                    // Main thread wait the execution of "gameLoop" thread
                    gameLoop.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                totalScore += levCon.getScore();
                if(levCon.getState().equals(State.WIN)) {
                    haveWin = true;
                }
            } else {
                // mostra errore caricamento
            }
        } while (haveWin);
        r.addRank(user.getName(), totalScore);
    }

    public synchronized void createLevel(final Integer id) {
        lock.lock();
        Rank r = new GameRank("levels.json");
        modelController.getErrorListener().getErrorList().clear();
        Level level = modelController.getModel().getLevel(id);

        if(!modelController.getErrorListener().getErrorPresent()){
            LevelControllerImpl levCon = new LevelControllerImpl(level);
            modelController.setLevelController(levCon);
            gameLoop = new Thread(levCon);

            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            // this.viewController.showLevel(level).show(); + while fino a quando non clicca play + ritorna l'angolo della pallina
            
            gameLoop.start();
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

    public void play() {
        lock.lock();
        try {
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void pause() {
        modelController.getLevelController().pauseGame();
    }

    public void resume() throws InterruptedException {
        modelController.getLevelController().resumeGame();
    }

    public void quit() {
        modelController.getLevelController().quitGame();
    }
}
