package brickbreaker.model.factory;

import java.util.List;

import brickbreaker.common.Vector2D;
import brickbreaker.model.world.World;
import brickbreaker.model.world.WorldImpl;
import brickbreaker.model.world.gameObjects.Ball;
import brickbreaker.model.world.gameObjects.Bar;
import brickbreaker.model.world.gameObjects.Brick;
import brickbreaker.model.world.gameObjects.PowerUp;
import brickbreaker.model.world.gameObjects.bounding.RectBoundingBox;

public class WorldFactoryBis implements WorldFactoryInt  {

    /**
     *
     */
    private static final Double BOUNDARIES_SIZE = 1000.0;
    private RectBoundingBox boundary = new RectBoundingBox(new Vector2D(BOUNDARIES_SIZE/2, BOUNDARIES_SIZE/2), BOUNDARIES_SIZE, BOUNDARIES_SIZE);

    

    public World RandomWorld(){
        return new WorldImpl(boundary){
            
            @Override
            public void addBricks(final List<Brick> bricks) {
                for (Brick b : bricks) {
                    this.bricks.add(b);
                }
            }
        
            
        };
    } 
}    
