package Tests;

import ListElement.ListElement;

import Queues.ArrayQueue.*;
//import Queues.CircularListQueue.*;
//import Queues.AdtQueue.*;

public class QueueTest {
    public static void Test() {
        Queue queue = new Queue();

        queue.Enqueue(new ListElement("Paris", "France"));
        queue.Front().Print();
        queue.Enqueue(new ListElement("London", "England"));
        queue.Front().Print();
        queue.Enqueue(new ListElement("Moscow", "Russia"));
        queue.Front().Print();
        queue.Enqueue(new ListElement("Berlin", "Germany"));
        queue.Front().Print();
        queue.Enqueue(new ListElement("Sofia", "Bulgaria"));
        queue.Front().Print();

        System.out.println("\nВынимаем элементы из очереди");
        while(!queue.Empty()){
            queue.Dequeue().Print();
        }
    }
}
