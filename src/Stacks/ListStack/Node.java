package Stacks.ListStack;

import ListElement.ListElement;

public class Node {
    public char data; // Данные, хранимые в узле
    public Node next; // Указатель на следующий узел

    public Node(char data, Node next) {
        this.data = data; // Инициализация данных узла
        this.next = next; // Инициализация указателя на следующий узел
    }
}
