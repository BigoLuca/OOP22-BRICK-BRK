package brickbreaker.model.world.gameObjects;

import brickbreaker.common.TypeObj;
import brickbreaker.common.TypePower;
import brickbreaker.common.Vector2D;
import brickbreaker.model.factory.WorldFactory;
import brickbreaker.model.world.gameObjects.bounding.RectBoundingBox;

/**
 * Class to model the game object Brick.
 * Extends {@link GameObjectImpl}.
 */
public class Brick extends GameObjectImpl<RectBoundingBox> {

    public static final Integer BRICKS_COL = 6;
    public static final Integer BRICKS_ROW = 6;

    public static final Double BRICK_WIDTH = WorldFactory.BOUNDARIES_SIZE/ BRICKS_COL;
    public static final Double BRICK_HEIGHT = WorldFactory.BOUNDARIES_SIZE/ (BRICKS_ROW*2); // Times two since we use half of the height
    private TypePower powerUp;

    /**
     * Brick constructor.
     * @param pos
     * @param lifeToSet
     */
    public Brick(final Vector2D pos, final int lifeToSet) {
        super(lifeToSet, new Vector2D(0, 0), TypeObj.BRICK, new RectBoundingBox(pos, BRICK_WIDTH, BRICK_HEIGHT));
        this.powerUp = TypePower.NULL;
    }

    /**
     * @return the type of powerUp
     */
    public TypePower getPowerUp() {
        return this.powerUp;
    }

    /**
     * @param powerUpToSet
     */
    public void setPowerUp(final TypePower powerUpToSet) {
        this.powerUp = powerUpToSet;
    }
}
