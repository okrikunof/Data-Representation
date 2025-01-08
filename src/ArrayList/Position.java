package ArrayList;

/**
 * Класс Position, который отвечает за позицию в списке на массиве
 */
public class Position {
    public int position;  // Переменная, содержащая позицию

    // Конструктор позиции
    public Position(int x) {
        this.position = x;
    }

    //Метод для проверки равенства позиций
    public boolean Equals(Position p) {
        return this.position == p.position;
    }
}
