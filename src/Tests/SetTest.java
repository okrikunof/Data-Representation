package Tests;

import Set.*;

import static Set.Set.intersection;

public class SetTest {
    public static void Test() {
        Set setA = new Set();
        setA.insert(1);
        setA.insert(3);
        setA.insert(5);
        setA.insert(7);

        Set setB = new Set();
        setB.insert(2);
        setB.insert(3);
        setB.insert(4);
        setB.insert(6);

        System.out.println(setA.min());

    }
}
