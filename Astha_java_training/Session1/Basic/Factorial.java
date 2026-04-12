package Astha_java_training.Session1.basic;

import java.util.Scanner;

/**
 * Author: Astha Paroha
 * Program: Calculate factorial of a number
 * Time Complexity: O(n)
 */
public class Factorial {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        if (number < 0) {
            System.out.println("Factorial not defined for negative numbers");
            scanner.close();
            return;
        }

        int factorial = 1;

        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }

        System.out.println("Factorial: " + factorial);

        scanner.close();
    }
}
