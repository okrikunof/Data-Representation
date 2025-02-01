package Queues.CircularListQueue;

import ListElement.*;

public class Node {
    public ListElement data; // Данные, хранимые в узле
    public Node next; // Ссылка на следующий узел в списке

    public Node(ListElement data, Node next) {
        this.data = data; // Инициализируем данные узла
        this.next = next; // Инициализируем ссылку на следующий узел
    }
}
