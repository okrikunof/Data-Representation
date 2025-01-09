package Maps.LinkedListMap;

import ListElement.*;

public class Node {
    public ListElement data; // Данные, хранящиеся в узле, представляют собой объект ListElement
    public Node next; // Ссылка на следующий узел в списке

    public Node(ListElement data, Node next) {
        this.data = data; // Инициализация данных узла
        this.next = next; // Инициализация ссылки на следующий узел
    }

    public void SetAddress(ListElement value) {
        this.data.SetAddress(value.GetAddress()); // Устанавливаем новый адрес в данные узла
    }
}
