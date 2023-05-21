package brickbreaker.model.state.timer;

/**
 * @inheritDoc
 * Class that implements {@link Timer} interface.
 */
public class TimerImpl implements Timer {

    private Time remainedTime;

    /**
     * Timer constructor.
     * @param time
     */
    public TimerImpl(final int time) {
        this.remainedTime = new TimeImpl(time);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setTimer(final Integer time) {
        this.remainedTime.setTotal(time);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void decTime() {
        int currentTimeAmount = this.remainedTime.getTotal();
        this.remainedTime.setTotal(currentTimeAmount - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Time getTime() {
        return this.remainedTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%02d", this.getTime().getMinutes() + ":" 
            + String.format("%02d", this.getTime().getSecondsInMinute()));
    }
}
