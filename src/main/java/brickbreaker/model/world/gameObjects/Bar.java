package brickbreaker.model.world.gameObjects;

import brickbreaker.common.P2d;
import brickbreaker.common.TypeObj;
import brickbreaker.common.V2d;
import brickbreaker.controllers.input.InputComponent;
import brickbreaker.controllers.input.InputController;
import brickbreaker.model.world.gameObjects.bounding.RectBoundingBox;

/**
 * Class to model the game object Bar.
 * Extends {@link GameObjectImpl}.
 * 
 * @author Tellarini Pietro
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
    public Bar(final P2d pos, final Integer lifeToset) {
        super(lifeToset, new V2d(0, 0), TypeObj.BAR, new RectBoundingBox(pos, BAR_WIDTH, BAR_HEIGHT));
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
        this.setPosition(new P2d(this.getPosition().getX() + m, this.getPosition().getY()));
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
