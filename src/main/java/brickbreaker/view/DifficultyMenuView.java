package brickbreaker.view;

import brickbreaker.common.Difficulty;
import brickbreaker.common.GameImages;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DifficultyMenuView extends ViewImpl {

    @FXML
    private AnchorPane root;

    @FXML
    private VBox vbLayout;

    @FXML
    private ImageView imgEndlessMode;

    @FXML
    private HBox hbDifficulty;

    @FXML
    private ImageView imgDifficulty;

    @FXML
    private ImageView imgSelectedDifficulty;

    @FXML
    private VBox vbDifficultySelection;

    @FXML
    private ImageView imgUpArrow;

    @FXML
    private ImageView imgDownArrow;

    @FXML
    private ImageView imgReady;

    private Image[] imgDifficulties;
    private Integer difficultyIndex;

    @Override
    public void init() {
        this.difficultyIndex = 0;
        this.imgDifficulties = new Image[4];

        this.imgDifficulties[3] = GameImages.MIX_DIFFICULTY.getImage();
        this.imgDifficulties[0] = GameImages.EASY_DIFFICULTY.getImage();
        this.imgDifficulties[1] = GameImages.MEDIUM_DIFFICULTY.getImage();
        this.imgDifficulties[2] = GameImages.HARD_DIFFICULTY.getImage();

        this.imgSelectedDifficulty.setImage(this.imgDifficulties[0]);

        this.imgUpArrow.setImage(GameImages.UP_ARROW.getImage());
        this.imgDownArrow.setImage(GameImages.DOWN_ARROW.getImage());
    }

    public void chooseDifficulty(final boolean up) {
        Integer d = up ? 1 : (difficultyIndex == 0 ? 3 : -1);
        this.difficultyIndex = (this.difficultyIndex + d) % 4;
        this.imgSelectedDifficulty.setImage(this.imgDifficulties[this.difficultyIndex]);
    }

    public void clickUpArrow() {
        chooseDifficulty(false);
    }

    public void clickDownArrow() {
        chooseDifficulty(true);
    }

    public void clickPlayButton() {
        System.out.println("Difficulty: " + Difficulty.values()[this.difficultyIndex]);
        this.getController().getLevelController().setDifficultyLevel(Difficulty.values()[this.difficultyIndex]);
        this.getController().setModel(this.mode);
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.MATCH);
    }
}
