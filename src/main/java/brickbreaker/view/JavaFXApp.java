package brickbreaker.view;

import brickbreaker.controllers.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Sample JavaFX application.
 */
public final class JavaFXApp extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {

        /*
        new PlayerScene().start(primaryStage);

        // Label
        final Label message = new Label("Hello, JavaFX!"); 
        message.setFont(new Font(100));

        // Button
        final Button button = new Button("Click me!");
        button.setOnAction(event -> {
            message.setText("Hello, World!");
            button.setText("Clicked!");
            PauseWindow.display();
        });
        */

        Controller controller = new Controller();
        Thread thread = new Thread(() -> controller.createEndless());
        thread.start();
        UserScene scene = new UserScene();
        scene.show(primaryStage, controller);
        
    }
}
