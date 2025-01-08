package DoubleLinkedList;

import ListElement.ListElement;

public class List {
    private Node head;
    private Node tail;

    // Конструктор для инициализации пустого списка
    public List(){
        head = null;
        tail = null;
    }

    // Проверяет, находится ли заданная позиция в списке
    private boolean inList(Position p){
        Node tmp = head;

        // Перебираем все элементы списка от начала до конца
        while(tmp != null){
            if (tmp.equals(p.node)) return true; // Если нашли совпадение, возвращаем true
            tmp = tmp.next;
        }
        return false; // Если позиция не найдена, возвращаем false
    }

    // Вспомогательный метод для поиска узла по значению
    private Node findNode(ListElement listElement){
        Node curr = head;
        while(curr!=null){
            if(curr.data.equals(listElement))
                return curr; // Возвращаем узел, если он найден
            curr=curr.next;
        }
        return null; // Если элемент не найден, возвращаем null
    }

    // Вставляет новый элемент в заданную позицию
    public void Insert(ListElement x, Position p) {

        // Если список пустой или содержит только один элемент
        if(head == tail){
            if(p.node == null){ // Добавление в конец пустого списка
                if(head == null){ // Если список полностью пустой
                    head = new Node(x);
                    tail = head;
                    return;
                }
                // Если в списке есть один элемент, добавляем новый в конец
                tail = new Node(x);
                tail.previous = head;
                head.next = tail;
                return;
            }
            // Вставка нового элемента на место существующего в списке с одним элементом
            head.next = new Node(head.data);
            head.data = new ListElement(x);
            tail = head.next;
            tail.previous = head;
            return;
        }

        // Вставка нового элемента перед первым в списке
        if(p.node == head){
            Node curr = p.node;
            Node nodeX = new Node(x);
            curr.previous = nodeX;
            nodeX.next = curr;
            head = nodeX; // Новый элемент становится головой списка
            return;
        }

        // Вставка нового элемента перед последним в списке
        if(p.node == tail){
            Node currPrev = tail.previous;
            currPrev.next = new Node(x); // Новый узел вставляется между предпоследним и последним
            currPrev.next.previous = currPrev;
            currPrev.next = tail;
            tail.previous = currPrev.next;
            return;
        }

        // Добавление нового элемента в конец списка
        if(p.node == null){
            Node tmp = new Node(x);
            tail.next = tmp;
            tmp.previous = tail;
            tail = tmp; // Новый элемент становится хвостом списка
            return;
        }

        // Вставка нового элемента в середину списка
        Node tmp;
        if(inList(p)) tmp = p.node;
        else return;

        Node next = tmp.next;
        next.next = new Node(x); // Связываем новый узел с последующими
        next.next.previous = next;
        next.next = tmp;
        tmp.previous = tmp.next; // Обновляем связи предыдущего элемента
    }

    // Удаляет элемент из списка в указанной позиции
    public void Delete(Position p) {

        // Если позиция не находится в списке, выбрасываем исключение
        if(!inList(p)){
            throw new RuntimeException("Invalid position");
        }
        // Если список пуст или позиция некорректная
        if(head==null || tail==null || p.node==null){
            throw new RuntimeException("List is empty");
        }

        // Удаление единственного элемента в списке
        if (tail==head && p.node==head){
            head=null;
            tail=null;
        }

        // Удаление первого элемента в списке
        if(p.node==head){
            head=head.next;
            head.previous = null;
            p.node = head; // Обновляем указатель позиции
            return;
        }

        // Удаление последнего элемента в списке
        if(p.node == tail){
            tail = tail.previous;
            tail.next = null;
            p.node = null; // Устанавливаем текущий узел как null
            return;
        }

        // Удаление элемента из середины списка
        if(inList(p)){
            Node tmpNext = p.node.next; // Узел, следующий за удаляемым
            Node tmpPrev = p.node.previous; // Узел, предшествующий удаляемому

            tmpPrev.next = p.node.next; // Переподключаем соседей
            tmpNext.previous = tmpPrev;
            p.node = tmpPrev.next; // Перемещаем позицию на следующий узел
        }

    }

    // Находит позицию элемента с заданным значением
    public Position Locate(ListElement x) {
        if(x == null)
            throw new RuntimeException("Invalid position");
        return new Position(findNode(x)); // Возвращаем позицию найденного элемента
    }

    // Возвращает элемент по позиции
    public ListElement Retrieve(Position p) {
        if(p==null || p.equals(End()))
            throw new RuntimeException("Invalid position");

        return p.node.data; // Возвращаем данные из узла
    }

    // Возвращает позицию предыдущего элемента
    public Position Previous(Position p){
        if(p.node == null)
            throw new RuntimeException("Invalid position");
        if(p.node == head)
            throw new RuntimeException("Invalid position");
        else{
            if(p.node == tail)
                return new Position(tail.previous);
            return new Position(p.node.previous);
        }
    }

    // Возвращает позицию следующего элемента
    public Position Next(Position p){
        if(p.node == null) {
            // Если текущий узел отсутствует, выбрасываем исключение
            throw new RuntimeException("Invalid position");
        }

        if (p.node == tail)
            // Если текущий узел последний, возвращаем пустую позицию
            return new Position(null);

        if(p.node == head){
            return new Position(head.next);
        }

        if (!inList(p)) throw new RuntimeException("Invalid position");
        return new Position(p.node.next);
    }

    // Возвращает позицию за последним элементом
    public Position End(){
        return new Position(null);
    }

    // Возвращает позицию первого элемента
    public Position First() {
        return new Position(head);
    }

    // Очищает список и возвращает пустую позицию
    public Position MakeNull(){
        head = null;
        tail = null;
        return new Position(null);
    }

    // Печатает все элементы списка
    public void PrintList() {
        if (head == null || tail == null)
            System.out.println("List is empty");
        else{
            Node curr = head;
            int i = 0;
            while(curr != null){
                i++; // Счетчик для нумерации элементов (можно использовать по необходимости)
                curr.data.Print();
                curr = curr.next; // Переходим к следующему узлу
            }
        }
    }
}
