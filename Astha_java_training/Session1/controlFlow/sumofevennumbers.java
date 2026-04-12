package Astha_java_training.Session1.controlFlow;

/**
 * Author: Astha Paroha
 * Program: Calculate sum of even numbers from 1 to 10
 */
public class SumEvenNumbers {

    public static void main(String[] args) {

        int number = 1;
        int sum = 0;

        while (number <= 10) {
            if (number % 2 == 0) {
                sum += number;
            }
            number++;
        }

        System.out.println("Sum of even numbers from 1 to 10: " + sum);
    }
}
