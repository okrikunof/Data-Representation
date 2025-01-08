package LinkedList;

/** Класс позиции для связного списка */
public class Position {
    public Node node;  // узел расположенный в позиции

    // Конструктор для позиции
    public Position(Node node) {
        this.node = node;
    }

    // Метод для сравнения позиций на равенство
    public boolean Equals(Position p) {
        return this.node == p.node;
    }
}
