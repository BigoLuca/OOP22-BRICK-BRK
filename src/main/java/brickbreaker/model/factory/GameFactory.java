package brickbreaker.model.factory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import brickbreaker.common.Difficulty;
import brickbreaker.common.Vector2D;
import brickbreaker.model.world.gameObjects.Ball;
import brickbreaker.model.world.gameObjects.Bar;
import brickbreaker.model.world.gameObjects.Brick;

/**
 * Factory class for creating game objects: Ball, Bar, Bricks.
 */
public class GameFactory {

    private static GameFactory instance;

    /**
     * @return the instance of GameFactory if it not exists yet.
     */
    public static GameFactory getInstance() {
        if (instance == null) {
            instance = new GameFactory();
        }
        return instance;
    }

    /**
     * @param posToSet
     * @param velToSet
     * @return a new Ball object
     */
    public Ball createBall(final Vector2D posToSet, final Vector2D velToSet) {
        return new Ball(posToSet, velToSet);
    }

    /**
     * @param list of bricks life
     * @param col
     * @param row
     * @return a list of brick objects
     */
    public List<Brick> createBricks(final List<Integer> list, final Integer col, final Integer row) {
        
        List<Brick> result = new ArrayList<>();
        Integer life;
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                life = list.get(x + y * col);
                if (life > 0) {
                    result.add(new Brick(new Vector2D(x, y), life));
                }
            }
        }
        return result;
    }

	public List<Brick> createRandomBricks(final Difficulty d, final Integer cols, final Integer rows) {
		Random r = new Random();
		List<Brick> bricks = new LinkedList<>();
        Integer max = d.getMaximumBricksQuantity();
        Integer min = d.getMinimumBricksQuantity();
        Integer bricksQ = r.nextInt((max - min) + 1) + min;
        Integer mLQ = d.getMoreLifePercentage(bricksQ);

		for(int i = 0; i < bricksQ; i++) {
			bricks.add(new Brick(new Vector2D(r.nextInt(cols), r.nextInt(rows)), 1));
		}

        while (mLQ > 0) {
            bricks.get(r.nextInt(bricksQ)).setLife(r.nextInt((5 - 1) + 1) + 1);
            mLQ--;
        }

		return bricks;
	}

	/**
	 * @param posToSet
	 * @return a new Bar object
	 */
    public Bar createBar(final Vector2D posToSet) {
        return new Bar(posToSet, 1);
    }
}
