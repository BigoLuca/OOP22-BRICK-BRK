package brickbreaker;

import javafx.application.Application;

/**
 * Launcher class.
 */
public final class Launcher {

    private Launcher() { }

    /**
     * Main method.
     * @param args Arguments.
     */
    public static void main(final String[] args) {
        Application.launch(App.class, args);
    }
}
