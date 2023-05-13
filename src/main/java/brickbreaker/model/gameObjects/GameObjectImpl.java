package brickbreaker.model.gameObjects;

import brickbreaker.common.P2d;
import brickbreaker.common.TypeObj;
import brickbreaker.common.V2d;
import brickbreaker.model.gameObjects.bounding.BoundingBox;

/**
 * {@inheritDoc}
 * Implements the {@link GameObject} interface. Abstract base class for all objects.
 * 
 * @author Bighini Luca
 * @param <T> the type of bounding box
 */
public abstract class GameObjectImpl<T extends BoundingBox> implements GameObject<T> {

    private Integer lifes;
    private TypeObj type;
	private V2d vel;
    private T bbox;

    /**
     * GameObject constructor.
     * @param lifesToSet
     * @param vel
     * @param typeToSet
     * @param bboxToSet
     */
    public GameObjectImpl(final Integer lifesToSet, final V2d vel, final TypeObj typeToSet, final T bboxToSet) {
		this.vel = vel;
		this.type = typeToSet;
        this.lifes = lifesToSet;
        this.bbox = bboxToSet;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLife() {
        return this.lifes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decLife() {
        this.lifes--;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incLife() {
        this.lifes++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeObj getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public P2d getPosition() {
        return this.bbox.getP2d();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final P2d newPosition) {
        this.bbox.setP2d(newPosition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V2d getSpeed() {
        return vel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public void setSpeed(final V2d vel) {
		this.vel = vel;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public T getBBox() {
        return this.bbox;
    }

}
