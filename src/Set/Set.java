package Set;

public class Set {
    private CycledList cycledList;


    public Set() {
        cycledList = new CycledList();
    }


    public Set(Set set) {
        cycledList = new CycledList(set.cycledList);
    }


    public void insert(int element) {
        cycledList.insert(element);
    }


    public void delete(int element) {
        cycledList.delete(element);
    }


    /**
     * Метод для объединения двух множеств
     *
     * @param a первое множество
     * @param b второе множество
     * @return новое множество, содержащее все уникальные элементы из a и b
     */
    public static Set union(Set a, Set b) {

        Set union = new Set(); // Создаем пустое множество для результата
        // Если оба множества пустые, возвращаем пустое множество
        if (a.cycledList.isEmpty() && b.cycledList.isEmpty()) {
            return union;
        }

        Set aCopy = new Set(a);
        Set bCopy = new Set(b);

        // Указатели для прохода по множествам
        Node currentA = aCopy.cycledList.getHead();
        Node currentB = bCopy.cycledList.getHead();

        // Флаги для отслеживания завершения прохода по множествам
        boolean isAFinished = false;
        boolean isBFinished = false;
        int dataA;
        int dataB;

        // Проходим по обоим множествам одновременно
        while (!isAFinished || !isBFinished) {
            if (isAFinished) {
                // Если множество A закончилось, добавляем оставшиеся элементы из B
                union.insert(currentB.getData());
                currentB = currentB.getNext();
                if (currentB == bCopy.cycledList.getHead()) {
                    isBFinished = true;
                }
            } else if (isBFinished) {
                // Если множество B закончилось, добавляем оставшиеся элементы из A
                union.insert(currentA.getData());
                currentA = currentA.getNext();
                if (currentA == aCopy.cycledList.getHead()) {
                    isAFinished = true;
                }
            } else {
                // Сравниваем текущие элементы из A и B
                dataA = currentA.getData();
                dataB = currentB.getData();

                if (dataA < dataB) {
                    // Добавляем элемент из A
                    union.insert(dataA);
                    currentA = currentA.getNext();
                    if (currentA == aCopy.cycledList.getHead()) {
                        isAFinished = true;
                    }
                } else if (dataA > dataB) {
                    // Добавляем элемент из B
                    union.insert(dataB);
                    currentB = currentB.getNext();
                    if (currentB == bCopy.cycledList.getHead()) {
                        isBFinished = true;
                    }
                } else {
                    // Если элементы равны, добавляем один из них (чтобы избежать дубликатов)
                    union.insert(dataA);
                    currentA = currentA.getNext();
                    currentB = currentB.getNext();
                    if (currentA == aCopy.cycledList.getHead()) {
                        isAFinished = true;
                    }
                    if (currentB == bCopy.cycledList.getHead()) {
                        isBFinished = true;
                    }
                }
            }
        }

        System.out.print("A: ");
        aCopy.print();
        System.out.print("B: ");
        bCopy.print();
        System.out.print("A ∪ B : ");
        union.print();

        return union;
    }


    public static Set intersection(Set a, Set b) {
        Set intersection = new Set(); // Создаем пустое множество для результата
        // Если одно из множеств пустое, возвращаем пустое множество
        if (a.cycledList.isEmpty() || b.cycledList.isEmpty()) {
            return intersection;
        }

        Set aCopy = new Set(a);
        Set bCopy = new Set(b);

        // Указатели для прохода по множествам
        Node currentA = aCopy.cycledList.getHead();
        Node currentB = bCopy.cycledList.getHead();

        // Флаги для отслеживания завершения прохода по множествам
        boolean isAFinished = false;
        boolean isBFinished = false;
        int dataA;
        int dataB;

        // Проходим по обоим множествам одновременно
        while (!isAFinished && !isBFinished) {
            dataA = currentA.getData();
            dataB = currentB.getData();

            if (dataA < dataB) {
                // Если элемент из A меньше, перемещаем указатель A вперед
                currentA = currentA.getNext();
                if (currentA == aCopy.cycledList.getHead()) {
                    isAFinished = true;
                }
            } else if (dataA > dataB) {
                // Если элемент из B меньше, перемещаем указатель B вперед
                currentB = currentB.getNext();
                if (currentB == bCopy.cycledList.getHead()) {
                    isBFinished = true;
                }
            } else {
                // Если элементы равны, добавляем их в результат
                intersection.insert(dataA);
                currentA = currentA.getNext();
                currentB = currentB.getNext();
                if (currentA == aCopy.cycledList.getHead()) {
                    isAFinished = true;
                }
                if (currentB == bCopy.cycledList.getHead()) {
                    isBFinished = true;
                }
            }
        }

        System.out.print("A: ");
        aCopy.print();
        System.out.print("B: ");
        bCopy.print();
        System.out.print("A ∩ B : ");
        intersection.print();

        return intersection;
    }


