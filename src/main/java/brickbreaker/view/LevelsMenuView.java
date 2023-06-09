package brickbreaker.view;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import brickbreaker.ResourceLoader;
import brickbreaker.common.Difficulty;
import brickbreaker.common.GameImages;
import brickbreaker.controllers.LevelMenuController;
import brickbreaker.controllers.session.EndlessSession;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public final class LevelsMenuView extends ViewImpl {

    @FXML private AnchorPane root;
    @FXML private VBox vbContainer;
    @FXML private ImageView imgChooseLevel;
    @FXML private GridPane gplevelsGrid;
    @FXML private HBox hbButtons;
    @FXML private ImageView imgGoBack;
    @FXML private ImageView imgGoForward;

    private List<String> mapsName;
    private List<GameImages> landscapes;
    private Image currentLandscapeSelected;
    private String currentLevelSelected;
    private Integer rowIndex;
    private Integer columnIndex;

    @Override
    public void init() {
        this.getController().init();

        mapsName = ((LevelMenuController) this.getController()).getMapsName();
        landscapes = ((LevelMenuController) this.getController()).getLandscapes();
        rowIndex = columnIndex = 0;

        this.imgChooseLevel.setImage(GameImages.PICK_A_LEVEL.getImage());
        this.imgGoBack.setImage(GameImages.LEFT_ARROW.getImage());
        this.imgGoForward.setImage(GameImages.RIGHT_ARROW.getImage());
        
        refreshLevels(true);
    }

    private void refreshLevels(final boolean forward) {

        if (!forward) {
            this.columnIndex -= 2;
            this.rowIndex -= 4;
        }

        try {
            for (Integer i = 0; i < this.gplevelsGrid.getRowCount(); i++) {
                for (Integer j = 0; j < this.gplevelsGrid.getColumnCount(); j++) {
                    ImageView imgLevel = new ImageView(landscapes.get(i * j).getImage());
                    Label mapName = new Label(mapsName.get(i + j));
                    VBox levelControl = new VBox();

                    imgLevel.setPreserveRatio(true);
                    imgLevel.setFitHeight(82.0);
                    imgLevel.setFitWidth(100.0);
    
                    levelControl.getChildren().addAll(imgLevel, mapName);
                    levelControl.setOnMouseEntered(new EventHandler<Event>() {
    
                        @Override
                        public void handle(Event event) {
                            currentLevelSelected = ((Label) levelControl.getChildren().get(1)).getText();
                            currentLandscapeSelected = ((ImageView) levelControl.getChildren().get(0)).getImage();
                        }
                        
                    });
    
                    this.gplevelsGrid.getChildren().add(i + j, levelControl);
                    this.columnIndex++;
                }
                this.rowIndex++;
            }
        } catch(ArrayIndexOutOfBoundsException a) {
            System.out.println("Level loading endend.");
        }
    }

    public void clickGoForward() {
        refreshLevels(true);
    }

    public void clickBackForward() {
        refreshLevels(false);
    }

    public void switchToLevelMatch() {
        Optional<Difficulty> d = ResourceLoader.getInstance().getMapDifficulty(this.currentLevelSelected);
        this.getController().setSession(new EndlessSession(d));
        this.getController().getModel().createLevel(0);
    }
}
