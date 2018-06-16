package controllers;

import start.Main;
import start.Repository;
import start.RobotResult;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TheFifthController {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button buttonExit;
    @FXML
    private Button buttonPrev;
    @FXML
    private TableView<RobotResult> tableView;
    @FXML
    private TableColumn<RobotResult, String> colQuestions;
    @FXML
    private TableColumn<RobotResult, String> colAnswer;

    private Repository repository = new Repository();
    private HashMap<Integer, String> hashMap = repository.getHashMap();
    ObservableList<RobotResult> items = FXCollections.observableArrayList();

    public void initialize() {
        tableView.itemsProperty().setValue(items);
        for (Map.Entry entry : hashMap.entrySet()) {
            if (Integer.parseInt(entry.getKey().toString()) == 0) {
                items.add(new RobotResult("Ваша любимая каша:", entry.getValue().toString()));
            } else if (Integer.parseInt(entry.getKey().toString()) == 1) {
                items.add(new RobotResult("Вы учились в пятом классе:", entry.getValue().toString()));
            } else {
                items.add(new RobotResult("Ваше образование:", entry.getValue().toString()));
            }
        }


        colQuestions.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getQuestion()));
        colAnswer.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getAnswer()));
        tableView.refresh();
    }

    @FXML
    public void buttonPrevAction() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxml/theFourthScene.fxml"));
        rootPane.getChildren().setAll(anchorPane);
        Main.getStage().setTitle("Юность");
    }
    @FXML
    public void buttonExitAction() {
        Main.getStage().close();
    }
}
