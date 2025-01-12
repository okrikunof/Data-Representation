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

    private static boolean compareCharArrays(char[] value1, char[] value2) {
        int len = Math.min(value1.length, value2.length);
        for (int i = 0; i < len; i++) {
            if (value1[i] == '0' || value2[i] == '0') continue;
            if (value1[i] != value2[i]) return false;
        }
        return true;
    }

    private Position findViaKey(char[] key) {
        Position position = list.First();

        while (!position.Equals(list.End())) {
            ListElement temp = list.Retrieve(position);
            if (compareCharArrays(key, temp.GetName()))
                return position;
            position = list.Next(position);
        }
        return position;
    }

    public void MakeNull() {
        list.MakeNull();
    }

    public void Assign(char[] d, char[] r) {
        // Если список пустой, добавляем первым элементом
        if (list.First().Equals(list.End())) {
            list.Insert(new ListElement(d, r), list.First());
            return;
        }

        Position position = findViaKey(d);
        if(!position.Equals(list.End())) {
            list.Retrieve(position).SetAddress(r);
            return;
        }

        // Если ключ не найден, вставляем на первую позицию
        list.Insert(new ListElement(d,r), list.First()) ;
    }

    public boolean Compute(char[] d, char[] r) {
        Position position = findViaKey(d);
        if(position.Equals(list.End()))
            return false;
        list.Retrieve(position).SetAddress(r);
        return true;
    }

    public void Print() {
        list.PrintList();
        System.out.println();
    }
}