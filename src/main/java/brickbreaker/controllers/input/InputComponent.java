package brickbreaker.controllers.input;

import brickbreaker.model.gameObjects.Bar;

/**
 * Interface with a common method for all input.
 * 
 * @author Bighini Luca
 * @author Tellarini Pietro
 */
public interface InputComponent {

    /**
     * Updates the position of the Bar object based on InputController
     * if is not out of bound rb.
     * @param bar
     * @param rb
     * @param c
     */
    void update(Bar bar, Double rb,  InputController c);
}
