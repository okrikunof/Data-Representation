package Tests;

import Set.*;

import static Set.Set.*;

public class SetTest {
    public static void Test() {
        Set setA = new Set();
        setA.insert(1);
        setA.insert(5);
        setA.insert(7);
        setA.insert(3);
        setA.insert(-12);

        Set setB = new Set();
        setB.insert(11);
        setB.insert(52);
        setB.insert(72);
        setB.insert(34);
        setB.insert(-1);

        difference(setA, setB);
    }
}
