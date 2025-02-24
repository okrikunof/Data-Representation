package Tests;

import ListElement.ListElement;

import Lists.ArrayList.*;
//import Lists.LinkedList.*;
//import Lists.DoubleLinkedList.*;
//import Lists.CursorList.*;

public class ListTest {
    public static void Test() {
        List list = new List();

        list.Insert(new ListElement("1", "A"), list.First());
        list.Insert(new ListElement("2", "B"), list.End());
        list.Insert(new ListElement("2", "B"), list.End());
        list.Insert(new ListElement("2", "B"), list.End());
        list.Insert(new ListElement("2", "B"), list.End());
        list.Insert(new ListElement("3", "C"), list.First());
        list.Insert(new ListElement("1", "A"), list.End());
        list.Insert(new ListElement("2", "B"), list.End());
        list.Insert(new ListElement("2", "B"), list.End());
        list.Insert(new ListElement("2", "B"), list.End());

        list.PrintList();

        RemoveDuplicates(list);

        System.out.println("\nСписок после удаления");

        list.PrintList();
    }

    public static void RemoveDuplicates(List list) {
        // Удаление дубликатов
        Position p = list.First();
        while (!p.Equals(list.End())) {
            Position q = list.Next(p);
            while (!q.Equals(list.End())) {
                if (list.Retrieve(p).Equals(list.Retrieve(q))) {
                    list.Delete(q);
                } else {
                    q = list.Next(q);
                }
            }
            p = list.Next(p);
        }
    }
}
