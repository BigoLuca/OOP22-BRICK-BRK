package brickbreaker;

import brickbreaker.view.JavaFXApp;
import javafx.application.Application;

/**
 * Main project class.
 */
public final class App {

    private App() { }

    /**
     * Entry point.
     * @param args command line args
     */
    public static void main(final String[] args) throws Exception {
        Application.launch(JavaFXApp.class, args);
    }
    


}
