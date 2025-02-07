package Set;

/**
 * Класс множества, который представляет собой закольцованый список, хранящий указатель на первый и последний элемент
 */
public class CycledList {
    private Node head;        // указатель на начало списка
    private Node tail;        // указатель на конец списка

    /**
     * Инициализирующий конструктор класса
     */
    public CycledList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Копирующий конструктор
     * @param cycledList ссылка на множество, которое надо скопировать
     */
    public CycledList(CycledList cycledList) {
        if (cycledList == null || cycledList.isEmpty()) {
            this.head = null;
            this.tail = null;
            return;
        }

        // Копируем первый элемент
        this.head = new Node(cycledList.head.getData());
        Node currentOriginal = cycledList.head.getNext();
        Node currentCopy = this.head;

        // Копируем остальные элементы
        while (currentOriginal != cycledList.head) {
            Node newNode = new Node(currentOriginal.getData());
            currentCopy.setNext(newNode);
            currentCopy = newNode;
            currentOriginal = currentOriginal.getNext();
        }

        // Замыкаем список
        this.tail = currentCopy;
        this.tail.setNext(this.head);
    }

    /**
     * Метод возвращающий true, если множество пустое, false в противном случае
     */
    public boolean isEmpty() {
        return head == null;
    }


    public void insert(int element) {
        // Если список пуст, создаем новый узел и делаем его head и tail
        if (isEmpty()) {
            head = new Node(element);
            tail = head;
            head.setNext(tail); // Замыкаем список на себя
            tail.setNext(head); // Замыкаем список на себя
            return;
        }

        // Если элемент уже существует в списке, ничего не делаем
        if (findElement(element)) {
            return;
        }

        // Создаем новый узел
        Node newNode = new Node(element);

        // Если элемент меньше head, он становится новым head
        if (element < head.getData()) {
            newNode.setNext(head);
            head = newNode;
            tail.setNext(head); // Обновляем tail, чтобы он указывал на новый head
        }
        // Если элемент больше tail, он становится новым tail
        else if (element > tail.getData()) {
            tail.setNext(newNode);
            tail = newNode;
            tail.setNext(head); // Замыкаем список на head
        }
        // Если элемент находится между head и tail, вставляем его в правильную позицию
        else {
            Node current = head;
            // Ищем позицию для вставки
            while (current.getNext() != head && current.getNext().getData() < element) {
                current = current.getNext();
            }
            // Вставляем новый узел после current
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
    }

    /**
     *
     * @param element
     */
    public void delete(int element) {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        // Если элемент не найден в списке
        if (!findElement(element)) {
            System.out.println("Element not found");
            return;
        }

        // Случай, когда элемент — единственный в списке
        if (head == tail && head.getData() == element) {
            head = null;
            tail = null;
            return;
        }

        // Случай, когда элемент находится в head
        if (head.getData() == element) {
            head = head.getNext();
            tail.setNext(head); // Обновляем tail, чтобы он указывал на новый head
            return;
        }

        // Случай, когда элемент находится в tail
        if (tail.getData() == element) {
            Node current = head;
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            current.setNext(head); // Удаляем tail и обновляем ссылку
            tail = current; // Обновляем tail
            return;
        }

        // Случай, когда элемент находится в середине списка
        Node current = head;
        while (current.getNext().getData() != element) {
            current = current.getNext();
        }
        current.setNext(current.getNext().getNext());
    }

    public void printSet() {
        // Если список пуст, выводим сообщение и завершаем выполнение
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        // Начинаем с головы списка
        Node temp = head;

        // Используем do-while, чтобы гарантированно вывести все элементы
        do {
            temp.print(); // Выводим данные текущего узла
            temp = temp.getNext(); // Переходим к следующему узлу
        } while (temp != head); // Продолжаем, пока не вернемся к голове

        System.out.println(); // Переход на новую строку после вывода всех элементов
    }

    /**
     * Метод для поиска элемента в кольцевом списке
     * @param element элемент, который нужно найти
     * @return true, если элемент найден, false в противном случае
     */
    private boolean findElement(int element) {
        if (isEmpty()) {
            return false; // Если список пуст, элемент не найден
        }

        Node current = head;
        do {
            if (current.getData() == element) {
                return true; // Элемент найден
            }
            current = current.getNext();
        } while (current != head); // Продолжаем, пока не вернемся к голове

        return false; // Элемент не найден
    }
}
