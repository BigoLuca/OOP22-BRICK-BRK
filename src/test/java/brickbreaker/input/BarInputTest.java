package brickbreaker.input;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import brickbreaker.common.Vector2D;
import brickbreaker.controllers.input.InputController;
import brickbreaker.model.factory.WorldFactory;
import brickbreaker.model.world.World;

public class BarInputTest {
    
    private World world;
    private InputController controller;

    @BeforeEach
    void setUp() {
        world = WorldFactory.getInstance().getWorld(1);
        controller = new InputController();
    }

    @Test
    void testMove() {
        Vector2D pos = world.getBar().getPosition();
        controller.notifyMoveLeft();
        world.getBar().updateInput(200.0, controller, world.getMainBBox().getBRCorner().getX());
        assertFalse(pos.getX() == world.getBar().getPosition().getX());
        assertFalse(pos.getY() == world.getBar().getPosition().getY());
    }
}
