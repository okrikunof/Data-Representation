package Lists.CursorList;

import ListElement.ListElement;

/**
 * Класс Lists.CursorList реализует список с использованием массива в качестве памяти для узлов.
 * Каждый узел содержит данные и указатель на следующий элемент списка.
 */
public class List {
    private int head; // Указатель на начало списка,  -1 (пустой список)
    private static int space = 0; // Указатель на первый свободный элемент в массиве
    private static final int LENGTH = 10; // Длина массива, которая будет использоваться для хранения элементов
    private static Node[] array; // Массив узлов

    public List() {
        head=-1;
    }
    static{
        array = new Node[10]; // Инициализация массива узлов длиной 10
        for (int i = 0; i < LENGTH; i++) {
            array[i] = new Node(i + 1); // Заполнение массива блоками с индексами
        }
        array[LENGTH - 1] = new Node(0); // Установка последнего элемента как пустого
    }

    // Метод для получения предыдущего элемента в списке
    private int getPrevious(int n) {
        int curr = head; // Начинаем с головы списка
        int tmp = -1; // Временная переменная для хранения предыдущего элемента

        while (curr != -1) {
            if (n == curr)
                return tmp;

            tmp = curr;
            curr = array[curr].next;
        }
        return -1;
    }

    // Метод для получения последнего элемента списка
    private int getPreEnd() {
        int curr = head; // Начинаем с головы списка
        int tmp = -1; // Временная переменная для хранения последнего элемента

        while (curr != -1) {
            tmp = curr;
            curr = array[curr].next;
        }

        return tmp;
    }

    public void Insert(ListElement x, Position p) {

        if (space == -1) throw new RuntimeException();

        if (p.position == -1) { // Если вставляем в конец списка
            if (head == -1) { // Если список пуст
                head = 0; // Устанавливаем голову на первый элемент
                array[head].data = new ListElement(x);
                array[head].next = -1; // Указываем на конец списка
                space++; // Увеличиваем количество использованных элементов
            } else { // Если список не пуст
                int last = getPreEnd(); // Получаем последний элемент
                int nextSpace = array[space].next; // Получаем следующий свободный элемент
                array[space].data = new ListElement(x); // Копируем объект в массив
                array[space].next = -1; // Указываем на конец списка
                array[last].next = space; // Соединяем последний элемент с новым элементом
                space = nextSpace; // Обновляем указатель на свободный элемент
            }
            return;
        }

        if (p.position == head) { // Если вставляем в начало списка
            int nextSpace = array[space].next; // Получаем следующий свободный элемент
            array[space].data = new ListElement(x); // Копируем объект в массив
            array[space].next = head; // Соединяем с головой списка
            head = space; // Обновляем голову
            space = nextSpace; // Обновляем указатель на свободный элемент
            return; // Завершаем метод
        }

        // Если вставляем не в начало
        if (getPrevious(p.position) == -1) return; // Проверяем существование предыдущего элемента

        int nextSpace = array[space].next; // Получаем следующий свободный элемент
        array[space].data = new ListElement(array[p.position].data); // Копируем данные из старого элемента
        array[space].next = -1; // Указываем на конец списка
        array[p.position].data = new ListElement(x); // Копируем новый объект
        array[p.position].next = space; // Соединяем новый элемент с предыдущим
        space = nextSpace; // Обновляем указатель на свободный элемент
    }

    // Метод для удаления элемента из списка
    public void Delete(Position p) {
        if (head == -1) return; // Если список пуст, ничего не делаем

        if (p.position == head) { // Если удаляем элемент из начала списка
            head = array[p.position].next; // Обновляем голову списка
            array[p.position].next = space; // Освобождаем пространство
            space = p.position; // Обновляем указатель на свободный элемент
        }

        // Удаление элемента не из головы
        int prev = getPrevious(p.position); // Получаем предыдущий элемент
        if (prev != -1) { // Если предыдущий элемент существует
            int current = array[prev].next; // Получаем текущий элемент
            array[prev].next = array[current].next; // Соединяем предыдущий с следующим
            array[current].next = space; // Освобождаем пространство
            space = current; // Обновляем указатель на свободный элемент
            p.position = array[prev].next; // Обновляем позицию
        }
    }

    // Метод для получения предыдущей позиции
    public Position Previous(Position p) {
        if (p.position == -1 || p.position == head || p.position > array.length)
            throw new RuntimeException();

        int tmp = getPrevious(p.position); // Получаем предыдущую позицию
        if (tmp == -1)
            throw new RuntimeException();

        return new Position(tmp); // Возвращаем предыдущую позицию
    }

    // Метод для получения элемента по позиции
    public ListElement Retrieve(Position p) {
        if (p.position > array.length)
            throw new RuntimeException();

        if (getPrevious(p.position) != -1 || p.position == head) {
            return array[p.position].data; // Возвращаем объект по позиции
        } else {
            throw new RuntimeException(); // Если позиции нет
        }
    }

    // Метод для получения следующей позиции
    public Position Next(Position p) {
        if (p.position == head)
            return new Position(array[head].next); // Если текущая позиция - голова

        int tmpPrev = getPrevious(p.position); // Получаем предыдущую позицию
        if (tmpPrev == -1)
            throw new RuntimeException(); // Если предыдущей позиции нет

        tmpPrev = array[tmpPrev].next; // Получаем следующую позицию
        if (tmpPrev == -1)
            throw new RuntimeException(); // Если следующей позиции нет

        return new Position(array[tmpPrev].next); // Возвращаем следующую позицию
    }

    // Метод для поиска позиции элемента
    public Position Locate(ListElement x) {
        if (head == -1 || x == null)
            return null; // Если список пуст или объект null, возвращаем null
        int current = head; // Начинаем с головы списка

        while (current != -1) { // Пока не достигнут конец списка
            if (array[current].data.equals(x)) // Если найден элемент
                return new Position(current); // Возвращаем позицию найденного элемента
            current = array[current].next; // Переходим к следующему элементу
        }
        return new Position(-1); // Если элемент не найден, возвращаем -1
    }

    // Метод для получения конца списка
    public Position End() {
        return new Position(-1); // Возвращаем -1, что обозначает конец
    }

    // Метод для получения первого элемента списка
    public Position First() {
        return new Position(head); // Возвращаем голову списка
    }

    // Метод для обнуления списка
    public void MakeNull() {
        head = -1; // Устанавливаем голову на -1 (пустой список)
    }

    // Метод для печати элементов списка
    public void PrintList() {
        if (head == -1) {
            System.out.println("Список пуст"); // Если список пуст
            return; // Завершаем метод
        }
        int temp = head; // Начинаем с головы

        while (temp != -1) { // Пока не достигнут конец списка
            array[temp].data.Print();
            temp = array[temp].next; // Переходим к следующему элементу
        }
    }
}
