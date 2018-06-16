package controllers;

import start.Main;
import start.Repository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TheFourthController {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button buttonNext;
    @FXML
    private Button buttonPrev;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Label label;
    private Repository repository = new Repository();
    private String stringSelected = "Неполное среднее";

    private ObservableList<String> options = FXCollections.observableArrayList(
                    "Неполное среднее",
                    "Среднее",
                    "Среднее-специальное",
                    "Высшее"
            );

    public void initialize() {
        comboBox.setItems(options);
        comboBox.getSelectionModel().selectFirst();
        comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (comboBox.getSelectionModel().isSelected(0)) {
                    label.setText("Выбрано: Неполное среднее");
                    stringSelected = "Отлично";
                } else if (comboBox.getSelectionModel().isSelected(1)) {
                    label.setText("Выбрано: Среднее");
                    stringSelected = "Среднее";
                } else if (comboBox.getSelectionModel().isSelected(2)) {
                    label.setText("Выбрано: Среднее-специальное");
                    stringSelected = "Среднее-специальное";
                } else {
                    label.setText("Выбрано: Высшее");
                    stringSelected = "Высшее";
                }
            }
        });
    }

    @FXML
    public void buttonNextAction() throws IOException {
        repository.setHashMap(2, stringSelected);
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxml/theFifthScene.fxml"));
        rootPane.getChildren().setAll(anchorPane);
        Main.getStage().setTitle("Результаты");
    }

    @FXML
    public void buttonPrevAction() throws IOException {
        repository.removeHashMap(2);
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxml/theThirdScene.fxml"));
        rootPane.getChildren().setAll(anchorPane);
        Main.getStage().setTitle("Отрочество");
    }
}
