package msk.lab4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        stage.setTitle("Сложные табличные вычисления в JAVA");
        stage.setResizable(false);
        stage.getIcons().add(new Image("msk/lab4/logo.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
