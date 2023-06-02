package brickbreaker.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

public class ModeScene {

    private final JavaFXApp app;

    public ModeScene(JavaFXApp app){
        this.app = app;
    }

    public void show() {
        Button button = new Button();
        button.setPrefSize(200, 100);

        // Load the image
        Image image = new Image("file:main/resources/icons/.png");

        // Set background image using CSS
        button.setStyle("-fx-background-image: url('" + image.getUrl() + "');" +
                        "-fx-background-size: cover;");

        StackPane root = new StackPane(button);
        Scene scene = new Scene(root, 400, 200);

        app.primaryStage.setScene(scene);
        app.primaryStage.setTitle("Button with Background Image");
        app.primaryStage.show();
    }
}


