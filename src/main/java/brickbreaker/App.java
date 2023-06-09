package brickbreaker;

import brickbreaker.model.GameModelImpl;
import brickbreaker.view.ViewSwitcher;
import brickbreaker.view.ViewType;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main project class.
 */
public class App extends Application{

    /**
     * Entry point.
     * @param args command line args
     */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ResourceLoader.getInstance().start();
        ViewSwitcher.getInstance().switchView(primaryStage, ViewType.SETUP, new GameModelImpl());
    }

}
