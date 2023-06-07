package brickbreaker.view;

import java.util.Optional;
import brickbreaker.ResourceLoader;
import brickbreaker.common.Difficulty;
import brickbreaker.common.GameImages;
import brickbreaker.controllers.Controller;
import brickbreaker.model.GameModel;
import brickbreaker.model.rank.GameRank;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EndlessMenuView extends ViewImpl {

    @FXML
    private ImageView imgChooseDifficulty;

    @FXML
    private ImageView imgUpArrow;

    @FXML
    private ImageView imgDownArrow;

    @FXML
    private ImageView imgDifficulty;

    @FXML
    private ImageView imgReadyGo;

    private Image[] imgDifficulties;
    private Integer difficultyIndex;

    public EndlessMenuView(Controller controllerToAttach) {
        super(controllerToAttach);
    }

    @Override
    public void init() {
        this.difficultyIndex = 0;
        this.imgDifficulties = new Image[4];

        this.imgDifficulties[3] = GameImages.MIX_DIFFICULTY.getImage();
        this.imgDifficulties[0] = GameImages.EASY_DIFFICULTY.getImage();
        this.imgDifficulties[1] = GameImages.MEDIUM_DIFFICULTY.getImage();
        this.imgDifficulties[2] = GameImages.HARD_DIFFICULTY.getImage();
    }

    public void chooseDifficulty(final boolean up) {
        Integer d = up ? 1 : -1;
        this.difficultyIndex = (this.difficultyIndex + d) % 4;
    }

    public void clickUpArrow() {
        chooseDifficulty(false);
    }

    public void clickDownArrow() {
        chooseDifficulty(true);
    }

    public void clickPlayButton() {
        Optional<Difficulty> od;

        if (this.difficultyIndex == 3) {
            od = Optional.empty();
        } else {
            od = Optional.of(Difficulty.values()[this.difficultyIndex]);
        }

        GameRank g = new GameRank(10, ResourceLoader.getInstance().getRank("endless.txt"));
        
    }
}
