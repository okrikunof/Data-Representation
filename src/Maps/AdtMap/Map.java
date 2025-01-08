package Maps.AdtMap;

import ListElement.*;

import Lists.ArrayList.*;
//import Lists.LinkedList.*;
//import Lists.DoubleLinkedList.*;
//import Lists.CursorList.*;

public class Map {
    private List list;

    public Map() {
        list = new List();
    }

    private Position findViaKey(ListElement x) {
        Position position = list.First();

        while (!position.Equals(list.End())) {
            ListElement current = list.Retrieve(position);
            if (x.Equals(current.GetName())) {
                return position;
            }
            position = list.Next(position);
        }

        return position;
    }

    public void MakeNull() {
        list.MakeNull();
    }

    public void Assign(ListElement x) {
        // 1. Если список пустой, добавляем первым
        if (list.First().Equals(list.End())) {
            list.Insert(x, list.First());
            return;
        }

        Position position = findViaKey(x);
        if (!position.Equals(list.End())) {
            list.Retrieve(position).SetAddress(x.GetAddress());
            return;
        }

        // 2. Если ключ не найден вставляем на первое место
        list.Insert(new ListElement(x), list.First());
    }

    public boolean Compute(ListElement x) {
        Position position = findViaKey(x);
        if (position.Equals(list.End())) {
            return false;
        }
        list.Retrieve(position).SetAddress(x.GetAddress());
        return true;
    }

    public void Print() {
        list.PrintList();
        System.out.println();
    }
}