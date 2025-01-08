package CursorList;

import ListElement.ListElement;

public class List {
    private int head = -1; // Указатель на первый элемент списка
    private static int space = 0; // Указатель на первый свободный элемент
    private static final int SIZE = 10; // Размер массива памяти
    private static Node[] memoryPool; // Массив для хранения узлов списка

    static {
        // Инициализация массива узлов
        memoryPool = new Node[10];
        for (int i = 0; i < SIZE; i++) {
            memoryPool[i] = new Node(i + 1); // Каждый узел указывает на следующий
        }
        memoryPool[SIZE - 1] = new Node(0); // Последний узел указывает на начало
    }

    // Метод для получения индекса предыдущего элемента списка
    private int prev(int n) {
        int curr = head;
        int tmp = -1;

        // Перебираем элементы до нахождения текущего
        while (curr != -1) {
            if (n == curr)
                return tmp;

            tmp = curr;
            curr = memoryPool[curr].next;
        }
        return -1; // Если элемент не найден, возвращаем -1
    }

    // Метод для получения индекса предпоследнего элемента списка
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

    // Поиск элемента по значению
    private Position findElement(ListElement x) {
        int current = head;

        // Перебор всех элементов до нахождения совпадения
        while (current != -1) {
            if (memoryPool[current].data.equals(x)) return new Position(current);
            current = memoryPool[current].next;
        }
        return new Position(-1); // Возвращаем -1, если элемент не найден
    }

    // Вставка нового элемента в список
    public void Insert(ListElement x, Position p) {
        // Проверяем, есть ли свободное место в массиве
        if (space == -1) throw new RuntimeException("List is full");

        // Если позиция - конец списка или список пуст
        if (p.position == -1) {
            if (head == -1) { // Вставка в пустой список
                head = 0;
                memoryPool[head].data = new ListElement(x);
                memoryPool[head].next = -1;
                space++;
            } else {
                // Вставка в конец непустого списка
                int last = preEnd(); // Находим последний элемент
                int nextSpace = memoryPool[space].next; // Сохраняем ссылку на следующий свободный
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
        if (prev(p.position) == -1) return; // Если позиция не найдена, выходим

        int nextSpace = memoryPool[space].next;
        memoryPool[space].data = new ListElement(memoryPool[p.position].data); // Копируем данные текущей позиции
        memoryPool[space].next = -1;
        memoryPool[p.position].data = new ListElement(x); // Записываем новый элемент
        memoryPool[p.position].next = space; // Обновляем указатель следующего
        space = nextSpace; // Переносим указатель на свободное место
    }

    // Удаление элемента из списка
    public void Delete(Position p) {
        // Если список пуст, ничего не делаем
        if (head == -1) return;

        // Удаление первого элемента списка
        if (p.position == head) {
            head = memoryPool[p.position].next; // Устанавливаем голову на следующий элемент
            memoryPool[p.position].next = space; // Добавляем старую голову в свободные места
            space = p.position; // Обновляем указатель на свободное место
            return;
        }

        // Удаление элемента из середины или конца списка
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
