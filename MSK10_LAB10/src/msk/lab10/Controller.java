package msk.lab10;

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

public class Controller extends Main {
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
        // Изменяем цвет текста ошибки на черный, если он красного цвета
        theFirstExceptionLabel.setTextFill(Color.web("#000000"));

        // В этот список заносим все значения элементов с таблицы
        ArrayList<Double> listNum = new ArrayList<>();
        double result = 0.0;
        double sum = 0;
        double multiplication = 1.0;
        double a = 0.0;
        double b = 0.0;
        double three = 3;
        double resultOperations = 0;
        double theFirstOperation = 0;
        double theSecondOperation = 0;
        double theThirdOperation = 0;

        try {

            // Добавляем в список данные из таблицы
            for (int i = 0; i < items.size(); i++) {
                listNum.add(Double.parseDouble(items.get(i).getNum1()));
            }

            // Получаем значения переменных a и b.
            a = Double.parseDouble(aTextField.getText());
            b = Double.parseDouble(bTextField.getText());

            // В этом цикле производится решение примера
            for (int j = 1; j < listNum.size()+1; j++) {
                sum += j;
                multiplication *= listNum.get(j - 1);

                // Производим вычисления
                theFirstOperation = Math.pow(Math.pow(a, 2) + Math.pow(b, 2), 3) * Math.pow(Math.cos(listNum.get(j - 1)), 2);
                theSecondOperation = sum - multiplication;
                if (theSecondOperation == 0) {
                    items.get(j-1).setNum2("-");
                } else {
                    theThirdOperation = theFirstOperation / theSecondOperation;
                    if (theThirdOperation < 0) {
                        resultOperations = Math.pow(Math.abs(theThirdOperation), 1 / three);
                        result = resultOperations * (-1);
                    } else {
                        resultOperations = Math.pow(theThirdOperation, 1 / three);
                        result = resultOperations;
                    }
                    // Записываем во второй столбик получившийся результат
                    items.get(j-1).setNum2(String.valueOf(result));
                }
            }
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
        theFirstExceptionLabel.setTextFill(Color.web("#000000"));

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
        double multiplication = 1.0;

        // Присваиваем переменным a и b случайные значения
        Random random1 = new Random();
        Random random2 = new Random();
        int a = random1.nextInt(20)-10;
        int b = random2.nextInt(20)-10;

        // Заносим значения a и b в соответсвующие текстовые поля.
        aTextField.setText(String.valueOf(a));
        bTextField.setText(String.valueOf(b));
        for (int i = 1; i < COUNT_ROW+1; i++) {
            // Присваеваем элементу К случайное число
            double randomNum = Math.random()*100;
            sum += i;
            multiplication *= randomNum;
            // Заполняем данные и ответ вычисления в таблицу
            items.add(new NumericGenerator(String.valueOf(randomNum), solveTheTaskRandom(randomNum,sum, multiplication, a, b)));
        }
        if (items.get(0).getNum1().equals(1)) {
            items.get(0).setNum2("-");
        }
    }

    /* Вспомогательный метод для заполения таблицы случаными
    числами (buttonRandomAction), в котором выполняются вычисления. */
    public String solveTheTaskRandom (double num, double sum, double multiplication, int a, int b) {
        String result = "";
        double aNum = a;
        double bNum = b;
        double three = 3;
        double resultOperations = 0;
        double theFirstOperation = Math.pow(Math.pow(a, 2) + Math.pow(b, 2), 3) * Math.pow(Math.cos(num), 2);
        double theSecondOperation = sum - multiplication;
        double theThirdOperation = theFirstOperation/theSecondOperation;
        if (theThirdOperation < 0) {
            resultOperations = Math.pow(Math.abs(theThirdOperation), 1/three);
            result = String.valueOf(resultOperations*(-1));
        } else {
            resultOperations = Math.pow(theThirdOperation, 1/three);
            result = String.valueOf(resultOperations);
        }
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

        tableView.refresh();
    }

    // При нажатии на кнопку buttonExit, этот метод реализует выход из программы.
    @FXML
    public void buttonExitAction() {
        Stage stage = getMain();
        stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }
}
