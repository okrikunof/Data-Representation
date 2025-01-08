package Queues.CircularListQueue;

import ListElement.*;

public class Queue {
    private Node tail;

    public Queue() {
        this.tail = null;
    }

    public void Enqueue(ListElement x) {
        // 1. Если список пустой
        if (tail == null) {
            tail = new Node(x, null);
            tail.next = tail;
            return;
        }

        // 2. Оставшиеся случаи
        tail.next = new Node(x, tail.next);
        tail = tail.next;
    }

    public ListElement Dequeue() {
        ListElement data = tail.next.data;

        // 1. Когда в списке один элемент
        if (tail.next == tail) {
            tail = null;
            return data;
        }

        // 2. Оставшиеся случи
        tail.next = tail.next.next;
        return data;
    }

    public ListElement Front() {
        return tail.next.data;
    }

    public boolean Full() {
        return false;
    }

    public boolean Empty() {
        return tail == null;
    }

    public void MakeNull() {
        tail = null;
    }
}
