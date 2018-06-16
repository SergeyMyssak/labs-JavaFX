package controllers;

import start.Main;
import start.Repository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TheSecondController {
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;
    @FXML
    private RadioButton rb4;
    @FXML
    private Button buttonNext;
    @FXML
    private Button buttonPrev;
    @FXML
    private Label label;

    final ToggleGroup toggleGroup = new ToggleGroup();

    @FXML
    private AnchorPane rootPane;
    private Repository repository = new Repository();
    private String stringSelected = "Манная";

    public void initialize() {
        rb1.setToggleGroup(toggleGroup);
        rb1.setSelected(true);
        rb2.setToggleGroup(toggleGroup);
        rb3.setToggleGroup(toggleGroup);
        rb4.setToggleGroup(toggleGroup);

        rb1.setUserData("1");
        rb2.setUserData("2");
        rb3.setUserData("3");
        rb4.setUserData("4");

        toggleGroup.selectedToggleProperty().addListener(event -> {
            if (toggleGroup.getSelectedToggle().getUserData().toString().equals("1")) {
                label.setText("Выбрано: Манная каша");
                stringSelected = "Манная";
            } else if (toggleGroup.getSelectedToggle().getUserData().toString().equals("2")) {
                label.setText("Выбрано: Рисовая каша");
                stringSelected = "Рисовая";
            } else if (toggleGroup.getSelectedToggle().getUserData().toString().equals("3")) {
                label.setText("Выбрано: Гороховая каша");
                stringSelected = "Гороховая";
            } else {
                label.setText("Выбрано: Гречневая каша");
                stringSelected = "Гречневая";
            }
        });
    }

    @FXML
    public void buttonNextAction() throws IOException {
        repository.setHashMap(0, stringSelected);
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxml/theThirdScene.fxml"));
        rootPane.getChildren().setAll(anchorPane);
        Main.getStage().setTitle("Отрочество");
    }

    @FXML
    public void buttonPrevAction() throws IOException {
        repository.removeHashMap(0);
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxml/theFirstScene.fxml"));
        rootPane.getChildren().setAll(anchorPane);
        Main.getStage().setTitle("Приветствие");
    }
}
