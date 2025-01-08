package Lists.CursorList;

/**
 * Класс позиции для списка на курсорах
 */
public class Position {
    public int position; // переменная позиции

    // Конструктор позиции
    public Position(int position) {
        this.position = position;
    }

    // Метод сравнения позиций
    public boolean Equals(Position p) {return this.position == p.position;}
}
