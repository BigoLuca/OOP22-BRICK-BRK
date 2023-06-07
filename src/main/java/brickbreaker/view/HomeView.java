package brickbreaker.view;

import brickbreaker.controllers.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class HomeView extends ViewImpl {

    @FXML
    private ImageView title;

    @FXML
    private Button playButton;

    @FXML
    private Button endlessPlayButton;

    @FXML
    private Button globalRanksButton;

    public HomeView(final Controller controllerToAttach) {
        super(controllerToAttach);
    }
    
    @Override
    public void init() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }
    
}
