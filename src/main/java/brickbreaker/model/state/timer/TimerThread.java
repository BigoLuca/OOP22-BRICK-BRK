package brickbreaker.model.state.timer;

/**
 * Class to work with different timer simultaneously.
 */
public class TimerThread extends Thread {

    private Timer timer;
    private boolean running;

    /**
     * Timer thread constructor.
     * @param t
     */
    public TimerThread(final Timer t) {
        this.timer = t;
        this.running = true;
    }

    /**
     * Method to stop the timer.
     */
    public void stopTimer() {
        this.running = false;
    }

    public void resumeTimer() {
        this.running = true;
    }
    /**
     * Method to start the timer.
     */
    @Override
    public void run() {
        while (this.running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.timer.decTime();
        }
    }
}
