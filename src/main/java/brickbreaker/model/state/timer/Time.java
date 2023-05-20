package brickbreaker.model.timer;

/**
 * Interface to work with time.
 * 
 * @author Agostinelli Francesco
 */
public interface Time {

    /**
     * Method to set the total time of the timer (seconds).
     * @param totalTime
     */
    void setTotal(Integer totalTime);

    /**
     * @return the total time of the timer (seconds)
     */
    Integer getTotal();

    /**
     * @return the total time of the timer (minutes)
     */
    Integer getMinutes();

    /**
     * Method to get the seconds after dividing the total time by minutes.
     * @return the seconds of total time (seconds)
     */
    Integer getSecondsInMinute();
}
