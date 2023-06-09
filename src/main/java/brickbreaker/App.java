package brickbreaker;

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
    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            ResourceLoader.getInstance().start();
        } catch (Exception e) {
            System.out.println("Error while loading resources");
        }

        
        ViewSwitcher.getInstance().switchView(primaryStage, ViewType.SETUP);
    }

}
