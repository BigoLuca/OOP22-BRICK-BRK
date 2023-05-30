package brickbreaker.view;

import java.util.ArrayList;
import java.util.List;

import brickbreaker.ResourceLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameWindow extends Application {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final int PADDLE_WIDTH = 600;
    private static final int PADDLE_HEIGHT = 20;
    private static final int BRICK_WIDTH = 80;
    private static final int BRICK_HEIGHT = 30;
    private static final int BALL_RADIUS = 10;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color PADDLE_COLOR = Color.WHITE;
    private static final Color BRICK_COLOR[] = {Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED, Color.PURPLE, Color.BLUE, Color.WHITE, Color.BLACK};
    private static final Color BALL_COLOR = Color.RED;
    private static final int BALL_SPEED = 5;
    private static final int PADDLE_SPEED = 20;
    private static int BRICK_ROWS = 5;
    private static int BRICK_COLUMNS = 8;

    private Canvas canvas;
    private GraphicsContext gc;
    private double paddleX;
    private double ballX;
    private double ballY;
    private double ballXSpeed;
    private double ballYSpeed;
    private Integer[][] bricks;
    private List<Integer> mattoni = new ArrayList<Integer>();
    private static final ResourceLoader rl = ResourceLoader.getInstance();

    

    public void start(Stage primaryStage) {
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        paddleX = (WIDTH - PADDLE_WIDTH) / 2;
        ballX = WIDTH / 2;
        ballY = HEIGHT - PADDLE_HEIGHT - BALL_RADIUS;
        ballXSpeed = BALL_SPEED;
        ballYSpeed = -BALL_SPEED;
        bricks = new Integer[BRICK_ROWS][BRICK_COLUMNS];

        // Initialize bricks        
        mattoni = (rl.loadMap("text.txt")).orElse(new ArrayList<Integer>());
        // TI ringrazio bigo di darmi una lista della quale non me ne faccio nulla        
        
        // Sti due metodi non funzionano o meglio sparano numeri strani?
        GameWindow.BRICK_ROWS = 6;      //GameWindow.BRICK_ROWS; NOT WORKING
        GameWindow.BRICK_COLUMNS = 4;   //GameWindow.BRICK_COLUMNS; NOT WORKING
        bricks = rl.convertToListArray(mattoni, BRICK_COLUMNS, BRICK_ROWS);

        System.out.println("Righe: " + BRICK_ROWS + " Colonne: " + BRICK_COLUMNS);
        System.out.println(mattoni);

        

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> update()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

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
        primaryStage.setTitle("Brick Breaker");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void update() {
        // Clear canvas
        gc.setFill(BACKGROUND_COLOR);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw paddle
        gc.setFill(PADDLE_COLOR);
        gc.fillRect(paddleX, HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);

        // Draw bricks 
        for (int row = 0; row < BRICK_ROWS; row++) {
            for (int col = 0; col < BRICK_COLUMNS; col++) {
                if (bricks[row][col] > 0) {
                    gc.setFill(BRICK_COLOR[(bricks[row][col] % BRICK_COLOR.length) -1]); // Brutto modo per evitare di sforre il vettore dei colori
                    double brickX = col * (BRICK_WIDTH + 2) + 1;
                    double brickY = row * (BRICK_HEIGHT + 2) + 1;
                    gc.fillRect(brickX, brickY, BRICK_WIDTH, BRICK_HEIGHT);
                }
            }
        }

        // LA DROW RIMANE COSI QUELLO CHE DEVE RITORNARE IL CONTROLLER SULL'UPDATE E' LA POSIZIONE DELLA PALLA
        // Draw ball
        gc.setFill(BALL_COLOR);
        gc.fillOval(ballX - BALL_RADIUS, ballY - BALL_RADIUS, BALL_RADIUS * 2, BALL_RADIUS * 2);
        // Update ball position da far fare al controller
        ballX += ballXSpeed;
        ballY += ballYSpeed;


        // Check for collision with paddle da far fare al controller non serivirà solo da ridisegnare mattoni
        if (ballY + BALL_RADIUS >= HEIGHT - PADDLE_HEIGHT && ballY + BALL_RADIUS <= HEIGHT &&
                ballX >= paddleX && ballX <= paddleX + PADDLE_WIDTH) {
            ballYSpeed = -BALL_SPEED;
        }

        // Check for collision with bricks da far fare al controller
        // TO DO USING PROVIDED METHOD!!!!!!!!!
        for (int row = 0; row < BRICK_ROWS; row++) {
            for (int col = 0; col < BRICK_COLUMNS; col++) {
                if (bricks[row][col] > 0) {
                    double brickX = col * (BRICK_WIDTH + 2) + 1;
                    double brickY = row * (BRICK_HEIGHT + 2) + 1;
                    if (ballX + BALL_RADIUS >= brickX && ballX - BALL_RADIUS <= brickX + BRICK_WIDTH &&
                            ballY + BALL_RADIUS >= brickY && ballY - BALL_RADIUS <= brickY + BRICK_HEIGHT) {
                        bricks[row][col] -= 1;
                        ballYSpeed = -ballYSpeed;
                    }
                }
            }
        }

        // Check for collision with walls
        if (ballX + BALL_RADIUS >= WIDTH || ballX - BALL_RADIUS <= 0) {
            ballXSpeed = -ballXSpeed;
        }
        if (ballY - BALL_RADIUS <= 0) {
            ballYSpeed = -ballYSpeed;
        }

        // Check for game over
        if (ballY + BALL_RADIUS >= HEIGHT) {
            // Game over logic here
            System.out.println("Game Over");
        }
    }

    private void handleKeyPress(KeyCode keyCode) {
        if (keyCode == KeyCode.LEFT) {
            paddleX -= PADDLE_SPEED;
            if (paddleX < 0) {
                paddleX = 0;
            }
        } else if (keyCode == KeyCode.RIGHT) {
            paddleX += PADDLE_SPEED;
            if (paddleX > WIDTH - PADDLE_WIDTH) {
                paddleX = WIDTH - PADDLE_WIDTH;
            }
        } else if (keyCode == KeyCode.SPACE) {
            // Additional logic for space bar, if needed
            paused();   
        }
    }

    private void handleKeyRelease(KeyCode keyCode) {
        // Additional logic for key release, if needed
    }

    private void paused() {
        // Additional logic for pause, if needed

        // Blocco il gioco TODO
        PauseWindow.display();
        // Riprendo il gioco TODO (con timer?)
    }
    public static void main(String[] args) {
        launch(args);
    }
}
