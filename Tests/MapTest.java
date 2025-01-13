package Tests;

import ListElement.*;

//import Maps.LinkedListMap.*;
import Maps.AdtMap.*;

public class MapTest {
    public static void Test() {
        Map map = new Map();

        map.Assign("Paris".toCharArray(), "France".toCharArray());
        map.Assign("London".toCharArray(), "England".toCharArray());
        map.Assign("Berlin".toCharArray(), "Germany".toCharArray());
        map.Print();

        System.out.println(map.Compute("Tumba".toCharArray(), "Unba".toCharArray()));

        System.out.println(map.Compute("London".toCharArray(), "Umba".toCharArray()));
        map.Print();
    }
}
