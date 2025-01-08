package LinkedList;

import ListElement.ListElement;

/**
 * Класс Node, который представляет собой узел связного списка, содержащий данные и указатель на следующий узел
 */
public class Node {
    public ListElement data;  // данные узла
    public Node next;         // указатель на следующий узел

    // Конструктор для копирования ListElement
    public Node(ListElement data) {
        this.data = new ListElement(data);
        this.next = null;
    }

    public Node() {
        this.data = null;
        this.next = null;
    }
}
