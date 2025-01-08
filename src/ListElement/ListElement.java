package ListElement;

/** Класс, который представляет содержимое списка — открытку с именем и адрессом */
public class ListElement {
    private final char[] name;    // имя (до 20 символов)
    private final char[] address; // адрес (до 50 символов)

    /** Конструктор, инициализирующий открытку с именем и адресом */
    public ListElement(String addName, String addAddress) {
        this.name = new char[20];
        this.address = new char[50];

        /* Копируем строки в символьные массивы
         Если длина передаваемого элемента больше вместимости массива,
         то умещаем только то, что можем вместить.
         В конце строки добавляем символ переноса строки
         Если строка пустая, то символ будет первым элементом */

        // Копируем имя
        for (int i = 0; i < (20 - addName.length() < 0 ? 20 : addName.length()); i++) {
            this.name[i] = addName.charAt(i);
        }
        if (addName.length() < 20) {
            this.name[addName.length()] = '\0';
        }

        // Копируем адрес
        for (int i = 0; i < (50 - addAddress.length() < 0 ? 50 : addAddress.length()); i++) {
            this.address[i] = addAddress.charAt(i);
        }
        if (addAddress.length() < 50) {
            this.address[addAddress.length()] = '\0';
        }
    }

    // Копирующий конструктор для того, чтобы передавать в функции операций списка
    public ListElement(ListElement other) {
        this.name = new char[20];
        this.address = new char[50];

        // Копируем массив name
        int i;
        for (i = 0; i < 20; i++) {
            if (other.name[i] == '\0') {
                break;
            }
            this.name[i] = other.name[i];
        }
        // Явно добавляем символ конца строки, если место позволяет
        if (i < 20) {
            this.name[i] = '\0';
        }

        // Копируем массив address
        for (i = 0; i < 50; i++) {
            if (other.address[i] == '\0') break;
            this.address[i] = other.address[i];
        }
        // Явно добавляем символ конца строки, если место позволяет
        if (i < 50) {
            this.address[i] = '\0';
        }
    }

    // Метод для вывод содержимого на экран
    public void Print() {
        System.out.print("Name: ");
        for (int i = 0; i < 20; i++) {
            if (name[i] == '\0') break;
            System.out.print(name[i]);
        }

        System.out.print(" | Address: ");
        for (int i = 0; i < 50; i++) {
            if (address[i]== '\0') break;
            System.out.print(address[i]);
        }
        System.out.println();
    }

    public boolean Equals(ListElement compareElement) {
        /* Сравнение полей name и address
        В начале проверяем равенство объектов
        Пробегаем по массиву чаров и сравниваем посимвольно, предварительно проверив размерность */
        if (compareElement == this) return true;

        // Сравниваем name по длине и содержимому
        for (int i = 0; i < 20; i++) {
            if (this.name[i] != compareElement.name[i]) {
                return false;
            }
            // Если достигнут конец строки '\0' у обоих
            if (this.name[i] == '\0' && compareElement.name[i] == '\0') {
                break; // завершить цикл, если обе строки закончились
            }
        }

        // Сравниваем address по длине и содержимому
        for (int i = 0; i < 50; i++) {
            if (this.address[i] != compareElement.address[i]) {
                return false;
            }
            // Если достигнут конец строки '\0' у обоих
            if (this.address[i] == '\0' && compareElement.address[i] == '\0') {
                break; // завершить цикл, если обе строки закончились
            }
        }

        return true;
    }
}