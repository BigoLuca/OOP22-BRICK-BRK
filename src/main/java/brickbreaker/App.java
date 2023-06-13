package brickbreaker;

import brickbreaker.view.ViewSwitcher;
import brickbreaker.view.ViewType;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Main project class.
 */
public class App extends Application{

    /**
     * Entry point.
     * @param args command line args
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            ResourceLoader.getInstance().start();
        } catch (Exception e) {
            System.out.println("Error while loading resources");
        }
        
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        double centerX = bounds.getMaxX() / 3.5;
        double centerY = bounds.getMinY() + 20;

        primaryStage.setResizable(true);
        primaryStage.setX(centerX);
        primaryStage.setY(centerY);

        primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });

        ViewSwitcher.getInstance().switchView(primaryStage, ViewType.SETUP);

        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
