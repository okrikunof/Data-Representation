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


    public boolean member(int element) {
        if (cycledList.findElement(element)) {
            return true;
        }

        return false;
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
        boolean isAFinished = (currentA == null);
        boolean isBFinished = (currentB == null);

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
                int dataA = currentA.getData();
                int dataB = currentB.getData();

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

        // Проходим по обоим множествам одновременно
        while (!isAFinished && !isBFinished) {
            int dataA = currentA.getData();
            int dataB = currentB.getData();

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

        // Проходим по обоим множествам одновременно
        while (!isAFinished) {
            int dataA = currentA.getData();
            int dataB = currentB.getData();

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


    public int min() {
        if (cycledList.isEmpty()) {
            throw new RuntimeException("Set is empty");
        }

        return cycledList.getHead().getData();
    }


    public int max() {
        if (cycledList.isEmpty()) {
            throw new RuntimeException("Set is empty");
        }

        return cycledList.getTail().getData();
    }


    public void print() {
        cycledList.printSet();
    }


}
