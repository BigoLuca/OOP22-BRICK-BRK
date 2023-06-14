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
public class App extends Application {

    /**
     * Entry point.
     * @param primaryStage
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        try {
            ResourceLoader.getInstance().start();
        } catch (Exception e) {
            System.out.println("Error while loading resources");
        }

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Double d = 3.5;
        Integer b = 20;
        double centerX = bounds.getMaxX() / d;
        double centerY = bounds.getMinY() + b;

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
