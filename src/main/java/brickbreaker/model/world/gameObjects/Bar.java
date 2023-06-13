package brickbreaker.model.world.gameObjects;

import brickbreaker.common.TypeObj;
import brickbreaker.common.Vector2D;
import brickbreaker.controllers.input.BarInput;
import brickbreaker.controllers.input.InputComponent;
import brickbreaker.controllers.input.InputController;
import brickbreaker.model.factory.WorldFactory;
import brickbreaker.model.world.gameObjects.bounding.RectBoundingBox;

/**
 * Class to model the game object Bar.
 * Extends {@link GameObjectImpl}.
 */
public class Bar extends GameObjectImpl<RectBoundingBox> {

    public static final Double BAR_WIDTH = WorldFactory.BOUNDARIES_SIZE / 3;
    public static final Double BAR_HEIGHT = WorldFactory.BOUNDARIES_SIZE / 25;
    private InputComponent input;

    /**
     * Bar constructor.
     * @param pos the position of the Bar
     * @param lifeToset the life to set
     */
    public Bar(final Vector2D pos, final Integer lifeToset) {
        super(lifeToset, new Vector2D(0, 0), TypeObj.BAR, new RectBoundingBox(pos, BAR_WIDTH, BAR_HEIGHT));
        input = new BarInput();
    }

    /**
     * @return the current width of the Bar
     */
    public Double getWidth() {
        return this.getBBox().getWidth();
    }

    /**
     * @return the current height of the Bar
     */
    public Double getHeight() {
        return this.getBBox().getHeight();
    }

    /**
     * Method to set the current Bar width.
     * @param widthToSet the width to set
     */
    public void setWidth(final Double widthToSet) {
        this.getBBox().setWidth(widthToSet);
    }

    /**
     * Method for horizontal movement of the Bar.
     * @param m the movement to set
     */
    public void move(final Long m) {
        this.setPosition(new Vector2D(this.getPosition().getX() + m, this.getPosition().getY()));
    }

    /**
     * Method to update the player input commands.
     * @param c the input controller
     * @param rightBorder the right border of the game
     */
    public void updateInput(final Double elapsed, final InputController c, final Double rightBorder) {
        input.update(this, rightBorder, c, elapsed);
    }
}
