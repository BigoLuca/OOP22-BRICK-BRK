package brickbreaker.model.gameObjects.power;

import java.util.List;
import java.util.stream.Stream;

import brickbreaker.common.TypePowerUp;

/**
 * Enum of type PowerUp.
 * Each powerUp have a duration and a type.
 * 
 * @author Bighini Luca
 */
public enum TypePower {

    /** Null. */
    NULL(0.0, TypePowerUp.NULL),
    /** SlowBall. */
    SLOWBALL(10.0, TypePowerUp.POSITIVE),
    /** FastBall. */
    FASTBALL(10.0, TypePowerUp.NEGATIVE),
    /** LongBar. */
    LONGBAR(10.0, TypePowerUp.POSITIVE),
    /** ShortBar. */
    SHORTBAR(10.0, TypePowerUp.NEGATIVE);

    private final Double timeAmount;
    private final TypePowerUp type;

    TypePower(final Double time, final TypePowerUp typePower) {
        this.timeAmount = time;
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
    public Double getTimeAmount() {
        return this.timeAmount;
    }

    /**
     * @return the type of the powerUp
     */
    public TypePowerUp getType() {
        return this.type;
    }
}
