package PriorityQueue;

public class PriorityQueue {
    private int[] priorityQueue;
    private int size;
    private final int CAPACITY = 100;

    public PriorityQueue() {
        this.priorityQueue = new int[CAPACITY + 1];
        this.size = 0; // Индексация начинается с 1
    }

    private void changePositions(int idx1, int idx2) {
        int temp = priorityQueue[idx1];
        priorityQueue[idx1] = priorityQueue[idx2];
        priorityQueue[idx2] = temp;
    }

    public void Insert(int value) {
        size++;  // Увеличиваем размер кучи
        priorityQueue[size] = value;  // Добавляем элемент в конец

        // Поднимаем элемент вверх
        int i = size;
        while (i > 1 && priorityQueue[i / 2] > priorityQueue[i]) {
            changePositions(i, i / 2);  // Меняем местами с родителем
            i = i / 2;  // Переходим к родителю
        }
    }

    public int DeleteMin() {
        if (size == 0) {
            throw new RuntimeException("Priority Queue is empty");
        }

        int root = priorityQueue[1];
        priorityQueue[1] = priorityQueue[size];
        size--;

        int i = 1;
        while (true) {
            int left = 2 * i;
            int right = 2 * i + 1;
            int minPriority = i;

            if (left <= size && priorityQueue[left] <= priorityQueue[minPriority]) {
                minPriority = left;
            }
            if (right <= size && priorityQueue[right] <= priorityQueue[minPriority]) {
                minPriority = right;
            }

            if (minPriority != i) {
                changePositions(i, minPriority);
                i = minPriority;
            } else {
                break;
            }
        }

        return root;
    }

    public void MakeNull() {
        size = 0;
    }

    public void Print() {
        for (int i = 1; i <= size; i++) {
            System.out.print(priorityQueue[i] + " ");
        }
        System.out.println();
    }
}
