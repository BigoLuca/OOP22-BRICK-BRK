package brickbreaker.view;

import brickbreaker.common.GameImages;
import brickbreaker.common.Mode;
import brickbreaker.common.State;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class EndGameView extends ViewImpl {

    @FXML private AnchorPane root;

    @FXML private VBox vbContainer;
    
    @FXML
    private ImageView imgStatus;

    @FXML
    private ImageView btnContinue;

    @FXML
    private ImageView btnQuit;
    
    @Override
    public void init() {
        Image [] status = { GameImages.PLAYER_LOST.getImage(), GameImages.PLAYER_WIN.getImage() };

        Integer index;
        if (this.getController().getModel().getState() == State.LOST) {
            index = 0;
            this.btnContinue.setVisible(false);
        } else {
            index = 1;
        }

        this.imgStatus.setImage(status[index]);
        this.btnContinue.setImage(GameImages.CONTINUE.getImage());
        this.btnQuit.setImage(GameImages.QUIT.getImage());
    }

    public void clickContinue() {
        Mode m = this.getController().getMode();

        if (m.equals(Mode.ENDLESS)) {
            ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
        } else {
            if(this.getController().getModel().getState() == State.WIN) {
                if(this.getController().getLevelController().hasNextLevel()){
                    // Next level
                    this.getController().getLevelController().nextLevel();
                    this.getController().setModel();
                    ViewSwitcher.getInstance().switchView(getStage(), ViewType.MATCH);
                } else {
                    // Lvels are over
                    ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
                }
            } else {
                // Lost
                this.getController().setModel();
                ViewSwitcher.getInstance().switchView(getStage(), ViewType.MATCH);
            }
        }
    }

    public void clickQuit() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
    }
}
