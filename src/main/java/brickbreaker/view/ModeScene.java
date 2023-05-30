package brickbreaker.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ModeScene extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button button = new Button();
        button.setPrefSize(200, 100);

        // Load the image
        Image image = new Image("file:main/resources/icons/.png");

        // Set background image using CSS
        button.setStyle("-fx-background-image: url('" + image.getUrl() + "');" +
                        "-fx-background-size: cover;");

        StackPane root = new StackPane(button);
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Button with Background Image");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


