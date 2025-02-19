package Set;

/**
 * Класс множества, который представляет собой закольцованный список, хранящий указатель на последний элемент.
 * Список отсортирован по возрастанию.
 */
public class CycledList {
    private Node tail; // Указатель на последний элемент списка (хвост)

    /**
     * Инициализирующий конструктор класса.
     * Создает пустой список.
     */
    public CycledList() {
        this.tail = new Node(); // Инициализируем tail как null, так как список пуст
    }


    /**
     * Копирующий конструктор.
     * Создает копию существующего списка.
     *
     * @param cycledList Ссылка на список, который нужно скопировать.
     */
    public CycledList(CycledList cycledList) {
        // Если исходный список пуст или равен null, создаем пустой список
        if (cycledList == null || cycledList.isEmpty()) {
            this.tail = null;
            return;
        }

        // Копируем первый элемент исходного списка
        Node currentOriginal = cycledList.getHead(); // Получаем голову исходного списка
        this.tail = new Node(currentOriginal.getData()); // Создаем новый узел с данными из головы
        this.tail.setNext(this.tail); // Замыкаем список на себя (так как пока только один элемент)

        Node currentCopy = this.tail; // Указатель на текущий узел в новом списке
        currentOriginal = currentOriginal.getNext(); // Переходим к следующему элементу исходного списка

        // Копируем остальные элементы исходного списка
        while (currentOriginal != cycledList.getHead()) {
            Node newNode = new Node(currentOriginal.getData()); // Создаем новый узел с данными
            currentCopy.setNext(newNode); // Связываем текущий узел с новым
            currentCopy = newNode; // Перемещаем указатель на новый узел
            currentOriginal = currentOriginal.getNext(); // Переходим к следующему элементу исходного списка
        }

        // Замыкаем список, связывая последний узел с головой
        currentCopy.setNext(this.tail);
        this.tail = currentCopy; // Обновляем tail на последний скопированный узел
    }


    /**
     * Метод для проверки, пуст ли список.
     *
     * @return true, если список пуст, иначе false.
     */
    public boolean isEmpty() {
        return tail == null; // Список пуст, если tail равен null
    }

    /**
     * Метод для получения головы списка (первого элемента).
     *
     * @return Первый элемент списка.
     */
    public Node getHead() {
        return tail.getNext(); // Голова списка — это следующий элемент после tail
    }


    /**
     * Метод для получения хвоста списка (последнего элемента).
     *
     * @return Последний элемент списка.
     */
    public Node getTail() {
        return tail; // Возвращаем tail
    }


    /**
     * Метод для вставки элемента в список.
     * Элемент вставляется в отсортированную позицию.
     *
     * @param element Элемент для вставки.
     */
    public void insert(int element) {
        // Если список пуст, создаем новый узел и делаем его tail
        if (isEmpty()) {
            tail = new Node(element); // Создаем новый узел
            tail.setNext(tail); // Замыкаем список на себя
            return;
        }

        // Если элемент уже существует в списке, ничего не делаем
        if (findElement(element)) {
            return;
        }

        // Создаем новый узел для вставки
        Node newNode = new Node(element);

        // Если элемент меньше головы списка, он становится новой головой
        if (element < getHead().getData()) {
            newNode.setNext(getHead()); // Новый узел указывает на текущую голову
            tail.setNext(newNode); // Tail теперь указывает на новую голову
        }
        // Если элемент больше tail, он становится новым tail
        else if (element > tail.getData()) {
            newNode.setNext(getHead()); // Новый узел указывает на голову
            tail.setNext(newNode); // Текущий tail указывает на новый узел
            tail = newNode; // Новый узел становится tail
        }
        // Если элемент находится между head и tail, вставляем его в правильную позицию
        else {
            Node current = getHead(); // Начинаем с головы списка
            // Ищем позицию для вставки
            while (current.getNext() != getHead() && current.getNext().getData() < element) {
                current = current.getNext(); // Переходим к следующему узлу
            }
            // Вставляем новый узел после current
            newNode.setNext(current.getNext()); // Новый узел указывает на следующий узел current
            current.setNext(newNode); // Current теперь указывает на новый узел
        }
    }


    /**
     * Метод для удаления элемента из списка.
     *
     * @param element Элемент для удаления.
     */
    public void delete(int element) {
        // Если список пуст, выводим сообщение и завершаем выполнение
        if (isEmpty()) {
            System.out.println("Множество пустое");
            return;
        }

        // Если элемент не найден в списке, выводим сообщение и завершаем выполнение
        if (!findElement(element)) {
            System.out.println("Element not found");
            return;
        }

        // Случай, когда элемент — единственный в списке
        if (tail.getNext() == tail && tail.getData() == element) {
            tail = null; // Обнуляем tail, так как список теперь пуст
            return;
        }

        // Случай, когда элемент находится в голове списка
        if (getHead().getData() == element) {
            tail.setNext(getHead().getNext()); // Обновляем tail, чтобы он указывал на новый head
            return;
        }

        // Случай, когда элемент находится в tail
        if (tail.getData() == element) {
            Node current = getHead(); // Начинаем с головы списка
            // Ищем узел, предшествующий tail
            while (current.getNext() != tail) {
                current = current.getNext(); // Переходим к следующему узлу
            }
            current.setNext(getHead()); // Удаляем tail, связывая предыдущий узел с головой
            tail = current; // Обновляем tail на предыдущий узел
            return;
        }

        // Случай, когда элемент находится в середине списка
        Node current = getHead(); // Начинаем с головы списка
        // Ищем узел, предшествующий узлу с элементом
        while (current.getNext().getData() != element) {
            current = current.getNext(); // Переходим к следующему узлу
        }
        // Удаляем узел с элементом, связывая current с узлом после удаляемого
        current.setNext(current.getNext().getNext());
    }


    /**
     * Метод для печати списка.
     */
    public void printSet() {
        // Если список пуст, выводим сообщение и завершаем выполнение
        if (isEmpty()) {
            System.out.println("Множество пустое");
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
     * Метод для поиска элемента в списке.
     *
     * @param element Элемент, который нужно найти.
     * @return true, если элемент найден, false в противном случае.
     */
    public boolean findElement(int element) {
        // Если список пуст, элемент не найден
        if (isEmpty()) {
            return false;
        }

        Node current = getHead(); // Начинаем с головы списка
        do {
            // Если текущий узел содержит искомый элемент, возвращаем true
            if (current.getData() == element) {
                return true;
            }
            current = current.getNext(); // Переходим к следующему узлу
        } while (current != getHead()); // Продолжаем, пока не вернемся к голове

        return false; // Элемент не найден
    }
}