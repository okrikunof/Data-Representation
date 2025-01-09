package Lists.CursorList;

import ListElement.ListElement;

/**
 * Класс Lists.CursorList реализует список с использованием массива в качестве памяти для узлов.
 * Каждый узел содержит данные и указатель на следующий элемент списка.
 */
public class List {
    private int head = -1; // Указатель на первый элемент списка
    private static int space = 0; // Указатель на первый свободный элемент
    private static final int SIZE = 10; // Размер массива памяти
    private static Node[] memoryPool; // Массив для хранения узлов списка

    // Статический блок инициализации для настройки памяти
    static {
        memoryPool = new Node[10]; // Инициализация массива узлов
        for (int i = 0; i < SIZE; i++) {
            memoryPool[i] = new Node(i + 1); // Каждый узел указывает на следующий
        }
        memoryPool[SIZE - 1] = new Node(0); // Последний узел указывает на начало
    }

    /**
     * Метод prev находит индекс предыдущего узла в списке.
     *
     * @param n индекс узла, для которого нужно найти предыдущий
     * @return индекс предыдущего узла или -1, если узел не найден
     */
    private int prev(int n) {
        int curr = head;
        int tmp = -1;

        // Перебираем элементы до нахождения текущего
        while (curr != -1) {
            if (n == curr) {
                return tmp; // Возвращаем индекс предыдущего узла
            }
            tmp = curr;
            curr = memoryPool[curr].next;
        }
        return -1; // Если элемент не найден
    }

    /**
     * Метод preEnd находит индекс предпоследнего узла в списке.
     *
     * @return индекс предпоследнего узла или -1, если список пуст
     */
    private int preEnd() {
        int curr = head;
        int tmp = -1;

        // Итерируем список до его конца
        while (curr != -1) {
            tmp = curr;
            curr = memoryPool[curr].next;
        }

        return tmp; // Возвращаем индекс предпоследнего элемента
    }

    /**
     * Метод findElement ищет позицию узла с заданным значением.
     *
     * @param x элемент для поиска
     * @return позиция узла или -1, если элемент не найден
     */
    private Position findElement(ListElement x) {
        int current = head;

        // Перебор всех элементов до нахождения совпадения
        while (current != -1) {
            if (memoryPool[current].data.equals(x)) {
                return new Position(current); // Возвращаем найденную позицию
            }
            current = memoryPool[current].next;
        }
        return new Position(-1); // Возвращаем -1, если элемент не найден
    }

    /**
     * Метод Insert добавляет новый элемент в список.
     *
     * @param x элемент для вставки
     * @param p позиция, перед которой нужно вставить новый элемент
     */
    public void Insert(ListElement x, Position p) {
        // Проверяем наличие свободного места
        if (space == -1) {
            throw new RuntimeException("List is full");
        }

        // Вставка в конец списка или в пустой список
        if (p.position == -1) {
            if (head == -1) { // Если список пустой
                head = 0;
                memoryPool[head].data = new ListElement(x);
                memoryPool[head].next = -1;
                space++;
            } else {
                int last = preEnd(); // Находим последний элемент
                int nextSpace = memoryPool[space].next; // Сохраняем следующий свободный
                memoryPool[space].data = new ListElement(x);
                memoryPool[space].next = -1;
                memoryPool[last].next = space; // Обновляем указатель последнего
                space = nextSpace; // Обновляем указатель на свободное место
            }
            return;
        }

        // Вставка элемента в начало списка
        if (p.position == head) {
            int nextSpace = memoryPool[space].next;
            memoryPool[space].data = new ListElement(x); // Копируем данные в новое место
            memoryPool[space].next = head; // Новый элемент указывает на старую голову
            head = space; // Обновляем указатель головы
            space = nextSpace;
            return;
        }

        // Вставка элемента в середину списка
        if (prev(p.position) == -1) {
            return; // Если позиция не найдена, выходим
        }

        int nextSpace = memoryPool[space].next;
        memoryPool[space].data = new ListElement(memoryPool[p.position].data); // Копируем данные текущей позиции
        memoryPool[space].next = -1;
        memoryPool[p.position].data = new ListElement(x); // Записываем новый элемент
        memoryPool[p.position].next = space; // Обновляем указатель следующего
        space = nextSpace; // Переносим указатель на свободное место
    }

    /**
     * Метод Delete удаляет элемент из списка.
     *
     * @param p позиция элемента для удаления
     */
    public void Delete(Position p) {
        if (head == -1) {
            return; // Если список пуст, ничего не делаем
        }

        // Удаление первого элемента
        if (p.position == head) {
            head = memoryPool[p.position].next; // Устанавливаем голову на следующий элемент
            memoryPool[p.position].next = space; // Возвращаем старую голову в свободные места
            space = p.position; // Обновляем указатель на свободное место
            return;
        }

        // Удаление элемента из середины или конца
        int prev = prev(p.position); // Находим предыдущий элемент
        if (prev != -1) {
            int current = memoryPool[prev].next;
            memoryPool[prev].next = memoryPool[current].next; // Обновляем указатель предыдущего
            memoryPool[current].next = space; // Возвращаем текущий в свободные места
            space = current; // Обновляем указатель свободного
            p.position = memoryPool[prev].next; // Сдвигаем позицию
        }
    }

    // Возвращает предыдущую позицию относительно заданной
    public Position Previous(Position p) {
        // Проверяем корректность переданной позиции
        if (p.position == -1 || p.position == head || p.position > memoryPool.length)
            throw new RuntimeException("Invalid position");
        int tmp = prev(p.position); // Получаем индекс предыдущего
        if (tmp == -1) throw new RuntimeException("Invalid position");
        return new Position(tmp);
    }

    // Возвращает элемент, находящийся в заданной позиции
    public ListElement Retrieve(Position p) {
        if (p.position > memoryPool.length) throw new RuntimeException("Invalid position");
        if (prev(p.position) != -1 || p.position == head) {
            return memoryPool[p.position].data; // Возвращаем данные элемента
        } else {
            throw new RuntimeException("Invalid position");
        }
    }

    // Возвращает следующую позицию относительно заданной
    public Position Next(Position p) {
        if (p.position == head) return new Position(memoryPool[head].next);

        int tmpPrev = prev(p.position);
        if (tmpPrev == -1) throw new RuntimeException("Invalid position");
        tmpPrev = memoryPool[tmpPrev].next;
        if (tmpPrev == -1) throw new RuntimeException("Invalid position");

        return new Position(memoryPool[tmpPrev].next);
    }

    // Поиск позиции элемента по значению
    public Position locate(ListElement x) {
        if (head == -1 || x == null) return null; // Если список пустой или элемент равен null
        return findElement(x); // Возвращаем позицию найденного элемента
    }

    // Возвращает позицию за последним элементом
    public Position End() {
        return new Position(-1);
    }

    // Возвращает позицию первого элемента
    public Position First() {
        return new Position(head);
    }

    // Очищает список
    public void MakeNull() {
        head = -1; // Устанавливаем голову на -1, что означает пустой список
    }

    // Печатает все элементы списка
    public void PrintList() {
        if (head == -1) { // Если список пуст, выводим сообщение
            System.out.println("List is empty");
            return;
        }
        int temp = head;
        int i = 0;
        // Итерируем список, пока не достигнем его конца
        while (temp != -1) {
            i++;
            memoryPool[temp].data.Print(); // Вызываем метод печати данных
            temp = memoryPool[temp].next; // Переходим к следующему узлу
        }
    }
}
