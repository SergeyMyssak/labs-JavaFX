package msk.lab3;

import javafx.beans.property.SimpleStringProperty;

public class Robot {
    private SimpleStringProperty num1;
    private SimpleStringProperty num2;
    private SimpleStringProperty num3;
    private SimpleStringProperty num4;
    private SimpleStringProperty num5;

    public Robot (String num1, String num2, String num3, String num4, String num5) {
        this.num1 = new SimpleStringProperty(num1);
        this.num2 = new SimpleStringProperty(num2);
        this.num3 = new SimpleStringProperty(num3);
        this.num4 = new SimpleStringProperty(num4);
        this.num5 = new SimpleStringProperty(num5);
    }

    public Robot () {
        this.num1 = new SimpleStringProperty("-1");
        this.num2 = new SimpleStringProperty("-5.0");
        this.num3 = new SimpleStringProperty("-8.0");
        this.num4 = new SimpleStringProperty("-10.0");
        this.num5 = new SimpleStringProperty("-9.0");
    }

    public String getNum1() {
        return num1.get();
    }

    public void setNum1(String num1) {
        this.num1.set(num1);
    }

    public String getNum2() {
        return num2.get();
    }

    public void setNum2(String num2) {
        this.num2.set(num2);
    }

    public String getNum3() {
        return num3.get();
    }

    public void setNum3(String num3) {
        this.num3.set(num3);
    }

    public String getNum4() {
        return num4.get();
    }

    public void setNum4(String num4) {
        this.num4.set(num4);
    }

    public String getNum5() {
        return num5.get();
    }

    public void setNum5(String num5) {
        this.num5.set(num5);
    }
}
