package Astha_java_training.Session1.Arrays;

import java.util.Scanner;

/**
 * Author: Astha Paroha
 * Program: Linear Search in an array
 * Description: Searches for a target element in the array
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class LinearSearch {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Taking array size input
        System.out.print("Enter number of elements: ");
        int size = scanner.nextInt();

        // Edge case validation
        if (size <= 0) {
            System.out.println("Error: Array size must be greater than zero.");
            scanner.close();
            return;
        }

        int[] numbers = new int[size];

        // Taking array elements input
        System.out.println("Enter " + size + " elements:");
        for (int index = 0; index < size; index++) {
            numbers[index] = scanner.nextInt();
        }

        // Taking target element input
        System.out.print("Enter element to search: ");
        int target = scanner.nextInt();

        int foundIndex = -1;

        // Linear search logic
        for (int index = 0; index < size; index++) {
            if (numbers[index] == target) {
                foundIndex = index;
                break;
            }
        }

        // Output result
        if (foundIndex != -1) {
            System.out.println("Element found at index: " + foundIndex);
        } else {
            System.out.println("Element not found in array.");
        }

        scanner.close();
    }
}
