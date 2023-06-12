package brickbreaker.common;

/**
 * Class representing a chronometer.
 * Method:
 */
public class Chronometer {
    private long startTime;
    private long totalPausedTime;
    private boolean isRunning;
    private boolean isPaused;

    /*
     * Method to start the chronometer.
     * If the chronometer is already running or paused, it does nothing.
     * If the chronometer is stopped, it starts it.
     */
    public void startChrono() {
        if (isRunning || isPaused) {
            if (isPaused) {
                totalPausedTime += System.currentTimeMillis() - startTime;
                isPaused = false;
            }
            return; // Already running or paused, so just return
        }

        startTime = System.currentTimeMillis();
        isRunning = true;
    }

    /**
     * Method to pause the chronometer.
     * 
     */
    public void pauseChrono() {
        if (isRunning && !isPaused) {
            totalPausedTime += System.currentTimeMillis() - startTime;
            isRunning = false;
            isPaused = true;
        }
    }

    /**
     * Method to stop the chronometer.
     * 
     */
    public void stopChrono() {
        if (isRunning || isPaused) {
            totalPausedTime = 0;
            isRunning = false;
            isPaused = false;
        }
    }

    /**
     * Method to get the elapsed time.
     * 
     * @return the elapsed time
     */
    public Integer getElapsedTime() {
        Integer elapsed = 0;
        if (isRunning) {
            elapsed = (int) (System.currentTimeMillis() - startTime + totalPausedTime) / 1000;
        } else {
            elapsed = (int) totalPausedTime / 1000;
        }
        return elapsed;
    }
}
