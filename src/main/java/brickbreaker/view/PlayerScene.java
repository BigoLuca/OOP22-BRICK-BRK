package brickbreaker.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;

public class PlayerScene {

    private final JavaFXApp switcher;
    private List<String> players;

    public PlayerScene(JavaFXApp switcher){
        this.switcher = switcher;
    }

    public void show() {
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

        // Create a Button for add a new player
        HBox addBox = new HBox();
        TextField addText = new TextField("Add a new player: ");
        addText.setStyle("-fx-text-fill: black; -fx-background-color: darkgrey;");
        Button addPlayer = new Button("NEW");
        addPlayer.setOnAction(event ->{
            String newPlayer = addText.getText();
            if (newPlayer != null && !newPlayer.isEmpty()) {
                this.switcher.controller.getUserController().addUser(newPlayer);
                show();
            }
        });
        addBox.getChildren().addAll(addText, addPlayer);
        root.getChildren().add(addBox);



        // Get a list of players
        players = this.switcher.controller.getUserController().getUsersName();

        // Create buttons for player selection
        for (String player : players) {
            Button playerButton = createPlayerButton(player);
            root.getChildren().add(playerButton);
        }

        // Create a scene with the root VBox
        Scene scene = new Scene(root, 400, 300);

        // Set the scene on the primary stage
        this.switcher.primaryStage.setScene(scene);
        this.switcher.primaryStage.setTitle("Player Selection");
        this.switcher.primaryStage.show();
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
            this.switcher.switchToModeScene();
            // You can perform further actions based on the selected player
        });

        return playerButton;
    }

}


