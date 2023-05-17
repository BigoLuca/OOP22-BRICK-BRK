package brickbreaker.model.world;

import java.util.ArrayList;
import java.util.List;
import brickbreaker.common.P2d;
import brickbreaker.controllers.state.event.HitBar;
import brickbreaker.controllers.state.event.HitBorder;
import brickbreaker.controllers.state.event.HitBrick;
import brickbreaker.controllers.state.event.HitPowerUp;
import brickbreaker.controllers.state.event.WorldEventListener;
import brickbreaker.model.factory.ApplicatorFactory;
import brickbreaker.model.gameObjects.Ball;
import brickbreaker.model.gameObjects.Bar;
import brickbreaker.model.gameObjects.Brick;
import brickbreaker.model.gameObjects.bounding.RectBoundingBox;
import brickbreaker.model.gameObjects.power.PowerUp;
import brickbreaker.model.gameObjects.power.TypePower;
import brickbreaker.model.gameObjects.power.applicator.PowerUpApplicator;

/**
 * {@inheritDoc}
 * Implements the {@link World} interface.
 * 
 * @author Bighini Luca
 * @author Agostinelli Francesco
 */
//TODO: Discuss if implement a powerup list or stay with the difficulty field.
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
    private WorldEventListener evListener;

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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEventListener(final WorldEventListener listener) {
        this.evListener = listener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBall(final Ball ball) {
        this.balls.add(ball);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeBall(final Ball ball) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeBrick(final Brick brick) {
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
        P2d ul = mainBBox.getULCorner();
        P2d br = mainBBox.getBRCorner();

        for (Ball ball : this.balls) {
            P2d pos = ball.getPosition();
            Double r = ball.getRadius();

            if (pos.getY() - r < ul.getY()) {
                //TOP-BORDER
                this.evListener.notifyEvent(new HitBorder(ball, SideCollision.TOP, ul.getY()));
            } else if (pos.getY() + r > br.getY()) {
                //BOTTOM-BORDER
                this.evListener.notifyEvent(new HitBorder(ball, SideCollision.BOTTOM, br.getY()));
            } else if (pos.getX() - r < ul.getX()) {
                //LEFT-BORDER
                this.evListener.notifyEvent(new HitBorder(ball, SideCollision.LEFT, ul.getX()));
            } else if (pos.getX() + r > br.getX()) {
                //RIGHT-BORDER
                this.evListener.notifyEvent(new HitBorder(ball, SideCollision.RIGHT, br.getX()));
            } else if (bar.getBBox().isCollidingWith(ball.getBBox())) {
                //BAR
                this.evListener.notifyEvent(new HitBar(ball));
            } else {
                //BRICK
                for (Brick b : this.bricks) {
                    if (b.getBBox().isCollidingWith(ball.getBBox())) {
                        this.evListener.notifyEvent(new HitBrick(b, ball));
                    }
                }
            }
        }
    }

    /*
     * Power up collision with bar
     */
    private void checkCollisionWithPowerUp() {
        for (PowerUp p : this.activePowerUps) {

            if (p.getPosition().getY() - p.getHeight() / 2 < mainBBox.getBRCorner().getY()) {
                this.activePowerUps.remove(p);
            } else if (p.getBBox().isCollidingWith(bar.getBBox())) {
                PowerUpApplicator a = ApplicatorFactory.getInstance().createApplicator(p.getPowerUp());
                this.evListener.notifyEvent(new HitPowerUp(p, a));
                this.activePowerUps.remove(p);
            }
        }
    }

}
