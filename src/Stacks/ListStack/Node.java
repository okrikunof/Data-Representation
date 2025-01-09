package Stacks.ListStack;

import ListElement.ListElement;

public class Node {
    public ListElement data; // Данные, хранимые в узле
    public Node next; // Указатель на следующий узел

    public Node(ListElement data, Node next) {
        this.data = data; // Инициализация данных узла
        this.next = next; // Инициализация указателя на следующий узел
    }
}
