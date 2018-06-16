package msk.lab4;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;

public class Controller {
    @FXML
    TableView<NumericGenerator> tableView;
    @FXML
    private TableColumn<NumericGenerator, String> col1;
    @FXML
    private TableColumn<NumericGenerator, String> col2;
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonRandom;
    @FXML
    private Button buttonCleanUp;
    @FXML
    private Button buttonExit;
    @FXML
    private TextField aTextField;
    @FXML
    private TextField bTextField;
    @FXML
    private Label theFirstExceptionLabel;
    @FXML
    private Label theFirstArithmeticExceptionLabel;
    @FXML
    private Label theThirdArithmeticExceptionLabel;
    @FXML
    private Label theFourthArithmeticExceptionLabel;

    private final int COUNT_ROW = 10;

    ObservableList<NumericGenerator> items = FXCollections.observableArrayList();

    // Это метод, при запуске программы, начинает делать свою работу самым первым.
    @FXML
    public void initialize() {
        tableView.itemsProperty().setValue(items);
        tableView.setEditable(true);

        /* Вызываем метод для заполения таблицы случайными числами и результатами. */
        addRandomNums();

        col1.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNum1()));
        col1.setCellFactory(TextFieldTableCell.forTableColumn());
        col1.setOnEditCommit(event -> ((NumericGenerator) event.getTableView().getItems().get(event.getTablePosition().getRow())).setNum1(event.getNewValue()));

        col2.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNum2()));
    }

    /* При нажатии на кнопку buttonStart, этот метод реализует
    считываение данных с таблицы, а также вычисление ответа. */
    @FXML
    public void buttonStartAction () {

        // В этот список заносим все значения элементов с таблицы
        ArrayList<Double> listNum = new ArrayList<>();
        double result = 0.0;
        double sum = 0.0;
        double a = 0.0;
        double b = 0.0;

        // Изменяем цвет текста ошибки на черный, если он красного цвета
        theFirstExceptionLabel.setTextFill(Color.web("#000000"));

        theFirstArithmeticExceptionLabel.setTextFill(Color.web("#000000"));
        theThirdArithmeticExceptionLabel.setTextFill(Color.web("#000000"));
        theFourthArithmeticExceptionLabel.setTextFill(Color.web("#000000"));

        try {

            // Добавляем в список данные из таблицы
            for (int i = 0; i < items.size(); i++) {
                listNum.add(Double.parseDouble(items.get(i).getNum1()));
            }

            // Получаем значения переменных a и b.
            a = Double.parseDouble(aTextField.getText());
            b = Double.parseDouble(bTextField.getText());

            // Если переменная a и b равны нулю, то генерируем исключение типа ArithmeticException
            if (a == 0 && b == 0) {
                ArithmeticException e = new ArithmeticException();
                throw e;
            }

            // В этом цикле производится решение примера
            for (int j = 0; j < listNum.size(); j++) {
                sum += listNum.get(j);
                if (j == 0) {
                    // Исходя из условия примера, у первого элемента таблицы не будет решения
                    items.get(0).setNum2("-");
                } else {
                    // Производим вычисления
                    result = Math.sqrt(Math.pow(Math.cos(listNum.get(j)), 2)/((Math.pow(a, 2) + Math.pow(b, 2)) - Math.sin(listNum.get(j))))*(sum-listNum.get(j));

                    // Записываем во второй столбик получившийся результат
                    items.get(j).setNum2(String.valueOf(result));
                }
            }
        } catch (ArithmeticException e) {
            theFirstArithmeticExceptionLabel.setTextFill(Color.web("#FF0000"));
            theThirdArithmeticExceptionLabel.setTextFill(Color.web("#FF0000"));
            theFourthArithmeticExceptionLabel.setTextFill(Color.web("#FF0000"));
        } catch (Exception e) {
            theFirstExceptionLabel.setTextFill(Color.web("#FF0000"));
        }

        // Обновляем таблицу
        tableView.refresh();
    }

    /* При нажатии на кнопку buttonRandom, этот метод реализует заполнение
        таблицы случайными числами, а также вычисляет ответ. */
    @FXML
    public void buttonRandomAction () {
        cleanUpTable();
        addRandomNums ();
        tableView.refresh();
    }

    /* Вспомогательный метод для заполения таблицы случаными числами (buttonRandomAction).
        В нем все данные в таблицы удаляются. */
    public void cleanUpTable () {
        if (items.size() != 0) {
            for (int i = 0; i < COUNT_ROW; i++) {
                items.remove(0);
            }
        }
    }

    /* Вспомогательный метод для заполения таблицы случаными числами (buttonRandomAction).
        Он заполняет таблицу данными и ответами. */
    public void addRandomNums () {
        double sum = 0.0;

        // Присваиваем переменным a и b случайные значения
        Random random1 = new Random();
        Random random2 = new Random();
        int a = random1.nextInt(20)-10;
        int b = random2.nextInt(20)-10;

        // Заносим значения a и b в соответсвующие текстовые поля.
        aTextField.setText(String.valueOf(a));
        bTextField.setText(String.valueOf(b));
        for (int i = 0; i < COUNT_ROW; i++) {
            // Присваеваем элементу К случайное число
            double randomNum = Math.random()*100;
            sum += randomNum;

            // Заполняем данные и ответ вычисления в таблицу
            items.add(new NumericGenerator(String.valueOf(randomNum), solveTheTaskRandom(randomNum,sum, a, b)));
        }

        // Исходя из условия примера, у первого элемента таблицы не будет решения
        items.get(0).setNum2("-");
    }

    /* Вспомогательный метод для заполения таблицы случаными
    числами (buttonRandomAction), в котором выполняются вычисления. */
    public String solveTheTaskRandom (double num, double sum, int a, int b) {
        String result = "";
        double aNum = a;
        double bNum = b;
        result = String.valueOf(Math.sqrt(Math.pow(Math.cos(num), 2)/((Math.pow(aNum, 2) + Math.pow(bNum, 2)) - Math.sin(num)))*(sum-num));
        return result;
    }

    /* При нажатии на кнопку buttonCleanUp, этот метод очищает
        все поля в форме и свойства некоторых элементов. */
    @FXML
    public void buttonCleanUpAction() {
        for (int i = 0; i < COUNT_ROW; i++) {
            items.get(i).setNum1("0");
            items.get(i).setNum2("");
        }
        aTextField.setText("0");
        bTextField.setText("0");
        theFirstExceptionLabel.setTextFill(Color.web("#000000"));

        theFirstArithmeticExceptionLabel.setTextFill(Color.web("#000000"));
        theThirdArithmeticExceptionLabel.setTextFill(Color.web("#000000"));
        theFourthArithmeticExceptionLabel.setTextFill(Color.web("#000000"));
        tableView.refresh();
    }

    // При нажатии на кнопку buttonExit, этот метод реализует выход из программы.
    @FXML
    public void buttonExitAction() {
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }
}
