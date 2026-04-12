package Astha_java_training.Session1.OOP;

/**
 * Author: Astha Paroha
 * Program: Demonstrate OOP concepts
 */
public class Main {

    public static void main(String[] args) {

        // Creating Student object
        Student student = new Student("Astha", 101, 85);
        student.displayDetails();
        student.calculateGrade();

        System.out.println("---------------------");

        // Creating GraduateStudent object
        GraduateStudent gradStudent = new GraduateStudent("Riya", 102, 78, "Computer Science");

        gradStudent.displayDetails();          // Inherited method
        gradStudent.displaySpecialization();   // Own method
        gradStudent.calculateGrade();          // Overridden method
    }
}
