package brickbreaker.model.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import brickbreaker.ResourceLoader;
import brickbreaker.common.Difficulty;
import brickbreaker.common.Error;
import brickbreaker.common.TypePower;
import brickbreaker.common.TypePowerUp;
import brickbreaker.common.Vector2D;
import brickbreaker.controllers.listener.ErrorListener;
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

    //proportioned to world (1000, 800)
    private final Double WORLD_WIDTH = 38.0;
    private final Double WORLD_HEIGHT = 30.4;
    private final Double BOTTOM_GAP = 1.5;

    //TODO:Adapt speed.
    private final Double X_SPEED = 0.0;
    private final Double Y_SPEED = -1.0;

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

    private World getEmptyWorld() {
        Bar newBar = GameFactory.getInstance().createBar(
            new Vector2D(WORLD_WIDTH / 2, WORLD_HEIGHT - BOTTOM_GAP));
        Ball newBall = GameFactory.getInstance().createBall(
            new Vector2D(WORLD_WIDTH / 2, WORLD_HEIGHT - (BOTTOM_GAP + newBar.getHeight() / 2 + Ball.RADIUS)), 
            new Vector2D(X_SPEED, Y_SPEED));
        RectBoundingBox boundary = new RectBoundingBox(
            new Vector2D(WORLD_WIDTH / 2, WORLD_HEIGHT / 2), WORLD_WIDTH, WORLD_HEIGHT);
        World w = new WorldImpl(boundary);

        w.setBar(newBar);
        w.addBall(newBall);
        return w;
    }

    private World getBasicWorld(final String name) {

        List<Brick> bricks;
        World w = this.getEmptyWorld();
        ResourceLoader r = ResourceLoader.getInstance();

        if (r.loadMap(name).isPresent()) {
            bricks = GameFactory.getInstance().createBricks(r.loadMap(name).get(), r.getMapColumns(), r.getMapRows());
        } else {
            ErrorListener.notifyError(Error.MAPLOADER_ERROR);
            bricks = new ArrayList<Brick>();
        }
        w.addBricks(bricks);
        return w;
    }

    /**
     * This method returns a new World with a percentage passed of positive powerUp.
     * @param name
     * @param bonusPercentage
     * @return a World object
     */
    public World getWorld(final String name, final Integer bonusPercentage) {

        World w = getEmptyWorld();
        List<Brick> bricks;
        ResourceLoader r = ResourceLoader.getInstance();

        if (r.loadMap(name).isPresent()) {
            bricks = GameFactory.getInstance().createBricks(r.loadMap(name).get(), r.getMapColumns(), r.getMapRows());
        } else {
            ErrorListener.notifyError(Error.MAPLOADER_ERROR);
            bricks = new ArrayList<Brick>();
        }
        w.addBricks(bricks);

        Integer bonusQuantity = (w.getBricks().size() / 100) * bonusPercentage;

        if (bonusQuantity < w.getBricks().size()) {
            Integer malusQuantity = (w.getBricks().size() - bonusQuantity) / 2;

            List<TypePower> typePowerList = getWorldPowerUp(bonusQuantity, true);
            typePowerList.addAll(getWorldPowerUp(malusQuantity / 2, false));

            randomPowerUpAssignment(w.getBricks(), getWorldPowerUp(bonusQuantity, true));
        }

        return w;
    }

    public World getWorld(final String name, final List<TypePower> p) {
        World w = this.getBasicWorld(name);
        randomPowerUpAssignment(w.getBricks(), p);
        return w;
    }
    
    /**
     * This method returns the world passed in with more positive 
     * powerUp if easier is true or less if it is false.
     * @param name
     * @param current
     * @param easier
     * @return a World object
     */
    public World createFromWorld(final String name, final World current, final boolean easier) {

        World w = this.getBasicWorld(name);
        Integer bonusQuantity = (int) current.getBricks().stream()
        .filter(item -> item.getPowerUp().getType() == TypePowerUp.POSITIVE).count();

        if (easier) {
            bonusQuantity++;
            bonusQuantity = bonusQuantity > w.getBricks().size() ? w.getBricks().size() : bonusQuantity;
        } else {
            bonusQuantity--;
            bonusQuantity = bonusQuantity < 0 ? 0 : bonusQuantity;
        }

        List<TypePower> p = getWorldPowerUp(bonusQuantity, true);
        p.addAll(getWorldPowerUp(w.getBricks().size() - bonusQuantity, false));
        randomPowerUpAssignment(w.getBricks(), p);

        return w;
    }

    /**
     * This method returns the world with a difficulty of diff.
     * @param name
     * @param diff
     * @return a World object
     */
    public World createFromDifficulty(final String name, final Difficulty diff) {
        return this.getWorld(name, diff.getBonusPercentage());
    }

    private void randomPowerUpAssignment(final List<Brick> b, final List<TypePower> p) {
        Integer diff = b.size() - p.size();
        Random random = new Random();
        if (diff > 0) {
            p.addAll(Collections.nCopies(diff, TypePower.NULL));
        }
        for (Brick brick : b) {
            brick.setPowerUp(p.remove(random.nextInt(p.size())));
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
