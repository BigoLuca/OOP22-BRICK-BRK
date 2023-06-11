package brickbreaker.view;

import java.util.Optional;

import brickbreaker.MapInfo;
import brickbreaker.common.GameImages;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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

    private String currentLevelSelected;
    private Integer rowIndex;
    private Integer columnIndex;

    @Override
    public void init() {
        rowIndex = columnIndex = 0;

        this.imgChooseLevel.setImage(GameImages.PICK_A_LEVEL.getImage());
        this.imgGoBack.setImage(GameImages.LEFT_ARROW.getImage());
        this.imgGoForward.setImage(GameImages.RIGHT_ARROW.getImage());

        this.gplevelsGrid.setVgap(30);
        this.refreshLevels(true);
    }

    private void refreshLevels(final boolean forward) {

        if (!forward) {
            this.columnIndex -= 2;
            this.rowIndex -= 4;
        }

        try {
            for (Integer i = this.rowIndex; i < this.gplevelsGrid.getRowCount(); i++) {
                for (Integer j = this.columnIndex; j < this.gplevelsGrid.getColumnCount(); j++) {

                    MapInfo m = this.getController().getLevelController().getMapInfo(i + j);
                    ImageView imgLevel = new ImageView(m.getLandscapeData().getImage());
                    Label mapName = new Label(m.getName());
                    VBox levelControl = new VBox();

                    imgLevel.setPreserveRatio(true);
                    imgLevel.setFitHeight(82.0);
                    imgLevel.setFitWidth(100.0);

                    levelControl.setAlignment(Pos.CENTER);
                    levelControl.getChildren().addAll(imgLevel, mapName);
                    levelControl.setOnMouseEntered(new EventHandler<Event>() {
    
                        @Override
                        public void handle(Event event) {
                            currentLevelSelected = ((Label) levelControl.getChildren().get(1)).getText();
                            System.out.println(currentLevelSelected);
                        }
                        
                    });

                    levelControl.setOnMouseClicked(new EventHandler<Event>() {

                        @Override
                        public void handle(Event event) {
                            switchToLevelMatch();
                        }
                        
                    });
    
                this.gplevelsGrid.add(levelControl, j, i);
                    this.columnIndex++;
                }
                this.rowIndex++;
            }
        } catch(ArrayIndexOutOfBoundsException a) {
            System.out.println("Levels loading endend.");
        }
    }

    public void clickGoForward() {
        refreshLevels(true);
    }

    public void clickBackForward() {
        refreshLevels(false);
    }

    public void switchToLevelMatch() {
        this.getController().getLevelController().setLevel((Optional.of(this.getController().getLevelController().getMapIndex(currentLevelSelected))));
        this.getController().setModel();
        ViewSwitcher.getInstance().switchView(this.getStage(), ViewType.MATCH);
    }
}
