package brickbreaker.view;

import java.util.List;

import brickbreaker.common.GameImages;
import brickbreaker.controllers.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;

public class SetUpView extends ViewImpl {

    @FXML private ComboBox<String> cbUsersList;
    @FXML private ImageView addButton;

    private final JavaFXApp s;
    private List<String> users;
    
    public SetUpView(final JavaFXApp s, final Controller controllerToAttach) {
        super(controllerToAttach);
        this.s = s;
        this.users = s.controller.getUserController().getUsersName();
    }

    @Override
    public void init() {

        //Setting up the combo box.
        cbUsersList.getItems().addAll(this.users);
        cbUsersList.setPromptText("Type your nick");
        cbUsersList.setEditable(true);

        //Setting up the images.
        addButton.setImage(GameImages.GAME_ICON.getImage());
    }

    public void switchToHome() {
        String nick = cbUsersList.getEditor().getText();

        if (nick.isEmpty()) {
            Dialog<String> d = new Dialog<>();
            d.setTitle("Empty user name.");
            d.setContentText("You cannot play without an user name!");
            d.getDialogPane().getButtonTypes().add(new ButtonType("Retry", ButtonData.BACK_PREVIOUS));
            d.showAndWait();
        } else if (!this.users.contains(nick)) {
            s.controller.getUserController().addUser(nick);
        }

        s.switchToHome();
    }
}
