/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msk.lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author MyssakSergey
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tempCount = 0;
        
        double X = 0;
        double A = 1;
        double B = 0;
        
        while (tempCount < 3) {
            try {
                if (tempCount == 0) {
                    System.out.print("Введите Х <число>: ");
                    X = Double.parseDouble(reader.readLine());
                } else if (tempCount == 1) {
                    System.out.print("Введите A <число, не равное нулю>: ");
                    A = Double.parseDouble(reader.readLine());
                    if (A == 0) {
                        System.out.println("Введите число, не равное нулю!");
                        tempCount--;
                    }
                } else {
                    System.out.print("Введите B <число>: ");
                    B = Double.parseDouble(reader.readLine());
                }
            } catch (Exception e) {
                System.out.println("Можно ввести только число!");
                tempCount--;
            }
            tempCount++;
        }
        System.out.println(decisionExample(X, A, B));
    }
    
    public static double decisionExample(double X, double A, double B ) {
        double result = 0;
        if (X > 4) {
            result = (5*X*X + B*B)/(A*A + B*B);
        } else {
            result = 6*(X*X - A*A);
        }
        return result;
    }
}
