package brickbreaker.common;

/**
 * Class representing a chronometer.
 * Method: 
 */
public class Chronometer extends Thread {

    private long startTime;
    private long pausedTime;
    private boolean isRunning;
    private boolean isStopped;
    private double elapsedTime;

    /**
     * Chronometer constructor.
     */
    public Chronometer() {
        startTime = 0;
        pausedTime = 0;
        isRunning = false;
        isStopped = false;

    }

    /**
     * @return the time elapsed from the start in seconds
     */
    public double getElepsedTime() {
        if (!isRunning || isStopped) {
            return this.elapsedTime;
        } else {
            return 1;
        }
    }

    /**
     * Method to put the chronometer in pause.
     */
    public void pauseChrono() {
        if (isRunning) {
            pausedTime = System.currentTimeMillis();
            isRunning = false;
        }
    }

    /**
     * Method to start and resume the chronometer.
     */
    public void resumeChrono() {
        if (!isRunning && !isStopped) {
            long currentTime = System.currentTimeMillis();
            startTime += currentTime - pausedTime;
            pausedTime = 0;
            isRunning = true;
        }
    }

    /**
     * Method to stop the chronometer.
     */
    public void stopChrono() {
        if (isRunning || !isStopped) {
            elapsedTime = getTimeElapsed() / 10000;
            isRunning = false;
            isStopped = true;
        }
    }

    private long getTimeElapsed() {
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
