package msk.lab10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{
    private Stage stage;
    private AnchorPane anchorPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        anchorPane = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setScene(new Scene(this.anchorPane));
        stage.setTitle("Работа с классами в JAVA");
        stage.setResizable(false);
        stage.show();
    }

    public Stage getMain() {
        return this.stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
