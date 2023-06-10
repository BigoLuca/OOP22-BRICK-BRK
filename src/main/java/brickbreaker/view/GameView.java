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
import javafx.scene.layout.Pane;
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
    private Pane gamePane;
    
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
        this.getController().setGameView(this);

        this.backGround.setImage(GameImages.CITY_LANDSCAPE.getImage());
        this.foreGround.setHeight(CANVAS_HEIGHT);
        this.foreGround.setWidth(CANVAS_WIDTH);
        this.foreGround.widthProperty().bind(this.gamePane.widthProperty());
        this.foreGround.heightProperty().bind(this.gamePane.heightProperty());
        this.gcF = foreGround.getGraphicsContext2D();
        RectBoundingBox b = this.getController().getModel().getWorld().getMainBBox();
        this.gcF.setFill(Color.BLACK);
        this.gcF.fillRect(b.getULCorner().getX(), b.getULCorner().getY(), b.getWidth(), b.getHeight());
        this.setUpBrickImages();
        this.getStage().getScene().setOnKeyPressed(e -> handleKeyPressed(e.getCode()));

        // Start the game
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
                this.gcF.drawImage(i, p.getX() - BRICK_WIDTH/2 , p.getY() - BRICK_HEIGHT/2 , BRICK_WIDTH, BRICK_HEIGHT);
            }

            Bar bar = this.getController().getModel().getWorld().getBar();
            Ball ball = this.getController().getModel().getWorld().getBalls().get(0);
            this.gcF.drawImage(GameObjectsImages.BAR.getImage(), bar.getPosition().getX() - bar.getWidth()/2, bar.getPosition().getY() - bar.getHeight() / 2, bar.getWidth(), bar.getHeight());
            this.gcF.drawImage(GameObjectsImages.BALL.getImage(), ball.getPosition().getX() - ball.getRadius(), ball.getPosition().getY() - ball.getRadius(), ball.getRadius()*2, ball.getRadius()*2);
        });
    }

    public void isOver() {
        System.out.println("Error while switching to GameOverView");
    }

    public void handleKeyPressed(KeyCode keyCode) {
        if (keyCode == KeyCode.LEFT) {
            this.getController().getInputController().notifyMoveLeft();
        } else if (keyCode == KeyCode.RIGHT) {
            this.getController().getInputController().notifyMoveRight();
        }
    }
}
