/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msk.lab2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author MyssakSergey
 */
public class Controller {
    @FXML
    private Button decideButton;

    @FXML
    private Button cleanOutButton;

    @FXML
    private Button exitButton;

    @FXML
    private TextField xTextField;

    @FXML
    private TextField aTextField;

    @FXML
    private TextField bTextField;

    @FXML
    private TextField resultTextField;
    
    @FXML
    public void decideButtonAction() {
        try {
            Double x = Double.parseDouble(xTextField.getText());
            Double a = Double.parseDouble(aTextField.getText());
            Double b = Double.parseDouble(bTextField.getText());

            if (x <= 7) {
                if (a == 0 && b == 0) {
                    resultTextField.setText("A и В не могут одновременно равняться нулю!");
                } else {
                    resultTextField.setText(String.valueOf((x + 4)/(a * a + b * b)));
                }
            } else {
                resultTextField.setText(String.valueOf(x*(a+b)*(a+b)));
            }
        } catch (Exception e) {
            resultTextField.setText("Введите корректные данные!");
        }
    }

    @FXML
    public void cleanOutButtonAction() {
        xTextField.setText("");
        aTextField.setText("");
        bTextField.setText("");
        resultTextField.setText("");
    }

    @FXML
    public void exitButtonAction() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
