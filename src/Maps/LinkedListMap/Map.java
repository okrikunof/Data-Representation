package Maps.LinkedListMap;

import ListElement.*;

public class Map {
    private Node head;

    public Map() {
        this.head = null;
    }

    private Node findViaKey(ListElement x) {
        Node key = head;
        while (key != null) {
            if (key.data.Equals(x.GetName())) {
                return key;
            }
            key = key.next;
        }
        return null;
    }

    public void MakeNull() {
        this.head = null;
    }

    public void Assign(ListElement x) {
        // 1. Если список пустой создаем голову
        if (head == null) {
           head = new Node(x, null);
           return;
        }

        // 2. Ищем по ключу
        Node node = findViaKey(x);
        if (node != null) {
            node.SetAddress(x);
            return;
        }

        // 3. Если ключа нет, то вставляем после head
        if (head == null) {
            head.next = new Node(x, null);
            return;
        }

        head.next = new Node(x, head.next);
    }

    public boolean Compute(ListElement x) {
        Node node = findViaKey(x);
        if (node == null) {
            return false;
        }
        node.SetAddress(x);
        return true;
    }

    public void Print() {
        Node next = head;
        while (next != null) {
            next.data.Print();
            next = next.next;
        }
    }
}
