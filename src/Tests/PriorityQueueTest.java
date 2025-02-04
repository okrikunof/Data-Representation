package Tests;

import PriorityQueue.*;

import java.util.Random;

public class PriorityQueueTest {
    public static void Test() {
        PriorityQueue priorityQueue = new PriorityQueue();
        Random random = new Random();

        // Добавляем значения для тестирования в диапазоне от 1 до 10
        for (int i = 0; i < 5; i++) {
            int randomValue = random.nextInt(10) + 1;
            System.out.println("Добавили: " + randomValue);
            priorityQueue.Insert(randomValue);
        }

        System.out.println("\nОчередь после вставки:");
        priorityQueue.Print();

        System.out.println("\nУдален корень: " + priorityQueue.DeleteMin());

        System.out.println("\nОчередь после удаления корня:");
        priorityQueue.Print();
    }
}
