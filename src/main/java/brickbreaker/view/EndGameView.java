package brickbreaker.view;

import java.util.Optional;

import brickbreaker.common.Difficulty;
import brickbreaker.common.GameImages;
import brickbreaker.common.Mode;
import brickbreaker.common.State;
import brickbreaker.controllers.session.EndlessSession;
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
        Integer index = this.getController().getModel().getLevel().getState() == State.LOST ? 0 : 1;
        this.imgStatus.setImage(status[index]);
    }

    public void clickContinue() {
        Mode m = this.getController().getModel().getMode();

        if (m.equals(Mode.ENDLESS)) {
            Optional<Difficulty> od = ((EndlessSession) this.getController().getSession()).getDifficulty();
            this.getController().getModel().createRandomLevel(od.get());
        } else {
            Integer index = this.getController().getModel().getLevel().getId() + 1;
            this.getController().getModel().createLevel(index);
        }

        ViewSwitcher.getInstance().switchView(getStage(), ViewType.MATCH, this.getController().getModel());
    }

    public void clickQuit() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME, this.getController().getModel());
    }
}
