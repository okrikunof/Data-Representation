package Stacks.AdtStack;

import ListElement.ListElement;

import Lists.ArrayList.*;
//import Lists.LinkedList.*;
//import Lists.DoubleLinkedList.*;
//import Lists.CursorList.*;

public class Stack {
    private List list;
    
    public Stack() {
        list = new List();
    }
    
    public void Push(ListElement x) {
        list.Insert(x, list.First());
    }

    public ListElement Pop() {
        ListElement data = list.Retrieve(list.First());
        list.Delete(list.First());
        return data;
    }


    public ListElement Top() {
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
