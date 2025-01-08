package Queues.CircularListQueue;

import ListElement.*;

public class Node {
    public ListElement data;
    public Node next;

    public Node(ListElement data, Node next) {
        this.data = data;
        this.next = next;
    }
}
