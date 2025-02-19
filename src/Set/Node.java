package Set;

/**
 * Класс Node, который представляет собой узел цикличного списка, содержащий данные и указатель на следующий узел
 */
public class Node {
    private int data;  // данные узла
    private Node next;  // указатель на следующий узел


    public Node() {
        this.next = null;
    }


    /**
     * Конструктор для узла множества
     * @param data число, которое будет лежать в узле
     */
    public Node(int data) {
        this.data = data;   // присваиваем узлу значение
        this.next = null;   // ставим указатель следующего элемента на null
    }


    public int getData() {
        return data;
    }


    public void setData(int data) {
        this.data = data;
    }


    public Node getNext() {
        return next;
    }


    public void setNext(Node next) {
        this.next = next;
    }


    public void print() {
        System.out.print(data + " ");
    }
}
