package Astha_java_training.Session1.advancedTopics;

import java.util.Scanner;

/**
 * Author: Astha Paroha
 * Program: Exception Handling Example
 */
public class ExceptionHandling {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter two numbers: ");
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            int result = a / b; // may throw exception

            System.out.println("Result: " + result);

        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero");
        } catch (Exception e) {
            System.out.println("Invalid input");
        } finally {
            System.out.println("Execution completed");
            scanner.close();
        }
    }
}
