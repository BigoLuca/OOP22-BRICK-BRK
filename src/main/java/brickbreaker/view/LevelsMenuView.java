package brickbreaker.view;

import java.io.FileNotFoundException;
import java.io.IOException;

import brickbreaker.ResourceLoader;
import brickbreaker.controllers.Controller;
import brickbreaker.model.GameModel;
import brickbreaker.model.rank.GameRank;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public final class LevelsMenuView extends ViewImpl {

    @FXML
    private ImageView imgChooseLevel;

    @FXML
    private GridPane gplevelsGrid;

    public LevelsMenuView(final Controller controllerToAttach) {
        super(controllerToAttach);
        
        Integer levelsQuantity = ResourceLoader.getInstance().getMapsNames().size();

        for (Integer i = 0; i < levelsQuantity - 5; i++) {
            for (Integer j = 0; j < 5; j++) {
                try {
                    Image img = ResourceLoader.getInstance().getLevelImage(j*i).getImage();
                    ImageView levelControl = new ImageView(img);
                    levelControl.setOnMouseClicked(new EventHandler<Event>() {

                        @Override
                        public void handle(Event event) {
                            switchToLevelMatch();
                        }
                        
                    });
                    
                    this.gplevelsGrid.add(levelControl, j, i, 0, 0);
                } catch (FileNotFoundException  e) {
                    System.out.println("[ERROR] Some images are not present.");
                    System.out.println("Some files may have been corrupted.");
                } catch (IOException e) {
                    System.out.println("[ERROR] Occured while loading some images.");
                    System.out.println("Exit the program and relaunch it.");
                }
            }
        }
    }

    public void switchToLevelMatch() {
        GameRank r = new GameRank(10, ResourceLoader.getInstance().getRank("levels.txt"));
        this.getController().setModel(new LevelsModel(r, null));
        ViewSwitcher.getInstance().switchView(this.getStage(), ViewType.MATCH);
    }

    @Override
    public void init() {

    }
    
}
