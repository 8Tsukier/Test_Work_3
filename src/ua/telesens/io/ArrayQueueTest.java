package ua.telesens.io;

import java.util.NoSuchElementException;
import java.util.Queue;

public class ArrayQueueTest {
    private static void testQueueOfIntegers() {
        System.out.println("----- Queue of Integers -----");
        ArrayQueue.setDebug(true);
        Queue<Integer> queue = new ArrayQueue<>(6);
        System.out.println("Try to add 15 items:");
        for (int i = 0; i <= 15; i++) {
            try {
                queue.add(i);
                System.out.println(queue);
            }
            catch (IllegalStateException e) {
                System.out.println(e.getMessage());
                System.out.println("Removing three items...");
                for (int j = 0; j < 3; j++) {
                    Integer k = queue.remove();
                    System.out.printf("%2d %s%n", k, queue);
                }
                System.out.println("Continue adding...");
                queue.add(i);
                System.out.println(queue);
            }
        }
        System.out.println("Try to remove 10 items:");
        for (int i = 0; i <= 10; i++) {
            try {
                Integer k = queue.remove();
                System.out.printf("%2d %s%n", k, queue);
            }
            catch (NoSuchElementException e) {
                System.out.println("Queue empty");
                break;
            }
        }
    }

    private static void testQueueOfStrings() {
        System.out.println("----- Queue of Strings -----");
        QueueOfStrings queueOfStrings = new QueueOfStrings(6);
        String[] arr = { "тільки", "Київ", "Європа", "Пісня", "Перший", "другий", "Бажання", "Ukraine", "Білий" };
        for (String s : arr) {
            try {
                System.out.print("Word " + s);
                System.out.println((queueOfStrings.add(s) ? " " : " not ") + "added");
            }
            catch (Exception e) {
                System.out.printf("%n%s%n", e.getMessage());
            }
        }
        System.out.println(queueOfStrings.inAlphabeticOrder());
    }

    public static void main(String[] args) {
        testQueueOfIntegers();
        testQueueOfStrings();
    }
}
