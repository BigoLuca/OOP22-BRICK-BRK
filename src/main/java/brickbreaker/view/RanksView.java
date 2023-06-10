package brickbreaker.view;

import java.util.LinkedList;
import java.util.List;

import brickbreaker.ResourceLoader;
import brickbreaker.common.GameImages;
import brickbreaker.controllers.RankController;
import brickbreaker.model.rank.GameRank;
import brickbreaker.model.user.User;
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
    private TableView<User> currentRank;

    @FXML
    private TableColumn<User, String> columnPlayers;

    @FXML
    private TableColumn<User, Integer> columnScores;

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

        this.columnPlayers.setCellFactory(new Callback<TableColumn<User, String>, TableCell<User, String>>() {

            @Override
            public TableCell<User, String> call(TableColumn<User, String> param) {
                return new TableCell<User, String>() {
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

        this.columnScores.setCellFactory(new Callback<TableColumn<User,Integer>,TableCell<User,Integer>>() {

            @Override
            public TableCell<User, Integer> call(TableColumn<User, Integer> param) {
                return new TableCell<User, Integer>() {
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
        this.columnPlayers.setCellValueFactory(new PropertyValueFactory<User, String>("Player:"));
        this.columnScores.setCellValueFactory(new PropertyValueFactory<User, Integer>("Score"));

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
            this.rankIndex %= ((RankController) this.getController()).getLevelsRankQuantity();
            r = ((RankController) this.getController()).getEndlessRank(this.rankIndex);
        } else {
            this.rankIndex %= ((RankController) this.getController()).getEndlessRankQuantity();
            r = ((RankController) this.getController()).getEndlessRank(this.rankIndex);
        }

        ObservableList<User> obRank = FXCollections.observableList()
    }
}
