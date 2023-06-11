package brickbreaker.view;

import java.util.Map;
import java.util.Map.Entry;

import brickbreaker.common.GameImages;
import brickbreaker.model.rank.GameRank;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class RanksView extends ViewImpl {

    @FXML
    private TableView<Map.Entry<String, Integer>> currentRank;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, String> columnPlayers;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, Integer> columnScores;

    @FXML
    private ImageView imgNext;

    @FXML
    private ImageView imgPrevious;

    @FXML
    private ImageView imgChangeEndlessLevel;

    private Image[] endlessLevels;
    private Integer endlessLevelsIndex;
    private Integer rankIndex;

    @Override
    public void init() {

        this.endlessLevelsIndex = 0;
        this.endlessLevels = new Image[2];
        this.endlessLevels[0] = GameImages.ENDLESS_MODE_CHOICE.getImage();
        this.endlessLevels[1] = GameImages.LEVELS_LABEL.getImage();

        this.imgNext.setImage(GameImages.NEXT.getImage());
        this.imgPrevious.setImage(GameImages.PREVIOUS.getImage());
        this.imgChangeEndlessLevel.setImage(GameImages.ENDLESS_MODE_CHOICE.getImage());

        this.rankIndex = 0;
        this.tableViewInit();
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
        this.imgChangeEndlessLevel.setImage(this.endlessLevels[this.endlessLevelsIndex]);
        this.rankIndex = 0;
    }

    public void clickNext() {
        GameRank r;
        Integer q;
        Integer e = this.getController().getRankController().getEndlessRankQuantity();
        Integer l = this.getController().getRankController().getLevelsRankQuantity();

        this.rankIndex++;
        q = this.endlessLevelsIndex == 0 ? e : l;

        if (q != 0) {
            this.rankIndex %= q;
            r = this.endlessLevelsIndex == 0 ? this.getController().getRankController().getEndlessRank(this.rankIndex) : this.getController().getRankController().getLevelsRank(this.rankIndex);
            this.bindData(r);
        }
    }

    public void bindData(final GameRank r) {
        ObservableList<Map.Entry<String, Integer>> obRank = FXCollections.observableArrayList(r.getRank().entrySet());
        this.currentRank.setItems(obRank);
    }
}
