package Stacks.ArrayStack;

import ListElement.ListElement;

public class Stack {
    private ListElement[] stack;
    private static final int SIZE = 100;
    private int top;

    public Stack() {
        stack = new ListElement[SIZE];
        top = -1;
    }

    public void Push(ListElement x) {
        stack[++top] = x;
    }

    public ListElement Pop() {
        return stack[top--];
    }

    public ListElement Top() {
        return stack[top];
    }

    public void MakeNull() {
        top = -1;
    }

    public boolean Full() {
        return top == SIZE - 1;
    }

    public boolean Empty() {
        return top == -1;
    }
}
