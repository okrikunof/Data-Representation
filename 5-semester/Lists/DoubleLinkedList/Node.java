package Lists.DoubleLinkedList;

import ListElement.ListElement;

/**
 * Класс Node, который представляет собой узел двусвязного списка, содержащий данные и указатель на следующий узел и предыдущий
 */
public class Node {
    ListElement data;       // данные в узле
    public Node next;       // указатель на следующий элемент
    public Node previous;   // указатель на предыдущий элемент

    // Конструктор для узла
    public Node(ListElement data) {
        this.data = new ListElement(data);
        this.next = null;
        this.previous = null;
    }
}
