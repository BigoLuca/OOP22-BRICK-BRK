package brickbreaker.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PauseWindow {
    public static boolean display() {
        Stage window = new Stage();
        System.out.println("PauseWindow.display() called!");

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Pause");
        window.setMinWidth(250);

        // Labels
        Label label = new Label("Game paused!");

        // Buttons
        Button closeButton = new Button("Close this window");
        closeButton.setOnAction(e -> window.close());

        // Layout
        StackPane layout = new StackPane();
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        // Scene
        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.showAndWait();
        return true;
    }
}
