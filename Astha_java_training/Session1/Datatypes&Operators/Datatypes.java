package Astha_java_training.Session1.DataTypes&Operators;

/**
 * Author: Astha Paroha
 * Program: Demonstrate primitive and reference data types
 */
public class DataTypesExample {

    public static void main(String[] args) {

      
        // Primitive Data Types Examples

        int integerValue = 10;
        double doubleValue = 25.5;
        char characterValue = 'A';
        boolean booleanValue = true;

        System.out.println("Primitive Data Types:");
        System.out.println("Integer: " + integerValue);
        System.out.println("Double: " + doubleValue);
        System.out.println("Character: " + characterValue);
        System.out.println("Boolean: " + booleanValue);

       
        // Reference Data Types Examples
      

        String name = "Astha";
        int[] numbers = {10, 20, 30};

        System.out.println("\nReference Data Types:");
        System.out.println("String: " + name);

        System.out.print("Array: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }

        
        // Key Difference Explanation
   

        System.out.println("\n\nDifference:");
        System.out.println("Primitive types store actual values.");
        System.out.println("Reference types store memory addresses of objects.");
    }
}
