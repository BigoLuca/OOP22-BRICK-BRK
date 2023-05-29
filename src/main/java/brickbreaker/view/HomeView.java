package brickbreaker.view;

import brickbreaker.controllers.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class HomeView extends ViewImpl {

    @FXML
    private ImageView title;

    @FXML
    private Button btnLevelMode;

    @FXML
    private Button btnEndless;

    @FXML
    private Button btnLeaderBoards;

    public HomeView(final Controller controllerToAttach) {
        super(controllerToAttach);
    }
    
    @Override
    public void init() {

    }

    public void switchToPlay() {
        ViewSwitcher.getInstance().switchView(this.getStage(), ViewType.SET_UP);
    }

    public void switchToRank() {
        ViewSwitcher.getInstance().switchView(this.getStage(), ViewType.RANK);
    }
}
