package brickbreaker.view;

import brickbreaker.common.GameImages;
import brickbreaker.controllers.GameController;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class RanksView extends ViewImpl {

    @FXML
    private TableView<PlayerStats> currentRank;

    @FXML
    private TableColumn<PlayerStats, String> columnPlayers;

    @FXML
    private TableColumn<PlayerStats, Integer> columnScores;

    @FXML
    private ImageView imgNext;

    @FXML
    private ImageView imgPrevious;

    @FXML
    private ImageView imgChangeGlobalLocal;

    public RanksView(GameController controllerToAttach) {
        super(controllerToAttach);
    }

    @Override
    public void init() {
        this.imgNext.setImage(GameImages.NEXT.getImage());
        this.imgPrevious.setImage(GameImages.PREVIOUS.getImage());
        this.imgChangeGlobalLocal.setImage(GameImages.GLOBAL_LABEL.getImage());
    }

    private void tableViewInit(final Font f) {
        this.currentRank.setEditable(false);
        this.columnPlayers.setEditable(false);
        this.columnScores.setEditable(false);
        this.columnPlayers.setSortable(false);
        this.columnScores.setSortable(false);

        this.columnPlayers.setCellFactory(new Callback<TableColumn<PlayerStats, String>, TableCell<PlayerStats, String>>() {

            @Override
            public TableCell<PlayerStats, String> call(TableColumn<PlayerStats, String> param) {
                return new TableCell<PlayerStats, String>() {
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

        this.columnScores.setCellFactory(new Callback<TableColumn<PlayerStats,Integer>,TableCell<PlayerStats,Integer>>() {

            @Override
            public TableCell<PlayerStats, Integer> call(TableColumn<PlayerStats, Integer> param) {
                return new TableCell<PlayerStats, Integer>() {
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
        this.columnPlayers.setCellValueFactory(new PropertyValueFactory<PlayerStats, String>("Player:"));
        this.columnScores.setCellValueFactory(new PropertyValueFactory<PlayerStats, Integer>("Score"));

        //Adding a sort policy in the TableView.
        this.columnScores.setSortType(SortType.ASCENDING);
        this.currentRank.getSortOrder().add(columnScores);
        this.currentRank.sort();
    }
}
