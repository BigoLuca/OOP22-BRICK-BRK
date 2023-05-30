package brickbreaker.model.world.gameObjects;

import brickbreaker.common.TypeObj;
import brickbreaker.common.Vector2D;
import brickbreaker.controllers.input.InputComponent;
import brickbreaker.controllers.input.InputController;
import brickbreaker.controllers.input.PlayerInput;
import brickbreaker.model.world.gameObjects.bounding.RectBoundingBox;

/**
 * Class to model the game object Bar.
 * Extends {@link GameObjectImpl}.
 */
public class Bar extends GameObjectImpl<RectBoundingBox> {

    private static final Double BAR_WIDTH = 5.0;
    private static final Double BAR_HEIGHT = 1.0;
    private InputComponent input;

    /**
     * Bar constructor.
     * @param pos
     * @param lifeToset
     */
    public Bar(final Vector2D pos, final Integer lifeToset) {
        super(lifeToset, new Vector2D(0, 0), TypeObj.BAR, new RectBoundingBox(pos, BAR_WIDTH, BAR_HEIGHT));
        input = new PlayerInput();
    }

    /**
     * @return the current width of the Bar
     */
    public Double getWidth() {
        return this.getBBox().getWidth();
    }

    /**
     * Method to set the current Bar width.
     * @param widthToSet
     */
    public void setWidth(final Double widthToSet) {
        this.getBBox().setWidth(widthToSet);
    }

    /**
     * Method for horizontal movement of the Bar.
     * @param m
     */
    public void move(final Integer m) {
        this.setPosition(new Vector2D(this.getPosition().getX() + m, this.getPosition().getY()));
    }

    /**
     * Method to update the player input commands.
     * @param c
     * @param rightBorder
     */
    public void updateInput(final InputController c, final Double rightBorder) {
        input.update(this, rightBorder, c);
    }
}
