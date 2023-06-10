package brickbreaker.view;

import java.util.Optional;

import brickbreaker.common.Difficulty;
import brickbreaker.common.GameImages;
import brickbreaker.common.Mode;
import brickbreaker.common.State;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EndGameView extends ViewImpl {

    @FXML
    private ImageView imgStatus;

    @FXML
    private ImageView btnContinue;

    @FXML
    private ImageView btnQuit;
    
    @Override
    public void init() {
        Image [] status = { GameImages.PLAYER_LOST.getImage(), GameImages.PLAYER_WIN.getImage() };
        Integer index = this.getController().getModel().getState() == State.LOST ? 0 : 1;
        this.imgStatus.setImage(status[index]);
    }

    public void clickContinue() {
        Mode m = this.getController().getModel().getMode();

        if (m.equals(Mode.ENDLESS)) {
            ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
        } else {
            if(this.getController().getModel().getState() == State.WIN) {
                if(this.getController().getLevelController().hasNextLevel()){
                    // Next level
                    this.getController().getLevelController().nextLevel();
                    this.getController().setModel(Mode.LEVELS);
                    ViewSwitcher.getInstance().switchView(getStage(), ViewType.MATCH);
                } else {
                    // Lvels are over
                    ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
                }
            } else {
                // Lost
                this.getController().setModel(Mode.LEVELS);
                ViewSwitcher.getInstance().switchView(getStage(), ViewType.MATCH);
            }
        }
    }

    public void clickQuit() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
    }
}
