package Tests;

import ListElement.ListElement;

//import Stack.ArrayStack.*;
//import Stack.ListStack.*;
import Stacks.AdtStack.*;

public class StackTest {
    public static void Test() {
        Stack stack = new Stack();
        stack.Push(new ListElement("Sigma", "Boy"));
        stack.Top().Print();
        stack.Push(new ListElement("Mark", "Peter"));
        stack.Top().Print();
        stack.Push(new ListElement("Volodya", "Moscow"));
        stack.Top().Print();
        stack.Push(new ListElement("Kirill", "Kazan"));
        stack.Top().Print();
        stack.Push(new ListElement("Pharan", "Paran"));
        stack.Top().Print();

        System.out.println("\nВынимаем элементы из стека");
        while(!stack.Empty()){
            stack.Pop().Print();
        }
    }
}
