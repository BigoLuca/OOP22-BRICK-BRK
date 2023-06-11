package brickbreaker.view;

import brickbreaker.common.GameImages;
import brickbreaker.common.Mode;
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
    
    @Override
    public void init() {

        this.imgTitle.setImage(GameImages.TITLE.getImage());
        this.imgLevel.setImage(GameImages.LEVELS_MODE_CHOICE.getImage());
        this.imgEndless.setImage(GameImages.ENDLESS_MODE_CHOICE.getImage());
        this.imgLeaderboards.setImage(GameImages.LEADERBOARD_CHOICE.getImage());
    }

    public void switchToLevel() {
        this.mode = Mode.LEVELS;
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.LEVEL);
    }

    public void switchToEndless() {
        this.mode = Mode.ENDLESS;
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.DIFFICULTY);
    }

    public void switchToLeaderboards() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.RANK);
    }
}
