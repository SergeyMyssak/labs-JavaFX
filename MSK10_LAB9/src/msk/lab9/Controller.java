package msk.lab9;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private Main main = new Main();

    @FXML
    private TextArea textArea;
    @FXML
    private Button buttonLoadOfData;
    @FXML
    private Button buttonProcessData;
    @FXML
    private Button buttonSaveProcessingData;

    // Первая текстовая ошибка.
    @FXML
    private Text textTheFirstMistake = new Text();
    // Вторая  текстовая ошибка.
    @FXML
    private Text textTheSecondMistake = new Text();
    // Третья текстовая ошибка.
    @FXML
    private Text textTheThirdMistake = new Text();

    // Массив для необработанной таблицы.
    private int[][] numArrOfRawData = new int[5][6];
    // Массив для обработанной таблицы.
    private int[][] numArrOfProcessedData = new int[5][6];
    // Вспомогательная переменная для вывода необработанной и обработанной таблицы в textArea.
    private String text = "Исходные данные из файла:\n";
    // Переменная для вывода необработанной и обработанной таблицы в textArea.
    private StringBuilder result = new StringBuilder(text);
    // Вспомогательная переменная для вывода обработанной таблицы в txt-файл.
    private String processingArr = "";
    // Переменная для вывода обработанной таблицы в txt-файл.
    private StringBuilder resultProcessingArr = new StringBuilder(processingArr);

    // Вспомогательная переменная для определения состояния кнопки buttonProcessData.
    private Boolean conditionForButtonProcessData = false;
    // Вспомогательная переменная для определения состояния кнопки buttonSaveProcessingData.
    private Boolean conditionForButtonSaveProcessingData = false;



    @FXML
    private void initialize() {
        // Устанавливаем начальное состояние кнопки buttonProcessData - disable.
        buttonProcessData.setDisable(true);
        // Устанавливаем начальное состояние кнопки buttonSaveProcessingData - disable.
        buttonSaveProcessingData.setDisable(true);
    }

    public void buttonLoadOfDataAction() {
        // Очищаем переменную для вывода необработанной и обработанной таблицы в textArea.
        result.delete(text.length(), result.length());
        // Очищаем TextArea.
        textArea.setText("");
        // Устанавливаем цвет текстовым ошибкам.
        textTheFirstMistake.setFill(Color.BLACK);
        textTheSecondMistake.setFill(Color.BLACK);

        // Устанавливаем состояние кнопки buttonProcessData - disable.
        conditionForButtonProcessData = true;
        buttonProcessData.disableProperty().bind(Bindings.createBooleanBinding(
                () -> conditionForButtonProcessData));
        // Устанавливаем состояние кнопки buttonSaveProcessingData - disable.
        conditionForButtonSaveProcessingData = true;
        buttonSaveProcessingData.disableProperty().bind(Bindings.createBooleanBinding(
                () -> conditionForButtonSaveProcessingData));
        // При нажатии на кнопку buttonLoadOfData, будет открываться окно с выбором txt-файла.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");
        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(main.getStage());


        try {
            // Начинаем считывать данные в выбранном txt-файле.
            Scanner scanner = new Scanner(selectedFile);
            while (scanner.hasNextLine()) {
                for (int i = 0; i < numArrOfRawData.length; i++) {
                    // Разбиваем строку на массив, в который заносим данные из файла.
                    String[] q = scanner.nextLine().split(" ");
                    // Если длина массива больше или меньше 6, то генерируем исключение.
                    if (q.length < 6 || q.length > 6){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    // Заносим данные из строкового массива в необработанный массив.
                    numArrOfRawData[i][0] = Integer.parseInt(q[0]);
                    numArrOfRawData[i][1] = Integer.parseInt(q[1]);
                    numArrOfRawData[i][2] = Integer.parseInt(q[2]);
                    numArrOfRawData[i][3] = Integer.parseInt(q[3]);
                    numArrOfRawData[i][4] = Integer.parseInt(q[4]);
                    numArrOfRawData[i][5] = Integer.parseInt(q[5]);
                }
            }

            // Красиво обрабатываем массив необработанных данных.
            dataProcessing(numArrOfRawData);
            // Отображаем массив необработанных данных в textArea.
            textArea.setText(String.valueOf(result));

            // Устанавливаем состояние кнопки buttonProcessData - not disable.
            conditionForButtonProcessData = false;
            buttonProcessData.disableProperty().bind(Bindings.createBooleanBinding(() -> conditionForButtonProcessData));

        }  catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            // Устанавливем цвет текстовой ошибки на красный.
            textTheFirstMistake.setFill(Color.RED);

            // Устанавливаем состояние кнопки buttonProcessData - disable.
            conditionForButtonProcessData = true;
            buttonProcessData.disableProperty().bind(Bindings.createBooleanBinding(() -> conditionForButtonProcessData));
        } catch (Exception e) {
            // Устанавливем цвет текстовой ошибки на красный.
            textTheSecondMistake.setFill(Color.RED);

            // Устанавливаем состояние кнопки buttonProcessData - disable.
            conditionForButtonProcessData = true;
            buttonProcessData.disableProperty().bind(Bindings.createBooleanBinding(() -> conditionForButtonProcessData));
        }
    }

    // Метод в котором обрабатываются массивы и затем красиво отображаются.
    public void dataProcessing(int[][] arr) {
        // Список максимальных длин чисел в таблице
        ArrayList<Integer> listOfTheMaxLengthsOfNumbers = new ArrayList<>();

        // Добавляем в список listOfTheMaxLengthsOfNumbers все длины чисел с первой строки.
        for (int i = 0; i < arr[0].length; i++) {
            listOfTheMaxLengthsOfNumbers.add(String.valueOf(arr[0][i]).length());
        }

        // Если длина числа меньше, чем последующее число в этом столбце, то устанавливаем ему новое значение.
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (listOfTheMaxLengthsOfNumbers.get(j) <= String.valueOf(arr[i][j]).length()) {
                    listOfTheMaxLengthsOfNumbers.set(j, String.valueOf(arr[i][j]).length());
                }
            }
        }

        // Делаем отоброжение массива красивым.
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                String[] size = String.valueOf(arr[i][j]).split("");
                for (int k = 0; k < listOfTheMaxLengthsOfNumbers.get(j) - size.length; k++) {
                    result.append("  ");
                }
                if (String.valueOf(arr[i][j]).contains("-")) {
                    result.append(" ");
                }
                result.append(arr[i][j] + "    ");
            }
            result.append("\n");
        }
    }

    public void buttonProcessDataAction() {
        // Список в котором хрянятся номера ячеек в которых есть единица.
        ArrayList<String> listOfNumUnits = new ArrayList<>();
        // Переменная которая хранит кол-во единиц во втором столбце.
        int countOne = 0;
        // Считаем кол-во единиц во втором столбце.
        for (int i = 0; i < numArrOfRawData.length; i++) {
            if (numArrOfRawData[i][1] == 1) {
                countOne++;
            }
        }

        // Если во втором столбце 2 единицы, то выполняем условие данное по заданию.
        if (countOne == 2) {
            // Максимальное число в первой строке.
            int max = numArrOfRawData[0][0];
            // Номер ячейки максимального числа в первой строке.
            String key = "";
            // Ищем максимальное число и его номер ячейки.
            for (int i = 0; i < numArrOfRawData[0].length; i++) {
                if (max <= numArrOfRawData[0][i]) {
                    max = numArrOfRawData[0][i];
                    key = String.valueOf(0) + String.valueOf(i);
                }
            }

            // Ищем номера ячеек в которых есть единица.
            for (int i = 0; i < numArrOfRawData.length; i++) {
                for (int j = 0; j < numArrOfRawData[i].length; j++) {
                    if (numArrOfRawData[i][j] == 1) {
                        listOfNumUnits.add(String.valueOf(i) + String.valueOf(j));
                    }
                }
            }
            // Временный строковый массив в котором хранятся номер ячейки в которой есть единица.
            String[] temp = new String[0];
            // Временный строковый массив в котором хранятся номер ячейки максимального числа.
            String[] tempMax = key.split("");
            for (int i = 0; i < numArrOfProcessedData.length; i++) {
                for (int j = 0; j < numArrOfProcessedData[i].length; j++) {
                    if (listOfNumUnits.size() != 0) {
                        temp = listOfNumUnits.get(0).split("");
                    }
                    numArrOfProcessedData[i][j] = numArrOfRawData[i][j];
                    // Заменяем единицы нулями.
                    if (String.valueOf(i).equals(temp[0]) && String.valueOf(j).equals(temp[1])) {
                        numArrOfProcessedData[i][j] = 0;
                        listOfNumUnits.remove(0);
                    }
                    // Уменьшаем максимальное число в первой строке в два раза.
                    if (String.valueOf(i).equals(tempMax[0]) && String.valueOf(j).equals(tempMax[1])) {
                        numArrOfProcessedData[i][j] = numArrOfRawData[i][j]/2;
                    }
                }
            }
            result.append("\nОбработанные данные:\n");

            // Устанавливаем состояние кнопки buttonProcessData - disable.
            conditionForButtonProcessData = true;
            buttonProcessData.disableProperty().bind(Bindings.createBooleanBinding(
                    () -> conditionForButtonProcessData));

            dataProcessing(numArrOfProcessedData);

            // Устанавливаем состояние кнопки buttonSaveProcessingData - not disable.
            conditionForButtonSaveProcessingData = false;
            buttonSaveProcessingData.disableProperty().bind(Bindings.createBooleanBinding(
                    () -> conditionForButtonSaveProcessingData));

            // Отображаем массив необработанных и обработанных данных в textArea.
            textArea.setText(String.valueOf(result));
            // Очищаем переменную для вывода необработанной и обработанной таблицы в textArea.
            result.delete(text.length(), result.length());
        }
    }

    public void buttonSaveProcessingDataAction() {
        // Устанавливем цвет текстовой ошибки на черный.
        textTheThirdMistake.setFill(Color.BLACK);

        /* При нажатии на кнопку buttonSaveProcessingDataAction, будет
            открываться окно с выбором txt-файла, в который мы хотим
            записать обработанные данные. */
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");
        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(main.getStage());

        // Записываем в переменную resultProcessingArr обработанные значения таблицы.
        for (int i = 0; i < numArrOfProcessedData.length; i++) {
            for (int j = 0; j < numArrOfProcessedData[i].length; j++) {
                resultProcessingArr.append(numArrOfProcessedData[i][j] + " ");
            }
            resultProcessingArr.append("\r\n");
        }

        try {
            // Записываем в файл обработанную таблицу.
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(selectedFile));
            bufferedWriter.write(String.valueOf(resultProcessingArr));
            bufferedWriter.flush();
            bufferedWriter.close();

            // Устанавливаем состояние кнопки buttonSaveProcessingData - disable.
            conditionForButtonSaveProcessingData = true;
            buttonSaveProcessingData.disableProperty().bind(Bindings.createBooleanBinding(
                    () -> conditionForButtonSaveProcessingData));
            // Очищаем переменную для вывода обработанной таблицы в textArea.
            resultProcessingArr.delete(0, resultProcessingArr.length());
        } catch (IOException e) {
            textTheThirdMistake.setFill(Color.RED);
        } catch (Exception e) {}
    }
}
