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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TheThirdController {
    @FXML
    private Button buttonNext;
    @FXML
    private Button buttonPrev;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ListView<String> listView;
    @FXML
    private Label label;
    private Repository repository = new Repository();
    private String stringSelected = "Отлично";

    public void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList (
                "Отлично", "Хорошо", "Удовлетворительно", "Неудовлетворительно");
        listView.setItems(items);
        listView.getSelectionModel().selectFirst();
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (listView.getSelectionModel().getSelectedIndex() == 0) {
                    label.setText("Выбрано: Отлично");
                    stringSelected = "Отлично";
                } else if (listView.getSelectionModel().getSelectedIndex() == 1) {
                    label.setText("Выбрано: Хорошо");
                    stringSelected = "Хорошо";
                } else if (listView.getSelectionModel().getSelectedIndex() == 2) {
                    label.setText("Выбрано: Удовлетворительно");
                    stringSelected = "Удовлетворительно";
                } else {
                    label.setText("Выбрано: Неудовлетворительно");
                    stringSelected = "Неудовлетворительно";
                }
            }
        });
    }

    @FXML
    public void buttonNextAction() throws IOException {
        repository.setHashMap(1, stringSelected);
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxml/theFourthScene.fxml"));
        rootPane.getChildren().setAll(anchorPane);
        Main.getStage().setTitle("Юность");
    }

    @FXML
    public void buttonPrevAction() throws IOException {
        repository.removeHashMap(1);
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxml/theSecondScene.fxml"));
        rootPane.getChildren().setAll(anchorPane);
        Main.getStage().setTitle("Детство");
    }
}
