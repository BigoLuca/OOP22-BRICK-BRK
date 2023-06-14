package brickbreaker.model.input;

import brickbreaker.controllers.InputController;
import brickbreaker.model.world.gameObjects.Bar;

/**
 * Interface with a common method for all object input.
 */
public interface InputComponent {

    /**
     * Updates the position of the Bar object based on InputController
     * if is not out of bound rb.
     * @param bar
     * @param rb
     * @param c
     * @param elapsed
     */
    void update(Bar bar, Double rb,  InputController c, Double elapsed);
}
