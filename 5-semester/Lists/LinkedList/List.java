package Lists.LinkedList;

import ListElement.ListElement;

/**
 * Класс LinkedList предназначен для работы со списком элементов, хранящихся в узлах
 */
public class List {

    private Node head;   // Указатель на первый узел списка

    // Конструктор, инициализирующий пустой список
    public List() {
        this.head = null;
    }


    /**
     * Метод, который возвращает позицию последнего элемента
     * <p>
     * Возвращает позицию после последнего, которая указывает на конец списка.
     *
     * @return End() всегда возвращает null
     */
    public Position End() {
        return new Position(null);
    }

    /**
     * Метод, который возвращает предыдущий узел для переданного в метод узла
     * <p>
     * Проходим по всему списку от начала и пока не встретим переданный элемент.
     * После того как встретили возвращаем предыдущий узел, хранимый во временной переменной
     *
     * @return возвращает предыдущий узел при наличии, если предыдущий невозможно найти возвращает null
     */
    private Node prev(Node node) {
        Node curr = this.head;
        Node tmp = null;

        // проходимся по всему списку
        while (curr != null) {
            if (node == curr)
                return tmp;
            tmp = curr;
            curr = curr.next;
        }
        return null;
    }

    /**
     * Метод, который возвращает последний элемент в списке
     * <p>
     * Проходим по всему списку от начала и до конца.
     * После того как дошли до конца, возвращаем предыдущий узел
     *
     * @return возвращает последний элемент
     */
    private Node last() {
        Node temp = head;
        Node prev = null;
        while (temp != null) {
            prev = temp;
            temp = temp.next;
        }

        return prev;
    }

    /**
     * Метод, который вставляет новый элемент на указанную позицию
     * <p>
     * Вставляет элемент после узла, на который указывает позиция p.
     * Если позиция p равна null, элемент вставляется в начало списка.
     *
     * @param x Элемент, который нужно вставить
     * @param p Позиция, после которой нужно вставить объект
     */
    public void Insert(ListElement x, Position p) {
        /*
          Если p.node == null, то
          1) если head == null, то добавляем вместо head
        */
        if (p.node == null) {
            // если вставляем в пустой список
            if (head == null) {
                // создать новый объект класса Node(x) и присвоить head
                head = new Node(new ListElement(x));
                return;
            }
            /*
              2) Если голова не пуста, то вставляем в конец
            */
            Node tmp = last();
            tmp.next = new Node(new ListElement(x));
            return;
        }

        /*
          Если необходимо добвить элемент в голову
        */
        if (p.node == head) {
            Node newNode = new Node(x);
            newNode.next = head;
            head = newNode;
            return;
        }

        /*
          Вставляем в середину
          создаем переменную для предыдущего элемента
        */
        Node prev = prev(p.node);
        if (prev == null) {
            throw new RuntimeException("Invalid position");
        }

        // вставляем в середину списка
        Node tempNext = new Node();
        Node next = p.node.next;

        p.node.next = tempNext;
        tempNext.next = next;

        p.node.next.data = p.node.data;
        p.node.data = new ListElement(x);
    }

    /**
     * Метод, который ищет элемент в списке и возвращает его позицию.
     * <p>
     * Создаем временную переменную, которая будет началом списка (head).
     * Проходим в цикле while пока не найдем нужный нам элемент. Сравниваем текущий элемент с искомым,
     * если не совпадает переходим на current.next. Если мы дошли до конца списка, но элемент не найден
     * возвращаем End().
     *
     * @param x элемент, который надо найти
     * @return Position(current) в случае, если элемент найден, если не найден, то возвращаем End()
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
     * Метод, который возвращает элемент, находящийся в заданной позиции списка.
     * <p>
     * Проверяем, является ли заданная позиция корректной. Если позиция равна null,
     * выбрасываем исключение. Если позиция указывает на голову списка, возвращаем данные головы.
     * <p>
     * Создаем временную переменную, которая будет началом списка (head). Проходим в цикле while
     * пока не найдем узел, соответствующий заданной позиции. Сравниваем данные текущего узла
     * с данными узла в заданной позиции. Если совпадает, возвращаем данные текущего узла.
     * Если мы дошли до конца списка, но позиция не найдена, выбрасываем исключение.
     *
     * @param p Позиция, из которой нужно извлечь элемент
     * @return Элемент, находящийся в заданной позиции
     * @throws RuntimeException если позиция некорректна или не существует
     */
    public ListElement Retrieve(Position p) {
        if (p.node == null) {
            throw new RuntimeException("Invalid position");
        }
        if (p.node == head) {
            return head.data;
        }
        Node current = head;
        while (current != null) {
            if (current.data.Equals(p.node.data)) {
                return current.data;
            }
            current = current.next;
        }

        throw new RuntimeException("Position does not exist");
    }


    /**
     * Delete(Position p)
     * Удаляет элемент на позиции p из списка.
     * Если позиция некорректна или равна null, выбрасывается исключение.
     */
    public void Delete(Position p) {
        // проверяем не пустой ли список
        if (p.node == null || head == null) return;
        // обрабатываем удаление head
        if (p.node == head) {
            head = head.next;
            return;
        }

        // удаляем элемент из списка
        Position curr = new Position(prev(p.node));
        Node tmp = curr.node.next;
        curr.node.next = tmp.next;
        p.node = curr.node.next;
    }


    /**
     * Next(Position p)
     * Возвращает элемент на следующей позиции после p.
     */
    public Position Next(Position p) {
        if (p.node == null) {
            throw new RuntimeException("Invalid position");
        }
        return new Position(p.node.next);
    }


    /**
     * Previous(Position p)
     * Возвращает позицию элемента перед p
     *
     * @param p позиция у которой надо найти предыдущий элемент
     * @return позицию
     */
    public Position Previous(Position p) {
        if (p.node == null || p.node == head) {
            throw new RuntimeException("Invalid position");
        }
        Node node = prev(p.node);
        if (node == null) {
            throw new RuntimeException("Invalid position");
        }
        return new Position(prev(p.node));
    }

    /**
     * MakeNull()
     * Очищает список, удаляя все узлы.
     */
    public Position MakeNull() {
        head = null;
        return new Position(null);
    }


    /**
     * First()
     * Возвращает первую позицию списка.
     */
    public Position First() {
        return new Position(head);
    }


    /**
     * PrintList()
     * Печатает содержимое списка.
     * Если список пуст, выводится сообщение об этом.
     */
    public void PrintList() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node current = head;
        while (current != null) {
            current.data.Print();
            current = current.next;
        }
    }
}
