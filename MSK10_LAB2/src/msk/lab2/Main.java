package msk.lab2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage stage;
    private AnchorPane anchorPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        anchorPane = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Создание простейшей визуальной программы на Java");
        stage.setResizable(false);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
