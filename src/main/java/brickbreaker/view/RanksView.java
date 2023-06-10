package brickbreaker.view;

import java.util.Map;

import brickbreaker.common.GameImages;
import brickbreaker.model.rank.GameRank;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
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
    private ImageView imgChangeGlobalLocal;

    private Image[] endlessLevels;
    private Integer endlessLevelsIndex;
    private Integer rankIndex;

    @Override
    public void init() {

        this.endlessLevelsIndex = 0;
        this.endlessLevels = new Image[2];
        this.endlessLevels[0] = GameImages.GLOBAL_LABEL.getImage();
        this.endlessLevels[1] = GameImages.LOCAL_LABEL.getImage();

        this.imgNext.setImage(GameImages.NEXT.getImage());
        this.imgPrevious.setImage(GameImages.PREVIOUS.getImage());
        this.imgChangeGlobalLocal.setImage(GameImages.GLOBAL_LABEL.getImage());

        this.rankIndex = 0;
    }

    private void tableViewInit(final Font f) {
        this.currentRank.setEditable(false);
        this.columnPlayers.setEditable(false);
        this.columnScores.setEditable(false);
        this.columnPlayers.setSortable(false);
        this.columnScores.setSortable(false);

        this.columnPlayers.setCellFactory(new Callback<TableColumn<Map.Entry<String, Integer>, String>, TableCell<Map.Entry<String, Integer>, String>>() {

            @Override
            public TableCell<Map.Entry<String, Integer>, String> call(TableColumn<Map.Entry<String, Integer>, String> param) {
                return new TableCell<Map.Entry<String, Integer>, String>() {
                    @Override
                    public void updateItem(final String item, final boolean empty) {
                        super.updateItem(item, empty);

                        if(isEmpty()) {
                            setText("");
                        } else {
                            setFont(f);
                            setText(item);
                        }
                    }
                };
            }
        });

        this.columnScores.setCellFactory(new Callback<TableColumn<Map.Entry<String, Integer> ,Integer>,TableCell<Map.Entry<String, Integer>,Integer>>() {

            @Override
            public TableCell<Map.Entry<String, Integer>, Integer> call(TableColumn<Map.Entry<String, Integer>, Integer> param) {
                return new TableCell<Map.Entry<String, Integer>, Integer>() {
                    @Override
                    public void updateItem(final Integer item, final boolean empty) {
                        super.updateItem(item, empty);

                        if (isEmpty()) {
                            setText("");
                        } else {
                            setFont(f);
                            setText(Integer.toString(item));
                        }
                    }
                };
            }
            
        });

        //Binding the columns with the data.
        this.columnPlayers.setCellValueFactory(new PropertyValueFactory<Map.Entry<String, Integer>, String>("Player:"));
        this.columnScores.setCellValueFactory(new PropertyValueFactory<Map.Entry<String, Integer>, Integer>("Score"));

        //Adding a sort policy in the TableView.
        this.columnScores.setSortType(SortType.ASCENDING);
        this.currentRank.getSortOrder().add(columnScores);
        this.currentRank.sort();
    }

    public void clickGlobalLocal(final boolean levels) {
        this.endlessLevelsIndex = levels ? 0 : 1;
        this.imgChangeGlobalLocal.setImage(this.endlessLevels[this.endlessLevelsIndex]);
        this.rankIndex = 0;
    }

    public void clickNext() {
        GameRank r;

        this.rankIndex++;
        if (this.endlessLevelsIndex == 0) {
            this.rankIndex %= this.getController().getRankController().getLevelsRankQuantity();
            r = this.getController().getRankController().getLevelsRank(this.rankIndex);
        } else {
            this.rankIndex %= this.getController().getRankController().getEndlessRankQuantity();
            r = this.getController().getRankController().getEndlessRank(this.rankIndex);
        }

        ObservableList<Map.Entry<String, Integer>> obRank = FXCollections.observableArrayList(r.getRank().entrySet());
        this.currentRank.setItems(obRank);
    }

    @Override
    public void render() {
        
    }
}
