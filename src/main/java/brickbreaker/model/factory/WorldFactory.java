package brickbreaker.model.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import brickbreaker.MapInfo;
import brickbreaker.ResourceLoader;
import brickbreaker.common.Difficulty;
import brickbreaker.common.TypePower;
import brickbreaker.common.TypePowerUp;
import brickbreaker.common.Vector2D;
import brickbreaker.model.world.World;
import brickbreaker.model.world.WorldImpl;
import brickbreaker.model.world.gameObjects.Ball;
import brickbreaker.model.world.gameObjects.Bar;
import brickbreaker.model.world.gameObjects.Brick;
import brickbreaker.model.world.gameObjects.bounding.RectBoundingBox;

/**
 * Factory class for creating game World.
 */
public class WorldFactory {

    //TODO:Adapt speed.
    private final Double X_SPEED = 15.0;
    private final Double Y_SPEED = 15.0;

    private static WorldFactory instance;

    /**
     * @return the instance of WorldFactory if it not exists yet.
     */
    public static WorldFactory getInstance() {
        if (instance == null) {
            instance = new WorldFactory();
        }

        return instance;
    }

    //TODO: Add actual parameters.
    private World getEmptyWorld() {
        Bar newBar = GameFactory.getInstance().createBar(new Vector2D(150,300));
        Ball newBall = GameFactory.getInstance().createBall(new Vector2D(140, 140), new Vector2D(X_SPEED, Y_SPEED));
        RectBoundingBox boundary = new RectBoundingBox(new Vector2D(0, 0), 576.0, 576.0);
        World w = new WorldImpl(boundary);

        w.setBar(newBar);
        w.addBall(newBall);
        return w;
    }

    public World getRandomWorld(final Difficulty d) {
        World w = this.getEmptyWorld();
        w.addBricks(GameFactory.getInstance().createRandomBricks(d, 10, 10));
        randomPowerUpAssignment(d, w.getBricks());
        return w;
    }

    public World getWorld(final Integer index) {
        World w = this.getEmptyWorld();
        MapInfo i = ResourceLoader.getInstance().getMapsInfo().stream().filter(item -> item.getIndex() == index).findFirst().get();
        w.addBricks(GameFactory.getInstance().createBricks(i.getBricksData(), ResourceLoader.getInstance().MAP_COLUMNS_FILE_FORMAT, ResourceLoader.getInstance().MAP_ROWS_FILE_FORMAT));
        randomPowerUpAssignment(i.getDifficulty(), w.getBricks());
        return w;
    }
    
    private void randomPowerUpAssignment(final Difficulty d, final List<Brick> b) {
        Random r = new Random();

        Integer other = (b.size() - d.getBonusPercentage(b.size()));
        List<TypePower> p = this.getWorldPowerUp(d.getBonusPercentage(b.size()), true);
        p.addAll(this.getWorldPowerUp(other, false));


        for (Brick i : b) {
            i.setPowerUp(p.get(r.nextInt(b.size())));
        }
    }

    /**
     * This method returns a list of TypePower which are the power up types that are
     * present in the current World.
     * @param pQuantity is the power up quantity value.
     * @param bonus is true if the powerup to fill 
     * @return a list of Typepower
     */
    private List<TypePower> getWorldPowerUp(final Integer pQuantity, final boolean bonus) {
        List<TypePower> p = new ArrayList<>();
        Random r = new Random();
        List<TypePower> types = TypePower.getElement(bonus ? TypePowerUp.POSITIVE : TypePowerUp.NEGATIVE);

        for (int i = 0; i < pQuantity; i++) {
            Integer randomChoice = r.nextInt(types.size());
            p.add(types.get(randomChoice));
        }

        return p;
    }
}
