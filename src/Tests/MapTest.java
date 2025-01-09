package Tests;

import ListElement.*;

//import Maps.LinkedListMap.*;
import Maps.AdtMap.*;

public class MapTest {
    public static void Test() {
        Map map = new Map();

        ListElement pair1 = new ListElement("Paris", "France");
        map.Assign(pair1);
        map.Print();
        ListElement pair2 = new ListElement("London", "England");
        map.Assign(pair2);
        map.Print();
        ListElement pair3 = new ListElement("Paris", "Russia");
        map.Assign(pair3);
        map.Print();

        ListElement pair4 = new ListElement("Tumba", "Umba");
        map.Compute(pair4);

        ListElement pair5 = new ListElement("London", "Zimbabwe");
        System.out.println(map.Compute(pair5));
        map.Print();
    }
}
