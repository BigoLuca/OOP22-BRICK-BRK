package brickbreaker.common;

public class Chronometer implements Runnable {
    private long startTime;
    private long pausedTime;
    private boolean isRunning;
    private boolean isStopped;

    public Chronometer() {
        startTime = 0;
        pausedTime = 0;
        isRunning = false;
        isStopped = false;
    }

    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            isRunning = true;
            isStopped = false;
        }
    }

    public void pause() {
        if (isRunning) {
            pausedTime = System.currentTimeMillis();
            isRunning = false;
        }
    }

    public void resume() {
        if (!isRunning && !isStopped) {
            long currentTime = System.currentTimeMillis();
            startTime += currentTime - pausedTime;
            pausedTime = 0;
            isRunning = true;
        }
    }

    public long stop() {
        if (isRunning || !isStopped) {
            long tempoTrascorso = getTimeElapsed() / 1000;
            isRunning = false;
            isStopped = true;
            //System.out.println("Cronometro interrotto." + tempoTrascorso);
            return tempoTrascorso;
        }
        return 1;
    }

    public long getTimeElapsed() {
        long currentTime = System.currentTimeMillis();
        if (isRunning) {
            return currentTime - startTime;
        } else {
            return pausedTime - startTime;
        }
    }

    @Override
    public void run() {
        while (!isStopped) {
            try {
                Thread.sleep(1000); // Aggiorna il tempo ogni secondo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
