package Queues.ArrayQueue;

import ListElement.*;

public class Queue {
    private ListElement[] queue;
    private final int SIZE = 100;
    private int head;
    private int tail;

    public Queue() {
        queue = new ListElement[SIZE];
        head = -1;
        tail = -1;
    }

    public boolean Full() {
        return tail == SIZE - 1;
    }

    public boolean Empty() {
        return head == -1;
    }

    public void Enqueue(ListElement x) {
        if (Empty()) {
            head = tail = 0;
            queue[tail] = x;
        }
        else {
            tail++;
            queue[tail] = x;
        }
    }

    public ListElement Dequeue() {
        if (head == tail) {
            ListElement data = queue[head];
            head = tail = -1;
            return data;
        }
        else {
            ListElement data = queue[head];
            head++;
            return data;
        }
    }

    public void MakeNull() {
        head = tail = -1;
    }

    public ListElement Front() {
        ListElement front = queue[head];
        return front;
    }
}
