package brickbreaker.model.state.timer;

/**
 * @inheritDoc
 * Class that implements {@link Time} interface.
 */
public class TimeImpl implements Time {

    private static final Integer SECONDS_IN_MINUTE = 60;
    private Integer total;

    /**
     * Time constructor.
     * @param time
     */
    public TimeImpl(final int time) {
        this.setTotal(time);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTotal(final Integer totalTime) {
        this.total = totalTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getTotal() {
        return this.total;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getMinutes() {
        return this.total / SECONDS_IN_MINUTE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getSecondsInMinute() {
        return this.total % SECONDS_IN_MINUTE;
    }
}
