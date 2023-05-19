package brickbreaker.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Sample JavaFX application.
 */
public final class JavaFXApp extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {

        new GameWindow().start(primaryStage);

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


        
        // Layout
        StackPane layout = new StackPane();
        layout.getChildren().add(message);
        layout.getChildren().add(button);
        
        // Scene
        Scene scene = new Scene(layout, 800, 600);
        //primaryStage.setScene(scene);
        primaryStage.setTitle("Game Window");
        primaryStage.show();
    }

    /**
     * Program's entry point.
     * @param args
     */
    public static void run(final String... args) {
        launch(args);
    }

    // Defining the main methods directly within JavaFXApp may be problematic:
    // public static void main(final String[] args) {
    //        run();
    // }

    /**
     * Entry point's class.
     */
    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        /**
         * Program's entry point.
         * @param args
         */
        public static void main(final String... args) {
            Application.launch(JavaFXApp.class, args);
            /* 
            The following line raises: Error: class it.unibo.samplejavafx.JavaFXApp$Main 
            is not a subclass of javafx.application.Application
            Because if you do not provide the Application subclass to launch() it will consider the enclosing class)
            */
            // JavaFXApp.launch(args);
            // Whereas the following would do just fine:
            // JavaFXApp.run(args)
        }
    }
}
