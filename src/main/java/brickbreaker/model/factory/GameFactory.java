package brickbreaker.model.factory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import brickbreaker.common.P2d;
import brickbreaker.common.V2d;
import brickbreaker.model.gameObjects.Ball;
import brickbreaker.model.gameObjects.Bar;
import brickbreaker.model.gameObjects.Brick;

/**
 * Factory class for creating game objects: Ball, Bar, Bricks.
 * 
 * @author Bighini Luca
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
    public Ball createBall(final P2d posToSet, final V2d velToSet) {
        return new Ball(posToSet, velToSet);
    }

    /**
     * @param list of bricks life
     * @param col
     * @param line
     * @return a list of brick objects
     */
    public List<Brick> createBricks(final List<Integer> list, final Integer col, final Integer line) {
        List<Brick> result = new ArrayList<>();
        Integer life;
        try {
            for (int y = 0; y < line; y++) {
                for (int x = 0; x < col; x++) {
                    life = list.get(x + y * col);
                    if (life > 0) {
                        result.add(new Brick(new P2d(x, y), life));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Size of map not correct");
            e.printStackTrace();
        }
        return result;
    }

	//TODO: Check probability distribution for life generation.
	public List<Brick> createRandomBricks(final Integer maxBricks, final Integer cols, final Integer rows) {
		Integer randomX, randomY;
		Random r = new Random();
		List<Brick> bricks = new LinkedList<>();

		for(int i = 0; i < maxBricks; i++) {
			randomX = r.nextInt(cols);
			randomY = r.nextInt(rows);
			bricks.add(new Brick(new P2d(randomX, randomY), 1));
		}

		return bricks;
	}

	/**
	 * @param posToSet
	 * @return a new Bar object
	 */
    public Bar createBar(final P2d posToSet) {
        return new Bar(posToSet, 1);
    }
}
