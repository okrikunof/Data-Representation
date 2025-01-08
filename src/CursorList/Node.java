package CursorList;

import ListElement.ListElement;

/**
 * Класс Node, который представляет собой узел списка на курсорах, содержащий данные и указатель на следующий узел
 */
public class Node {
    public ListElement data; // данные в узле
    public int next = -1;    // указатель на следующий элемент

    public Node(ListElement data) {
        this.data = new ListElement(data);
    }

    public Node(int i) {
        next = i;
    }

    public Node(Position position) {
        this.next = position.position;
    }
}
