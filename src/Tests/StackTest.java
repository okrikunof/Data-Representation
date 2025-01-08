package Tests;

import ListElement.ListElement;

import Stacks.ArrayStack.*;
//import Stacks.ListStack.*;
//import Stacks.AdtStack.*;

public class StackTest {
    public static void Test() {
        Stack stack = new Stack();

        stack.Push(new ListElement("Paris", "France"));
        stack.Top().Print();
        stack.Push(new ListElement("London", "England"));
        stack.Top().Print();
        stack.Push(new ListElement("Moscow", "Russia"));
        stack.Top().Print();
        stack.Push(new ListElement("Berlin", "Germany"));
        stack.Top().Print();
        stack.Push(new ListElement("Sofia", "Bulgaria"));
        stack.Top().Print();

        System.out.println("\nВынимаем элементы из стека");
        while(!stack.Empty()){
            stack.Pop().Print();
        }
    }
}
