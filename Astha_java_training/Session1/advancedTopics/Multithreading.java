package Astha_java_training.Session1.advancedTopics;

/**
 * Author: Astha Paroha
 * Program: Multithreading Example
 */

// Thread class
class MyThread extends Thread {

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread running: " + i);
        }
    }
}

public class MultithreadingExample {

    public static void main(String[] args) {

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start(); // start thread
        t2.start();
    }
}
