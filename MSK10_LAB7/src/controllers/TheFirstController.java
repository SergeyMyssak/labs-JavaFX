package controllers;

import start.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TheFirstController {
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonExit;
    @FXML
    private AnchorPane rootPane;

    public void initialize() {}

    @FXML
    public void buttonStartAction() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxml/theSecondScene.fxml"));
        rootPane.getChildren().setAll(anchorPane);
        Main.getStage().setTitle("Детство");
    }

    @FXML
    public void buttonExitAction() {
        Main.getStage().close();
    }
}