package Tests;

import ListElement.ListElement;

import Stacks.ArrayStack.*;
//import Stacks.ListStack.*;
//import Stacks.AdtStack.*;

public class StackTest {
    public static void Test() {
        Stack stack = new Stack();

        stack.Push("Paris".toCharArray());
        System.out.println(stack.Top());
        stack.Push("London".toCharArray());
        System.out.println(stack.Top());
        stack.Push("Moscow".toCharArray());
        System.out.println(stack.Top());
        stack.Push("Berlin".toCharArray());
        System.out.println(stack.Top());
        stack.Push("Sofia".toCharArray());
        System.out.println(stack.Top());

        System.out.println();
        System.out.println("Достаем элементы");
        while(!stack.Empty()){
            System.out.println(stack.Pop());
        }
    }
}
