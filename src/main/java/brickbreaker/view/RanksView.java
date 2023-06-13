package brickbreaker.view;

import java.util.Map;
import java.util.Map.Entry;

import brickbreaker.common.Difficulty;
import brickbreaker.common.GameImages;
import brickbreaker.model.rank.GameRank;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class RanksView extends ViewImpl {

    @FXML
    private TableView<Map.Entry<String, Integer>> currentRank;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, String> columnPlayers;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, Integer> columnScores;

    @FXML
    private VBox vbContainer;

    @FXML
    private ImageView imgTitle;

    @FXML
    private Label lblTitle;

    @FXML
    private ImageView imgNext;

    @FXML
    private ImageView imgPrevious;

    @FXML
    private ImageView imgBack;

    @FXML
    private ImageView imgChange;

    private Image[] endlessLevels;
    private Integer endlessLevelsIndex;
    private Integer rankIndex;

    @Override
    public void init() {


        this.endlessLevelsIndex = 0;
        this.endlessLevels = new Image[2];
        this.endlessLevels[0] = GameImages.ENDLESS_MODE_CHOICE.getImage();
        this.endlessLevels[1] = GameImages.LEVELS_LABEL.getImage();

        //this.imgBack.setImage(GameImages.PREVIOUS.getImage());
        this.imgNext.setImage(GameImages.RIGHT_ARROW.getImage());
        this.imgPrevious.setImage(GameImages.LEFT_ARROW.getImage());
        this.imgChange.setImage(GameImages.ENDLESS_MODE_CHOICE.getImage());
        this.imgTitle.setImage(GameImages.ENDLESS_MODE_CHOICE.getImage());
        this.imgBack.setImage(GameImages.BACK_ARROW.getImage());
        
        this.rankIndex = 0;
        this.tableViewInit();
        this.setRank();
    }

    private void tableViewInit() {
        this.currentRank.setEditable(false);
        this.columnPlayers.setEditable(false);
        this.columnScores.setEditable(false);
        this.columnPlayers.setSortable(false);
        this.columnScores.setSortable(false);

        this.columnPlayers.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(CellDataFeatures<Entry<String, Integer>, String> param) {
                return new SimpleStringProperty(param.getValue().getKey());
            }
            
        });

        this.columnScores.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Integer>, Integer>, ObservableValue<Integer>>() {

            @Override
            public ObservableValue<Integer> call(CellDataFeatures<Entry<String, Integer>, Integer> param) {
                return new SimpleIntegerProperty(param.getValue().getValue()).asObject();
            }

        });

        //Loading an initial rank.
        this.bindData(this.getController().getRankController().getEndlessRank(0));
        
    }

    public void changeMode() {
        this.endlessLevelsIndex = this.endlessLevelsIndex == 0 ? 1 : 0;
        this.imgChange.setImage(this.endlessLevels[this.endlessLevelsIndex]);
        this.imgChange.setImage(this.endlessLevels[this.endlessLevelsIndex]);
        this.rankIndex = 0;
        setRank();
    }

    private Integer getMaxIndex(){
        return this.endlessLevelsIndex == 0 ? this.getController().getRankController().getEndlessRankQuantity() : this.getController().getRankController().getLevelsRankQuantity();
    }

    private void setRank(){
        GameRank r;
        Integer q = getMaxIndex();
        if (q != 0) {
            this.rankIndex %= q;

            if (this.endlessLevelsIndex == 0) {
                r = this.getController().getRankController().getEndlessRank(this.rankIndex);
            } else {
                r = this.getController().getRankController().getLevelsRank(this.rankIndex);
            }

            String s = this.endlessLevelsIndex == 0 ? Difficulty.values()[this.rankIndex].toString() : this.getController().getLevelController().getLevelName(this.rankIndex);
            this.bindData(r);
            this.lblTitle.setText(s);
        }
       
    }

    public void clickNext() {
        this.rankIndex++;
        setRank();
    }

    public void clickPrevious() {
        if (this.rankIndex == 0) {
            this.rankIndex = getMaxIndex();
        } else {
            this.rankIndex--;
        }
        setRank();
    }

    public void clickBack() {
        ViewSwitcher.getInstance().switchView(this.getStage(), ViewType.HOME);
    }

    public void bindData(final GameRank r) {
        ObservableList<Map.Entry<String, Integer>> obRank = FXCollections.observableArrayList(r.getRank().entrySet());
        this.currentRank.setItems(obRank);
    }
}
