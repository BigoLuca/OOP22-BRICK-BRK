package brickbreaker.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PlayerScene extends Application {

    private List<String> players;

    @Override
    public void start(Stage primaryStage) {
        // Create a VBox to hold the components
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setPadding(new Insets(10));

        // Create a label to display the title
        final Label titleLabel = new Label("SELECT A PLAYER");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setFont(new javafx.scene.text.Font("Calibri", 40));
        root.getChildren().add(titleLabel);

        // Create a list of players
        players = new ArrayList<>();
        players.add("Player 1");
        players.add("Player 2");
        players.add("Player 3");
        players.add("Player 4");
        // Add more players if needed

        // Create buttons for player selection
        for (String player : players) {
            Button playerButton = createPlayerButton(player);
            root.getChildren().add(playerButton);
        }

        // Create a scene with the root VBox
        Scene scene = new Scene(root, 400, 300);

        // Set the scene on the primary stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Player Selection");
        primaryStage.show();
    }

    private Button createPlayerButton(String player) {
        Button playerButton = new Button(player);
        playerButton.setPrefWidth(200);
        playerButton.setPrefHeight(100);
        playerButton.setStyle("-fx-text-fill: black; -fx-background-color: darkgrey;");
        // Hover effect
        playerButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                playerButton.setStyle("-fx-text-fill: black; -fx-background-color: grey;");
            } else {
                playerButton.setStyle("-fx-text-fill: black; -fx-background-color: darkgrey;");
            }
        });
        

        playerButton.setOnAction(event -> {
            Button clickedButton = (Button) event.getSource();
            String selectedPlayer = clickedButton.getText();
            System.out.println("Selected Player: " + selectedPlayer);
            // You can perform further actions based on the selected player
        });

        return playerButton;
    }

}


