package Stacks.ArrayStack;

public class Stack {
    private char[] stack;
    private static final int SIZE = 100;
    private int top;

    public Stack() {
        stack = new char[SIZE];
        top = -1;
    }

    public void Push(char[] name) {
        for (int i = 0; i < name.length; i++) {
            stack[++top] = name[i];
        }
    }

    public char Pop() {
        return stack[top--];
    }

    public char Top() {
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
