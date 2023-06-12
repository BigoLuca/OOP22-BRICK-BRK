package brickbreaker.view;

import brickbreaker.common.GameImages;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeView extends ViewImpl {

    @FXML
    private AnchorPane root;

    @FXML
    private VBox vbAnchor;

    @FXML
    private HBox hBTitle;

    @FXML
    private VBox vbButtons;

    @FXML
    private ImageView imgTitle;

    @FXML
    private ImageView imgLevel;

    @FXML
    private ImageView imgEndless;

    @FXML
    private ImageView imgLeaderboards;

    @FXML
    private ImageView imgBack;
    
    @Override
    public void init() {

        this.imgTitle.setImage(GameImages.TITLE.getImage());
        this.imgLevel.setImage(GameImages.LEVELS_MODE_CHOICE.getImage());
        this.imgEndless.setImage(GameImages.ENDLESS_MODE_CHOICE.getImage());
        this.imgLeaderboards.setImage(GameImages.LEADERBOARD_CHOICE.getImage());
        this.imgBack.setImage(GameImages.PREVIOUS.getImage());
    }

    public void switchToLevel() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.LEVEL);
    }

    public void switchToEndless() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.DIFFICULTY);
    }

    public void switchToLeaderboards() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.RANK);
    }

    public void clickBack() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.SETUP);
    }
}
