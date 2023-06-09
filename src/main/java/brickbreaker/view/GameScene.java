package brickbreaker.view;

import java.util.ArrayList;
import java.util.List;

import brickbreaker.model.Level;
import brickbreaker.model.world.World;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameScene {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final int PADDLE_WIDTH = 600;
    private static final int PADDLE_HEIGHT = 20;
    private static final int BRICK_WIDTH = 80;
    private static final int BRICK_HEIGHT = 30;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color PADDLE_COLOR = Color.WHITE;
    private static final Color BRICK_COLOR[] = {Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED, Color.PURPLE, Color.BLUE, Color.WHITE, Color.BLACK};
    private static final Color BALL_COLOR = Color.RED;
    private static final int PADDLE_SPEED = 20;
    private static int BRICK_ROWS = 5;
    private static int BRICK_COLUMNS = 8;

    private final Stage primaryStage;
    private final ViewController app;
    private Canvas canvas;
    private GraphicsContext gc;
    private double paddleX;
    private List<Integer> mattoni = new ArrayList<Integer>();
    private World world;



    public GameScene(ViewController app) {
        this.app = app;
        this.primaryStage = app.primaryStage;
        
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        

        System.out.println("Righe: " + BRICK_ROWS + " Colonne: " + BRICK_COLUMNS);
        System.out.println(mattoni);   

        // Create root layout and add canvas and static components to it
        StackPane root = new StackPane(canvas);
        
        // Button
        Button pauseBUtton = new Button("Pause");
        pauseBUtton.setOnAction(e -> paused());
        pauseBUtton.setFocusTraversable(false);
        StackPane.setAlignment(pauseBUtton, javafx.geometry.Pos.TOP_RIGHT);                                   // Set the alignment of the button to the top-right corner       
        StackPane.setMargin(pauseBUtton, new javafx.geometry.Insets(10, 10, 0, 0));     // Set the margin of the button to create spacing from the top and right edges

        
        // Add static component to root layout
        root.getChildren().add(pauseBUtton);
        root.setAlignment(Pos.CENTER);

        // Create the scene to add graphical components
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
        scene.setOnKeyReleased(e -> handleKeyRelease(e.getCode()));

        // Set the title of the primary stage (window)
        //Stage primaryStage = new Stage();
        primaryStage.setTitle("Brick Breaker");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void update() { // Level level
        // Clear canvas
        gc.setFill(BACKGROUND_COLOR);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw paddle
        gc.setFill(PADDLE_COLOR);

        World world = this.app.controller.getModel().getWorld();
        var paddle = world.getBar().getPosition();
        gc.fillRect(paddle.getX(), HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);

        // Draw bricks 
        var bricks = world.getBricks();
        bricks.forEach(brick -> {
            gc.setFill(BRICK_COLOR[(brick.getLife() % BRICK_COLOR.length) -1]); // Brutto modo per evitare di sforre il vettore dei colori
            double brickX = brick.getPosition().getX();
            double brickY = brick.getPosition().getY();
            gc.fillRect(brickX, brickY, BRICK_WIDTH, BRICK_HEIGHT);
        });

        // LA DROW RIMANE COSI QUELLO CHE DEVE RITORNARE IL CONTROLLER SULL'UPDATE E' LA POSIZIONE DELLA PALLA
        // Draw ball
        var balls = world.getBalls();
        balls.forEach(ball -> {
            gc.setFill(BALL_COLOR);
            double ballX = ball.getPosition().getX();
            double ballY = ball.getPosition().getY();
            double ballR = ball.getRadius();
            gc.fillOval(ballX - ballR, ballY - ballR, ballR * 2, ballR * 2);
        });
    }

    private void handleKeyPress(KeyCode keyCode) {
        if (keyCode == KeyCode.LEFT) {
            world.getBar().move(-PADDLE_SPEED);
            paddleX -= PADDLE_SPEED;
            if (paddleX < 0) {
                paddleX = 0;
            }
        } else if (keyCode == KeyCode.RIGHT) {
            world.getBar().move(PADDLE_SPEED);
            paddleX += PADDLE_SPEED;
            if (paddleX > WIDTH - PADDLE_WIDTH) {
                paddleX = WIDTH - PADDLE_WIDTH;
            }
        } else if (keyCode == KeyCode.SPACE) {
            // Additional logic for space bar, if needed
            paused();   
        }
    }

    private void paused() {
        // Additional logic for pause, if needed
    }

    private void handleKeyRelease(KeyCode keyCode) {
        // Additional logic for key release, if needed
    }
}
