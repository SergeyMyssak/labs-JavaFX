package msk.lab6;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage stage;
    private Scene scene;
    private int width = 325;
    private int height = 150;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        // Что-то на подобие панели, куда мы цепляем наш рисунок
        Group group = new Group();
        this.scene = new Scene(group, width, height);
        this.stage.setScene(this.scene);
        this.scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
        this.stage.setResizable(false);
        this.stage.setTitle("Работа с графикой в Java");

        // Основа тыквы
        Rectangle rectangle = new Rectangle(115, 75);
        
        // Устанавливаем основе тыквы различные значения
        rectangle.setFill(Color.rgb(255, 153, 0, 1));
        rectangle.setX(width/3);
        rectangle.setY(height/4);
        rectangle.setArcHeight(80);
        rectangle.setArcWidth(80);
        rectangle.getStyleClass().add("border-rectangle");

        group.getChildren().add(rectangle);

        // Хвостик тыквы
        Polygon tail = new Polygon();
        tail.getPoints().addAll(new Double[]{
                173.0, 37.0,
                183.0, 17.0,
                173.0, 17.0,
                153.0, 37.0 });
        // Градиент для хвостика тыквы
        Stop[] stops = new Stop[] { new Stop(0, Color.rgb(157,255,0)), new Stop(1, Color.rgb(0,50,0))};
        LinearGradient lg2 = new LinearGradient(175, 10, 193, 40, false, CycleMethod.NO_CYCLE, stops);
        tail.setFill(lg2);

        group.getChildren().add(tail);

        // Рисуем первый глаз
        Polygon theFirstEye = new Polygon();
        theFirstEye.getPoints().addAll(new Double[]{
                144.0, 62.0,
                158.0, 62.0,
                158.0, 65.0,
                131.0, 65.0,
                148.0, 45.0,
                150.0, 45.0,
                144.0, 55.0});

        group.getChildren().add(theFirstEye);

        // Первая засечка для первого глаза
        Polygon theFirstSerifOfTheFirstEye = new Polygon();
        theFirstSerifOfTheFirstEye.getPoints().addAll(new Double[]{
                155.0, 57.0,
                158.0, 57.0,
                158.0, 64.0,
                155.0, 64.0 });

        group.getChildren().add(theFirstSerifOfTheFirstEye);

        // Вторая засечка для первого глаза
        Polygon theSecondSerifTheFirstOfEye = new Polygon();
        theSecondSerifTheFirstOfEye.getPoints().addAll(new Double[]{
                148.0, 45.0,
                150.0, 45.0,
                150.0, 50.0,
                148.0, 50.0 });

        group.getChildren().add(theSecondSerifTheFirstOfEye);



        // Рисуем второй глаз
        Polygon theSecondEye = new Polygon();
        theSecondEye.getPoints().addAll(new Double[]{
                182.0, 62.0,
                196.0, 62.0,
                196.0, 65.0,
                169.0, 65.0,
                186.0, 45.0,
                188.0, 45.0,
                182.0, 55.0});

        group.getChildren().add(theSecondEye);

        // Первая засечка для второго глаза
        Polygon theFirstSerifOfTheSecondEye = new Polygon();
        theFirstSerifOfTheSecondEye.getPoints().addAll(new Double[]{
                193.0, 57.0,
                196.0, 57.0,
                196.0, 64.0,
                193.0, 64.0 });

        group.getChildren().add(theFirstSerifOfTheSecondEye);

        // Вторая засечка для второго глаза
        Polygon theSecondSerifTheSecondOfEye = new Polygon();
        theSecondSerifTheSecondOfEye.getPoints().addAll(new Double[]{
                186.0, 45.0,
                188.0, 45.0,
                188.0, 50.0,
                186.0, 50.0 });

        group.getChildren().add(theSecondSerifTheSecondOfEye);

        // Рисуем нос
        Polygon nose = new Polygon();
        nose.getPoints().addAll(new Double[]{
                159.0, 79.0,
                164.0, 75.0,
                169.0, 79.0,
                169.0, 73.0,
                164.0, 70.0,
                159.0, 73.0});

        group.getChildren().add(nose);

        // Рисуем рот
        Polygon mouth = new Polygon();
        mouth.getPoints().addAll(new Double[]{
                159.0, 103.0,
                170.0, 103.0,
                210.0, 68.0,
                174.0, 86.0,
                154.0, 86.0,
                120.0, 68.0,
                160.0, 103.0});

        group.getChildren().add(mouth);

        // Первая засечка для рта
        Polygon theFirstSerifOfMouth = new Polygon();
        theFirstSerifOfMouth.getPoints().addAll(new Double[]{
                115.0, 69.0,
                124.0, 69.0,
                124.0, 71.0,
                115.0, 71.0 });

        group.getChildren().add(theFirstSerifOfMouth);

        // Вторая засечка для рта
        Polygon theSecondSerifOfMouth = new Polygon();
        theSecondSerifOfMouth.getPoints().addAll(new Double[]{
                206.0, 69.0,
                215.0, 69.0,
                215.0, 71.0,
                206.0, 71.0 });

        group.getChildren().add(theSecondSerifOfMouth);

        // Рисуем текст
        Text text = new Text();
        text.setText("MSK-10");
        text.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        text.setX(100.0f);
        text.setY(80.0f);
        text.setFill(Color.rgb(0, 0, 0, 0.3));
        text.setRotate(-35.0);

        group.getChildren().add(text);

        this.stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
