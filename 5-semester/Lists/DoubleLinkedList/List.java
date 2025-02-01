package Lists.DoubleLinkedList;

import ListElement.ListElement;

/**
 * Класс DoubleLinkedList представляет собой реализацию двусвязного списка.
 */
public class List {
    private Node head; // Указатель на первый элемент списка
    private Node tail; // Указатель на последний элемент списка

    /**
     * Конструктор для инициализации пустого списка.
     * Изначально указатели head и tail равны null.
     */
    public List() {
        head = null;
        tail = null;
    }

    /**
     * Метод, проверяющий, находится ли заданная позиция в списке.
     *
     * @param p позиция для проверки
     * @return true, если позиция присутствует в списке; иначе false
     */
    private boolean inList(Position p) {
        // Проходим по всем узлам списка и сравниваем их с заданной позицией
        Node tmp = head;
        while (tmp != null) {
            if (tmp.equals(p.node)) return true; // Позиция найдена
            tmp = tmp.next;
        }
        return false; // Позиция не найдена
    }

    /**
     * Вспомогательный метод для поиска узла по значению.
     *
     * @param listElement элемент для поиска
     * @return узел, содержащий указанный элемент, или null, если элемент не найден
     */
    private Node findNode(ListElement listElement) {
        // Перебираем узлы, пока не найдем совпадение по значению
        Node curr = head;
        while (curr != null) {
            if (curr.data.equals(listElement)) {
                return curr; // Узел найден
            }
            curr = curr.next;
        }
        return null; // Элемент отсутствует в списке
    }

    /**
     * Метод для вставки нового элемента в заданную позицию.
     *
     * @param x элемент для вставки
     * @param p позиция, перед которой нужно вставить новый элемент
     */
    public void Insert(ListElement x, Position p) {
        // Обработка случая с пустым списком или списком из одного элемента
        if (p.node == null) {
            // Вставка в пустой список
            if (head == null) {
                head = new Node(x); // Создаем новый узел и назначаем его первым
                tail = head; // Первый элемент одновременно является и последним
                return;
            }
            if (head == tail) {
                // Добавление второго элемента в список
                tail = new Node(x);
                tail.previous = head; // Указываем, что tail следует за head
                head.next = tail; // Указываем, что head ссылается на tail
                return;
            }
            Node tmp = new Node(x);
            tail.next = tmp;
            tmp.previous = tail;
            tail = tmp; // Обновляем указатель на последний элемент
            return;
        }

        // Вставка перед первым элементом
        if (p.node == head) {
            Node curr = p.node;
            Node nodeX = new Node(x);
            curr.previous = nodeX;
            nodeX.next = curr;
            head = nodeX; // Новый узел становится первым
            return;
        }

        // Вставка перед последним элементом
        if (p.node == tail) {
            Node currPrev = tail.previous;
            currPrev.next = new Node(x);
            currPrev.next.previous = currPrev;
            currPrev.next = tail;
            tail.previous = currPrev.next;
            return;
        }

        // Вставка в середину списка
        if (!inList(p)) {
            return;
        } else {
            Node tmp = p.node;
            Node next = tmp.next;
            next.next = new Node(x); // Создаем новый узел и связываем его с текущим и следующим
            next.next.previous = next;
            next.next = tmp;
            tmp.previous = tmp.next;
        }
    }

    /**
     * Метод для удаления элемента из списка по позиции.
     *
     * @param p позиция элемента для удаления
     */
    public void Delete(Position p) {
        if(p.node == null || head == null)
            return;
        //Если позиция является головой
        if (p.node == head) {
            //если в списке один элемент (head=tail)
            if (head == tail) {
                head = null;
                tail = null;
                return;
            }
            //иначе удаляем объект из головы:
            head = p.node.next;
            //head теперь ссылается на следующий элемент
            head.previous = null;
            //обнуляем ссылку previous у нового элемента головы
            p.node = p.node.next;
            //обновляем позицию p.node = p.node.next;
            return;
            //return
        }
        // Если позиция является хвостом
        if (p.node == tail) {
            //обновляем  tail, чтобы он указывал на предыдущий элемент
            tail = p.node.previous;
            //обнуляем ссылку next у нового хвоста (null)
            tail.next = null;
            //обновляем позицию p.node = p.node.next (или сразу null просто?)
            p.node = p.node.next;
            return;
        }
        //Иначе: (поиск позиции)
        if (!inList(p)) {
            throw new RuntimeException("Invalid position");
        }
        p.node.previous.next = p.node.next;
        // обновляем ссылку next у предыдущего элемента (p.node.previous) так, чтобы она указывала на следующий элемент (p.node.next)
        p.node.next.previous =  p.node.previous;
        // обновляем previous у следуюещего на предыдущий
        p.node = p.node.next;
        //обновляем позицию
    }

    /**
     * Метод, находящий позицию элемента с заданным значением.
     *
     * @param x элемент для поиска
     * @return позиция найденного элемента или null, если элемент не найден
     */
    public Position Locate(ListElement x) {
        Node current = head;      // Создаем временную переменную, которая = head

        while (current != null) {       // Проходим по списку пока не найдем или не дойдем до конца
            if (current.data.Equals(x)) {
                return new Position(current);
            }
            current = current.next;
        }

        return new Position(null); // Если элемент не найден, возвращаем позицию после последнего узла
    }

    /**
     * Метод, возвращающий элемент по указанной позиции.
     *
     * @param p позиция элемента
     * @return элемент на указанной позиции
     * @throws RuntimeException если позиция некорректна
     */
    public ListElement Retrieve(Position p) {
        if (p == null || p.Equals(End()))
            throw new RuntimeException("Invalid position");
        return p.node.data;
    }

    /**
     * Метод, возвращающий позицию предыдущего элемента.
     *
     * @param p текущая позиция
     * @return позиция предыдущего элемента
     * @throws RuntimeException если позиция некорректна
     */
    public Position Previous(Position p) {
        if (p.node == null || p.node == head)
            throw new RuntimeException("Invalid position");
        return new Position(p.node.previous);
    }

    /**
     * Метод, возвращающий позицию следующего элемента.
     *
     * @param p текущая позиция
     * @return позиция следующего элемента
     * @throws RuntimeException если позиция некорректна
     */
    public Position Next(Position p) {
        if (p.node == null)
            throw new RuntimeException("Invalid position");
        return new Position(p.node.next);
    }

    /**
     * Метод, возвращающий позицию за последним элементом.
     *
     * @return позиция за последним элементом
     */
    public Position End() {
        return new Position(null);
    }

    /**
     * Метод, возвращающий позицию первого элемента.
     *
     * @return позиция первого элемента
     */
    public Position First() {
        return new Position(head);
    }

    /**
     * Метод, очищающий список.
     *
     * @return пустая позиция
     */
    public Position MakeNull() {
        head = null;
        tail = null;
        return new Position(null);
    }

    /**
     * Метод, печатающий все элементы списка.
     * Если список пуст, выводится сообщение об этом.
     */
    public void PrintList() {
        if (head == null || tail == null) {
            System.out.println("List is empty");
            return;
        }
        Node curr = head;
        while (curr != null) {
            curr.data.Print();
            curr = curr.next;
        }
    }
}
