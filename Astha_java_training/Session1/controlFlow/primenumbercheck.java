package Astha_java_training.Session1.controlFlow;

import java.util.Scanner;

/**
 * Author: Astha Paroha
 * Program: Check if a number is prime
 * Time Complexity: O(n)
 */
public class PrimeCheck {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        if (number <= 1) {
            System.out.println("Not a Prime Number");
            scanner.close();
            return;
        }

        boolean isPrime = true;

        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }

        if (isPrime) {
            System.out.println("Prime Number");
        } else {
            System.out.println("Not a Prime Number");
        }

        scanner.close();
    }
}
