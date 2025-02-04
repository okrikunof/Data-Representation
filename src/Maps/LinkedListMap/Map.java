package Maps.LinkedListMap;

import ListElement.*;

/**
 * Класс Map представляет собой отображение, реализованное с помощью связного списка.
 * Каждый элемент отображения представляет собой пару "ключ-значение", где ключом является имя, а значением адрес.
 */
public class Map {
    private Node head; // Указатель на первый узел в списке, представляющем карту

    /**
     * Конструктор Map.
     * Инициализирует пустую карту, устанавливая head в null.
     */
    public Map() {
        this.head = null;
    }

    private static boolean compareCharArrays(char[] value1, char[] value2) {
        int len = Math.min(value1.length, value2.length);
        for (int i = 0; i < len; i++) {
            if (value1[i] == '0' || value2[i] == '0') continue;
            if (value1[i] != value2[i]) return false;
        }
        return true;
    }

    /**
     * Метод для поиска узла по ключу.
     * Перебирает все элементы карты, чтобы найти узел с указанным ключом.
     *
     * @param d элемент, ключ которого нужно найти в карте
     * @return найденный узел или null, если ключ не найден
     */
    private Node findViaKey(char[] d) {
        Node key = head;
        while (key != null) {
            if (compareCharArrays(key.data.GetName(), d)) {
                return key;
            }
            key = key.next;
        }
        return null;
    }

    /**
     * Метод для очистки карты.
     * Устанавливает head в null, очищая все элементы карты.
     */
    public void MakeNull() {
        this.head = null; // Очищаем отображение, устанавливая голову в null
    }

    /**
     * Метод для добавления или обновления элемента в карте.
     * Если ключ уже существует, обновляет значение, иначе вставляет новый элемент в начало списка.
     */
    public void Assign(char[] d, char[] r) {
        //Если список пустой, создаем голову, нет смысла искать ключ
        if (head == null) {
            head = new Node(d, r, null);
            return;
        }

        //Поиск по ключу. Если найден, вставить значение
        Node node = findViaKey(d);
        if (node != null) {
            node.SetAddress(r);
            return;
        }

        //Если ключ не найдет, то вставляем сразу после головы, чтобы было быстро
        if (head.next == null) {
            head.next = new Node(d, r, null);
            return;
        }

        head.next = new Node(d, r, head.next);
    }

    /**
     * Метод для вычисления значения по ключу.
     * Если элемент с заданным ключом существует, обновляет его значение и возвращает true.
     * Если элемент не найден, возвращает false.
     */
    public boolean Compute(char[] d, char[] r) {
        Node node = findViaKey(d);
        if (node == null)
            return false;
        node.SetAddress(r);
        return true;
    }

    /**
     * Метод для печати всех элементов карты.
     * Перебирает все узлы и вызывает метод Print для каждого элемента.
     */
    public void Print() {
        Node next = head; // Начинаем с головы списка
        while (next != null) {
            next.data.Print(); // Печатаем данные текущего узла
            next = next.next; // Переходим к следующему узлу
        }

        System.out.println(); // Печатаем новую строку после вывода всех элементов
    }
}
