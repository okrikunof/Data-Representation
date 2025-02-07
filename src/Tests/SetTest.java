package Tests;
import Set.*;

public class SetTest {
    public static void Test() {
        CycledList cycledList = new CycledList();
        cycledList.insert(5);
        cycledList.insert(3);
        cycledList.insert(8);
        cycledList.insert(1);
        cycledList.insert(7);
        cycledList.printSet();

        cycledList.delete(1);
        cycledList.delete(3);
        cycledList.delete(5);
        cycledList.delete(7);
        cycledList.delete(8);

        cycledList.printSet();
    }
}
