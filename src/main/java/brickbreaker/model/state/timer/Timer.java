package brickbreaker.model.state.timer;

/**
 * Interface to create a timer.
 * 
 * @author Agostinelli Francesco
 */
public interface Timer {

    /**
     * Method to set the Time of the timer.
     * @param time
     */
    void setTimer(Integer time);

    /**
     * Method to decrease the time of the timer of 1.
     */
    void decTime();

    /**
     * @return the remaining time of the timer.
     */
    Time getTime();

    /**
     * @return a String representation the timer
     */
    String toString();
}
