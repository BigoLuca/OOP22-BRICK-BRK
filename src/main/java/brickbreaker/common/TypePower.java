package brickbreaker.common;

import java.util.List;
import java.util.stream.Stream;

/**
 * Enum of type PowerUp.
 * Each powerUp have a duration and a type.
 */
public enum TypePower {

    /** Null. */
    NULL(0, TypePowerUp.NULL),

    /** SlowBall. */
    SLOWBALL(10, TypePowerUp.POSITIVE),
    /** FastBall. */
    FASTBALL(10, TypePowerUp.NEGATIVE),
    /** SlowBall. */
    BIGBALL(10, TypePowerUp.POSITIVE),
    /** FastBall. */
    SMALLBALL(10, TypePowerUp.NEGATIVE),

    /** LongBar. */
    LONGBAR(10, TypePowerUp.POSITIVE),
    /** ShortBar. */
    SHORTBAR(10, TypePowerUp.NEGATIVE);

    private final Integer duration;
    private final TypePowerUp type;

    TypePower(final Integer time, final TypePowerUp typePower) {
        this.duration = time;
        this.type = typePower;
    }

    /**
     * This method returns a list of positive or negative powerUp type.
     * @param typePass
     * @return a list of powerUp type
     */
    public static List<TypePower> getElement(final TypePowerUp typePass) {
        return Stream.of(TypePower.values()).filter(t -> t.type.equals(typePass)).toList();
    }

    /**
     * @return the duration of the powerUp
     */
    public Integer getDuration() {
        return this.duration;
    }

    /**
     * @return the type of the powerUp
     */
    public TypePowerUp getType() {
        return this.type;
    }
}
