package Astha_java_training.Session1.basic;

import java.util.Scanner;

/**
 * Author: Astha Paroha
 * Program: Print Fibonacci sequence
 * Time Complexity: O(n)
 */
public class Fibonacci {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of terms: ");
        int terms = scanner.nextInt();

        if (terms <= 0) {
            System.out.println("Enter positive number");
            scanner.close();
            return;
        }

        int first = 0, second = 1;

        System.out.print("Fibonacci Series: ");

        for (int i = 1; i <= terms; i++) {
            System.out.print(first + " ");

            int next = first + second;
            first = second;
            second = next;
        }

        scanner.close();
    }
}
