package brickbreaker;

import brickbreaker.view.JavaFXApp;
import brickbreaker.view.ViewSwitcher;
import brickbreaker.view.ViewType;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main project class.
 */
public final class App extends Application {

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewSwitcher.getInstance().switchView(primaryStage, ViewType.HOME, );
    }

    
}