    public static Set difference(Set a, Set b) {
        Set difference = new Set(); // Создаем пустое множество для результата
        // Если множество a пустое, возвращаем пустое множество
        if (a.cycledList.isEmpty()) {
            return difference;
        }

        // Если множество b пустое, возвращаем копию множества a
        if (b.cycledList.isEmpty()) {
            return new Set(a);
        }

        Set aCopy = new Set(a);
        Set bCopy = new Set(b);

        // Указатели для прохода по множествам
        Node currentA = aCopy.cycledList.getHead();
        Node currentB = bCopy.cycledList.getHead();

        // Флаги для отслеживания завершения прохода по множествам
        boolean isAFinished = false;
        boolean isBFinished = false;
        int dataA;
        int dataB;

        // Проходим по обоим множествам одновременно
        while (!isAFinished) {
            dataA = currentA.getData();
            dataB = currentB.getData();

            if (dataA < dataB) {
                // Если элемент из A меньше, добавляем его в результат
                difference.insert(dataA);
                currentA = currentA.getNext();
                if (currentA == aCopy.cycledList.getHead()) {
                    isAFinished = true;
                }
            } else if (dataA > dataB) {
                // Если элемент из B меньше, перемещаем указатель B вперед
                currentB = currentB.getNext();
                if (currentB == bCopy.cycledList.getHead()) {
                    isBFinished = true;
                }
            } else {
                // Если элементы равны, пропускаем их (они не входят в разность)
                currentA = currentA.getNext();
                currentB = currentB.getNext();
                if (currentA == aCopy.cycledList.getHead()) {
                    isAFinished = true;
                }
                if (currentB == bCopy.cycledList.getHead()) {
                    isBFinished = true;
                }
            }

            // Если множество B закончилось, добавляем оставшиеся элементы из A
            if (isBFinished && !isAFinished) {
                difference.insert(dataA);
                currentA = currentA.getNext();
                if (currentA == aCopy.cycledList.getHead()) {
                    isAFinished = true;
                }
            }
        }

        System.out.print("A: ");
        aCopy.print();
        System.out.print("B: ");
        bCopy.print();
        System.out.print("A \\ B : ");
        difference.print();

        return difference;
    }


    /**
     * Метод для проверки равенства двух множеств
     *
     * @param A первое множество
     * @param B второе множество
     * @return true, если множества равны, иначе false
     */
    public static boolean equal(Set A, Set B) {
        // Если оба множества пустые, они равны
        if (A.cycledList.isEmpty() && B.cycledList.isEmpty()) {
            return true;
        }

        // Если одно из множеств пустое, а другое нет, они не равны
        if (A.cycledList.isEmpty() || B.cycledList.isEmpty()) {
            return false;
        }

        // Указатели для прохода по множествам
        Node currentA = A.cycledList.getHead();
        Node currentB = B.cycledList.getHead();

        // Проходим по обоим множествам одновременно
        do {
            // Если элементы не равны, множества не равны
            if (currentA.getData() != currentB.getData()) {
                return false;
            }

            // Переходим к следующим элементам
            currentA = currentA.getNext();
            currentB = currentB.getNext();
        } while (currentA != A.cycledList.getHead() && currentB != B.cycledList.getHead());

        // Если мы дошли до конца обоих множеств, они равны
        return currentA == A.cycledList.getHead() && currentB == B.cycledList.getHead();
    }


    /**
     * Метод для присваивания множества b множеству a
     *
     * @param a множество, в которое будет записано значение
     * @param b множество, значение которого будет скопировано
     */
    public static void assign(Set a, Set b) {
        Set bCopy = new Set(b);

        // Очищаем множество a, если оно не пустое
        while (!a.cycledList.isEmpty()) {
            a.delete(a.cycledList.getHead().getData()); // Удаляем элементы по одному
        }

        // Если множество b пустое, то a останется пустым
        if (bCopy.cycledList.isEmpty()) {
            return;
        }

        // Копируем элементы из b в a
        Node current = bCopy.cycledList.getHead();
        do {
            a.insert(current.getData()); // Вставляем каждый элемент из b в a
            current = current.getNext();
        } while (current != bCopy.cycledList.getHead()); // Продолжаем, пока не пройдем весь список
    }


    /**
     * Метод для объединения двух множеств A и B в множество C.
     * Результат будет не определен, если A и B имеют общие элементы.
     *
     * @param A первое множество
     * @param B второе множество
     * @throws RuntimeException если множества A и B пересекаются
     */
    public static Set merge(Set A, Set B) {
        // Проверяем, пересекаются ли множества A и B
        Set intersection = intersection(A, B);
        if (!intersection.cycledList.isEmpty()) {
            throw new RuntimeException("Множества A и B пересекаются. Операция MERGE не может быть выполнена.");
        }

        // Если множества не пересекаются, выполняем объединение
        Set unionResult = union(A, B);
        return unionResult;
    }


    public Set makeNull(Set set) {
        Set emptySet = new Set();
    }

    public boolean find(int x) {
        if (this.cycledList.findElement(x)) {
            return true;
        }

        return false;
    }

    public static boolean member(Set set, int element) {
        if (set.cycledList.findElement(element)) {
            return true;
        }

        return false;
    }


    public int min() {
        if (cycledList.isEmpty()) {
            throw new RuntimeException("Множество пустое");
        }

        return cycledList.getHead().getData();
    }


    public int max() {
        if (cycledList.isEmpty()) {
            throw new RuntimeException("Множество пустое");
        }

        return cycledList.getTail().getData();
    }


    public void print() {
        cycledList.printSet();
    }
}
