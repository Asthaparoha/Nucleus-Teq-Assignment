package Astha_java_training.Session1.stringManipulation;

import java.util.Scanner;

/**
 * Author: Astha Paroha
 * Program: Reverse a given string
 * Time Complexity: O(n)
 */
public class ReverseString {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        String reversed = "";

        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i);
        }

        System.out.println("Reversed String: " + reversed);

        scanner.close();
    }
}
