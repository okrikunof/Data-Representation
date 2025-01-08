package Maps.LinkedListMap;

import ListElement.*;

public class Node {
    public ListElement data;
    public Node next;

    public Node(ListElement data, Node next) {
        this.data = data;
        this.next = next;
    }

    public void SetAddress(ListElement value) {
        this.data.SetAddress(value.GetAddress());
    }
}
