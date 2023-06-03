package brickbreaker.model.world;


import java.util.List;

import java.util.ArrayList;
import java.util.Iterator;

import brickbreaker.common.TypePower;
import brickbreaker.common.Vector2D;
import brickbreaker.model.factory.ApplicatorFactory;
import brickbreaker.model.world.event.*;
import brickbreaker.model.world.gameObjects.Ball;
import brickbreaker.model.world.gameObjects.Bar;
import brickbreaker.model.world.gameObjects.Brick;
import brickbreaker.model.world.gameObjects.PowerUp;
import brickbreaker.model.world.gameObjects.bounding.RectBoundingBox;
import brickbreaker.model.world.gameObjects.power.applicator.PowerUpApplicator;

/**
 * {@inheritDoc}
 * Implements the {@link World} interface.
 */
public class WorldImpl implements World {

    /**
     * Indicates on which side the collision occurred.
     */
    public enum SideCollision { TOP, BOTTOM, LEFT, RIGHT }

    private List<Ball> balls;
    private Bar bar;
    private List<Brick> bricks;
    private List<PowerUp> activePowerUps;
    private RectBoundingBox mainBBox;
    private WorldEvent event;
    private Integer score;

    private final Double mulELAPSED = 0.001;

    /**
     * World constructor.
     * @param mainBbox
     */
    public WorldImpl(final RectBoundingBox mainBbox) {
        this.balls = new ArrayList<>();
        this.bricks = new ArrayList<>();
        this.activePowerUps = new ArrayList<>();
        this.mainBBox = mainBbox;
        this.score = 0;
        this.event = new WorldEvent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBall(final Ball ball) {
        this.balls.add(ball);
    }

    private void removeBall(final Ball ball) {
        this.balls.remove(ball);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Ball> getBalls() {
        return this.balls;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bar getBar() {
        return this.bar;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBar(final Bar bar) {
        this.bar = bar;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBricks(final List<Brick> bricks) {
        this.bricks.addAll(bricks);
    }

    private void removeBrick(final Brick brick) {
        if (brick.getPowerUp() != TypePower.NULL) {
            this.activePowerUps.add(new PowerUp(brick.getBBox().getP2d(), brick.getPowerUp()));
        }
        this.bricks.remove(brick);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Brick> getBricks() {
        return this.bricks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RectBoundingBox getMainBBox() {
        return this.mainBBox;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGame(final int elapsed) {
        balls.stream().forEach(t -> t.setPosition(t.getPosition().sum(t.getSpeed().mul(mulELAPSED * elapsed))));
        activePowerUps.stream().forEach(t -> t.setPosition(t.getPosition().sum(t.getSpeed().mul(mulELAPSED * elapsed))));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkCollision() {
        checkCollisionWithBall();
        checkCollisionWithPowerUp();
    }

    /*
     * Ball collsion with boundary
     * Ball collision with bar
     * Ball collision with bricks
     */
    private void checkCollisionWithBall() {
        Vector2D ul = mainBBox.getULCorner();
        Vector2D br = mainBBox.getBRCorner();

        Iterator<Ball> ballIt = balls.iterator();
        while (ballIt.hasNext()) {
            Ball ball = ballIt.next();
            Vector2D pos = ball.getPosition();
            Double r = ball.getRadius();

            if (pos.getY() - r < ul.getY()) {
                //TOP-BORDER
                this.event.process(ball, SideCollision.TOP, ul.getY());
            } else if (pos.getY() + r > br.getY()) {
                //BOTTOM-BORDER
                this.removeBall(ball);
                if (this.balls.size() <= 0) {
                    this.bar.decLife();
                }
            } else if (pos.getX() - r < ul.getX()) {
                //LEFT-BORDER
                this.event.process(ball, SideCollision.LEFT, ul.getX());
            } else if (pos.getX() + r > br.getX()) {
                //RIGHT-BORDER
                this.event.process(ball, SideCollision.RIGHT, br.getX());
            } else if (bar.getBBox().isCollidingWith(ball.getBBox())) {
                //BAR
                this.event.process(ball, this.bar);
            } else {
                //BRICK
                Iterator<Brick> brickIt = bricks.iterator();
                while (brickIt.hasNext()) {
                    Brick brick = brickIt.next();
                    if (brick.getBBox().isCollidingWith(ball.getBBox())) {
                        this.event.process(ball, brick);
                        brick.decLife();
                        if (brick.getLife() <= 0) {
                            this.removeBrick(brick);
                        }
                    }
                }
            }
        }
    }

    /*
     * Power up collision with bar
     */
    private void checkCollisionWithPowerUp() {
        Iterator<PowerUp> powerIt = activePowerUps.iterator();
        while (powerIt.hasNext()) {
            PowerUp p = powerIt.next();
            if (p.getPosition().getY() - p.getHeight() / 2 < mainBBox.getBRCorner().getY()) {
                this.activePowerUps.remove(p);
            } else if (p.getBBox().isCollidingWith(bar.getBBox())) {
                //PowerUpApplicator a = ApplicatorFactory.getInstance().createApplicator(p.getPowerUp());
                this.event.process(p,); // end power up applicator
                this.activePowerUps.remove(p);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getScore() {
        return this.score;
    }

    // TODO potrebbero essere privati
    /**
     * {@inheritDoc}
     */
    @Override
    public final void incScore(final Integer increment) {
        this.score += increment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void decScore(final Integer decrement) {
        this.score -= decrement;
    }

}
