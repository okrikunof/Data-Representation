package Maps.LinkedListMap;

import ListElement.*;

public class Node {
    public ListElement data; // Данные, хранящиеся в узле, представляют собой объект ListElement
    public Node next; // Ссылка на следующий узел в списке

    public Node(ListElement data, Node next) {
        this.data = data; // Инициализация данных узла
        this.next = next; // Инициализация ссылки на следующий узел
    }

    public Node(char[] name, char[] address, Node node) {
        next = node;
        data = new ListElement(name, address);
    }

    public void SetAddress(char[] value) {
        this.data.SetAddress(value); // Устанавливаем новый адрес в данные узла
    }
}
