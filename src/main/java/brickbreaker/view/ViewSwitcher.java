package brickbreaker.view;

import java.io.IOException;

import brickbreaker.ResourceLoader;
import brickbreaker.common.GameImages;
import brickbreaker.controllers.Controller;
import brickbreaker.model.GameModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public final class ViewSwitcher {
    
    private boolean firstSwitch = true;
    private static ViewSwitcher instance;

    public static ViewSwitcher getInstance() {
        if (instance == null) {
            instance = new ViewSwitcher();
        }

        return instance;
    }

    private View loadStyle(final Stage stage, final ViewType viewType) {
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(viewType.getPath()));
        Parent root = null;

        try {
            root = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        Scene newScene;
        if (this.firstSwitch) {
            newScene = new Scene(root);
            this.firstSwitch = false;
        } else {
            newScene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
        }

        stage.setScene(newScene);
        View view = loader.getController();
        stage.getScene().getStylesheets().clear();

        stage.setMinHeight(((AnchorPane) stage.getScene().getRoot()).getMinHeight());
        stage.setMinWidth(((AnchorPane) stage.getScene().getRoot()).getMinWidth());
        if (root != null) {
            root.scaleXProperty().bind(Bindings.min(stage.widthProperty().divide(stage.minWidthProperty()),
                stage.heightProperty().divide(stage.minHeightProperty())));

            root.scaleYProperty().bind(root.scaleXProperty());
        }

        view.setStage(stage);
        stage.setScene(newScene);
        return view;
    }

    public void switchView(final Stage stage, final ViewType type, final GameModel model) {
        View view = this.loadStyle(stage, type);
        Controller c = type.getController();
        c.setView(view);
        c.setModel(model);
        view.setController(c);
        view.setStage(stage);
        view.init();
        stage.getIcons().add(GameImages.GAME_ICON.getImage());
        stage.setTitle("Brick-Breaker");
        stage.show();
    }
}
