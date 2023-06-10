package brickbreaker.view;

import brickbreaker.common.GameImages;
import brickbreaker.common.GameObjectsImages;
import brickbreaker.common.Vector2D;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import brickbreaker.model.factory.WorldFactory;
import brickbreaker.model.world.gameObjects.Ball;
import brickbreaker.model.world.gameObjects.Bar;
import brickbreaker.model.world.gameObjects.Brick;
import brickbreaker.model.world.gameObjects.bounding.RectBoundingBox;

import java.util.HashMap;
import java.util.List;

public class GameView extends ViewImpl {

    private static final Double CANVAS_HEIGHT = WorldFactory.BOUNDARIES_SIZE;
    private static final Double CANVAS_WIDTH = WorldFactory.BOUNDARIES_SIZE;

    private static final Double BRICK_WIDTH = Brick.BRICK_WIDTH;
    private static final Double BRICK_HEIGHT = Brick.BRICK_HEIGHT;

    @FXML 
    private Label scoreLabel;
    
    @FXML 
    private ImageView backGround;
    
    @FXML 
    private Canvas foreGround;
    
    @FXML 
    private ImageView ball;
    
    @FXML 
    private ImageView pauseButton;

    private GraphicsContext gcF;
    private HashMap<Integer, Image> brickImages;

    @Override
    public void init() {
        this.backGround.setImage(GameImages.CITY_LANDSCAPE.getImage());
        this.gcF = foreGround.getGraphicsContext2D();
        RectBoundingBox b = this.getController().getModel().getWorld().getMainBBox();
        gcF.setFill(Color.WHITE);
        gcF.fillRect(b.getP2d().getX(), b.getP2d().getY(), b.getWidth(), b.getHeight());
        this.setUpBrickImages();
        this.getStage().getScene().setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
        //this.getStage().getScene().setWidth(this.getController().getModel().getWorld().getMainBBox().getULCorner().getX());
        this.getController().play();
    }

    public void setUpBrickImages() {
        this.brickImages = new HashMap<>(10);

        for (Integer i = 0; i < 10; i++) {
            this.brickImages.put(Integer.valueOf(i + 1), GameObjectsImages.values()[9 - i].getImage());
        }
    }

    public void render() {

        Platform.runLater(() -> {
            this.gcF.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

            List<Brick> b = this.getController().getModel().getWorld().getBricks();
            for (Brick item : b) {
                Image i = this.brickImages.get(item.getLife());
                Vector2D p = item.getPosition();
                this.gcF.drawImage(i, p.getX(), p.getY(), BRICK_WIDTH, BRICK_HEIGHT);
            }

            Bar bar = this.getController().getModel().getWorld().getBar();
            Ball ball = this.getController().getModel().getWorld().getBalls().get(0);
            this.gcF.drawImage(GameObjectsImages.BAR.getImage(), bar.getPosition().getX(), bar.getPosition().getY(), bar.getWidth(), bar.getHeight());
            this.gcF.drawImage(GameObjectsImages.BALL.getImage(), ball.getPosition().getX(), ball.getPosition().getY(), ball.getRadius()*2, ball.getRadius()*2);
        });
    }

    public void handleKeyPressed(KeyCode keyCode) {
        if (keyCode == KeyCode.LEFT) {
            this.getController().getInputController().notifyMoveLeft();
        } else if (keyCode == KeyCode.RIGHT) {
            this.getController().getInputController().notifyMoveRight();
        }
    }
}
