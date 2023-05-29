package brickbreaker.view;

import brickbreaker.controllers.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SetUpView extends ViewImpl {

    @FXML private TextField tfNickname;

    public SetUpView(Controller controllerToAttach) {
        super(controllerToAttach);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void init() {

    }
}
