package Queues.AdtQueue;

import ListElement.*;

import Lists.ArrayList.*;
//import Lists.LinkedList.*;
//import Lists.DoubleLinkedList.*;
//import Lists.CursorList.*;

public class Queue {
    private List list;

    public Queue() {
        list = new List();
    }

    public void Enqueue(ListElement x) {
        list.Insert(x, list.End());
    }

    public ListElement Dequeue() {
        ListElement data = list.Retrieve(list.First());
        list.Delete(list.First());
        return data;
    }

    public ListElement Front() {
        return list.Retrieve(list.First());
    }

    public boolean Full() {
        return false;
    }

    public boolean Empty() {
        return list.First().Equals(list.End());
    }

    public void MakeNull() {
        list.MakeNull();
    }


}
