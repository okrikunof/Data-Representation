package Set;

/**
 * Класс множества, который представляет собой закольцованный список, хранящий указатель на последний элемент
 */
public class CycledList {
    private Node tail; // указатель на конец списка

    /**
     * Инициализирующий конструктор класса
     */
    public CycledList() {
        this.tail = null;
    }

    /**
     * Копирующий конструктор
     * @param cycledList ссылка на множество, которое надо скопировать
     */
    public CycledList(CycledList cycledList) {
        if (cycledList == null || cycledList.isEmpty()) {
            this.tail = null;
            return;
        }

        // Копируем первый элемент
        Node currentOriginal = cycledList.getHead();
        this.tail = new Node(currentOriginal.getData());
        this.tail.setNext(this.tail); // Замыкаем список на себя

        Node currentCopy = this.tail;
        currentOriginal = currentOriginal.getNext();

        // Копируем остальные элементы
        while (currentOriginal != cycledList.getHead()) {
            Node newNode = new Node(currentOriginal.getData());
            currentCopy.setNext(newNode);
            currentCopy = newNode;
            currentOriginal = currentOriginal.getNext();
        }

        // Замыкаем список
        currentCopy.setNext(this.tail);
        this.tail = currentCopy;
    }

    /**
     * Метод возвращающий true, если множество пустое, false в противном случае
     */
    public boolean isEmpty() {
        return tail == null;
    }

    /**
     * Метод для получения головы списка
     * @return первый элемент списка
     */
    public Node getHead() {
        if (isEmpty()) {
            return null;
        }
        return tail.getNext(); // Голова списка — это следующий элемент после tail
    }

    /**
     * Метод для получения хвоста списка
     * @return последний элемент списка
     */
    public Node getTail() {
        return tail;
    }

    /**
     * Метод для вставки элемента в список
     * @param element элемент для вставки
     */
    public void insert(int element) {
        // Если список пуст, создаем новый узел и делаем его tail
        if (isEmpty()) {
            tail = new Node(element);
            tail.setNext(tail); // Замыкаем список на себя
            return;
        }

        // Если элемент уже существует в списке, ничего не делаем
        if (findElement(element)) {
            return;
        }

        // Создаем новый узел
        Node newNode = new Node(element);

        // Если элемент меньше головы списка, он становится новой головой
        if (element < getHead().getData()) {
            newNode.setNext(getHead());
            tail.setNext(newNode); // Обновляем tail, чтобы он указывал на новую голову
        }
        // Если элемент больше tail, он становится новым tail
        else if (element > tail.getData()) {
            newNode.setNext(getHead());
            tail.setNext(newNode);
            tail = newNode;
        }
        // Если элемент находится между head и tail, вставляем его в правильную позицию
        else {
            Node current = getHead();
            // Ищем позицию для вставки
            while (current.getNext() != getHead() && current.getNext().getData() < element) {
                current = current.getNext();
            }
            // Вставляем новый узел после current
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
    }

    /**
     * Метод для удаления элемента из списка
     * @param element элемент для удаления
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
        if (tail.getNext() == tail && tail.getData() == element) {
            tail = null;
            return;
        }

        // Случай, когда элемент находится в голове списка
        if (getHead().getData() == element) {
            tail.setNext(getHead().getNext()); // Обновляем tail, чтобы он указывал на новый head
            return;
        }

        // Случай, когда элемент находится в tail
        if (tail.getData() == element) {
            Node current = getHead();
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            current.setNext(getHead()); // Удаляем tail и обновляем ссылку
            tail = current; // Обновляем tail
            return;
        }

        // Случай, когда элемент находится в середине списка
        Node current = getHead();
        while (current.getNext().getData() != element) {
            current = current.getNext();
        }
        current.setNext(current.getNext().getNext());
    }

    /**
     * Метод для печати списка
     */
    public void printSet() {
        // Если список пуст, выводим сообщение и завершаем выполнение
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        // Начинаем с головы списка
        Node temp = getHead();

        // Используем do-while, чтобы гарантированно вывести все элементы
        do {
            temp.print(); // Выводим данные текущего узла
            temp = temp.getNext(); // Переходим к следующему узлу
        } while (temp != getHead()); // Продолжаем, пока не вернемся к голове

        System.out.println(); // Переход на новую строку после вывода всех элементов
    }

    /**
     * Метод для поиска элемента в списке
     * @param element элемент, который нужно найти
     * @return true, если элемент найден, false в противном случае
     */
    public boolean findElement(int element) {
        if (isEmpty()) {
            return false; // Если список пуст, элемент не найден
        }

        Node current = getHead();
        do {
            if (current.getData() == element) {
                return true; // Элемент найден
            }
            current = current.getNext();
        } while (current != getHead()); // Продолжаем, пока не вернемся к голове

        return false; // Элемент не найден
    }
}