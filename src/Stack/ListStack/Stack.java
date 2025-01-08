package Stack.ListStack;

import ListElement.ListElement;

public class Stack {
    private Node head;

    public Stack() {
        this.head = null;
    }

    public void Push(ListElement x) {
        head = new Node(x, head);
    }

    public ListElement Pop() {
        ListElement data = head.data;
        head = head.next;
        return data;
    }

    public ListElement Top() {
        return head.data;
    }

    public boolean Full() {
        return false;
    }

    public boolean Empty() {
        return head == null;
    }

    public void MakeNull() {
        head = null;
    }
}
