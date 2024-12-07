import java.util.ArrayList;       // Импортируем класс ArrayList для хранения списка задач
import java.util.List;            // Импортируем интерфейс List для работы со списками
import java.util.Scanner;         // Импортируем класс Scanner для чтения пользовательского ввода

public class Checklist {
    // Статический список для хранения задач
    private static List<String> tasks = new ArrayList<>();

    // Статический объект Scanner для чтения пользовательского ввода
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в ваш чеклист!");     // Приветственное сообщение

        // Флаг для продолжения работы программы
        boolean running = true;

        // Главный цикл программы
        while (running) {
            printMenu();           // Выводим меню на экран

            // Получаем выбор пользователя
            int choice = getUserChoice();

            // Обрабатываем выбор пользователя
            switch (choice) {
                case 1:
                    addTask();      // Вызываем метод для добавления задачи
                    break;
                case 2:
                    markTaskAsDone();// Вызываем метод для отметки задачи как выполненной
                    break;
                case 3:
                    editTask();     // Вызываем метод для редактирования задачи
                    break;
                case 4:
                    removeTask();   // Вызываем метод для удаления задачи
                    break;
                case 5:
                    showTasks();    // Вызываем метод для показа всех задач
                    break;
                case 0:
                    running = false;// Завершаем работу программы
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");// Сообщение об ошибочном выборе
            }
        }

        System.out.println("До свидания!");   // Прощальное сообщение
        scanner.close();                      // Закрываем Scanner
    }

    /**
     * Метод для вывода меню на экран
     */
    private static void printMenu() {
        System.out.println("\nМеню:");             // Заголовок меню
        System.out.println("1. Добавить задачу");  // Пункт меню для добавления задачи
        System.out.println("2. Отметить задачу как выполненную");// Пункт меню для отметки задачи как выполненной
        System.out.println("3. Редактировать задачу");          // Пункт меню для редактирования задачи
        System.out.println("4. Удалить задачу");                // Пункт меню для удаления задачи
        System.out.println("5. Показать все задачи");           // Пункт меню для просмотра всех задач
        System.out.println("0. Выход");                         // Пункт меню для выхода из программы
    }

    /**
     * Метод для получения выбора пользователя
     *
     * @return Возвращает введенное число
     */
    private static int getUserChoice() {
        System.out.print("\nВаш выбор: ");  // Запрашиваем выбор пользователя
        return scanner.nextInt();           // Читаем целое число
    }

    /**
     * Метод для добавления новой задачи
     */
    private static void addTask() {
        System.out.print("Введите новую задачу: "); // Запрашиваем текст новой задачи
        String task = scanner.nextLine();           // Сбрасываем буфер после nextInt()
        task = scanner.nextLine().trim();           // Читаем строку и убираем лишние пробелы
        tasks.add(task);                            // Добавляем задачу в список
        System.out.println("Задача добавлена успешно.");// Сообщение о успешном добавлении
    }

    /**
     * Метод для отметки задачи как выполненной
     */
    private static void markTaskAsDone() {
        if (tasks.isEmpty()) {                     // Проверяем, есть ли задачи в списке
            System.out.println("Список задач пуст.");// Если нет, выводим сообщение
            return;
        }

        showTasks();                               // Показываем текущие задачи
        System.out.print("Введите номер задачи, которую хотите отметить как выполненную: ");// Запрашиваем номер задачи
        int index = scanner.nextInt() - 1;         // Преобразуем номер задачи в индекс массива

        if (index >= 0 && index < tasks.size()) {  // Проверяем корректность индекса
            String task = tasks.get(index);        // Получаем текст задачи
            tasks.set(index, strikeThrough(task)); // Зачеркиваем текст задачи и обновляем элемент в списке
            System.out.println("Задача отмечена как выполненная.");// Сообщение об успешной отметке
        } else {
            System.out.println("Некорректный индекс задачи.");// Сообщение об ошибке
        }
    }

    /**
     * Метод для редактирования существующей задачи
     */
    private static void editTask() {
        if (tasks.isEmpty()) {                     // Проверяем наличие задач
            System.out.println("Список задач пуст.");// Если нет задач, сообщаем об этом
            return;
        }

        showTasks();                               // Показываем текущие задачи
        System.out.print("Введите номер задачи, которую хотите отредактировать: ");// Запрашиваем номер задачи
        int index = scanner.nextInt() - 1;         // Преобразуем номер задачи в индекс массива

        if (index >= 0 && index < tasks.size()) {  // Проверяем корректность индекса
            System.out.print("Введите новый текст задачи: ");// Запрашиваем новый текст задачи
            String newTask = scanner.nextLine();   // Сбрасываем буфер после nextInt()
            newTask = scanner.nextLine().trim();   // Читаем строку и убираем лишние пробелы
            tasks.set(index, newTask);             // Обновляем текст задачи в списке
            System.out.println("Задача отредактирована успешно.");// Сообщение об успешной правке
        } else {
            System.out.println("Некорректный индекс задачи.");// Сообщение об ошибке
        }
    }

    /**
     * Метод для удаления задачи из списка
     */
    private static void removeTask() {
        if (tasks.isEmpty()) {                     // Проверяем наличие задач
            System.out.println("Список задач пуст.");// Если нет задач, сообщаем об этом
            return;
        }

        showTasks();                               // Показываем текущие задачи
        System.out.print("Введите номер задачи, которую хотите удалить: ");// Запрашиваем номер задачи
        int index = scanner.nextInt() - 1;         // Преобразуем номер задачи в индекс массива

        if (index >= 0 && index < tasks.size()) {  // Проверяем корректность индекса
            tasks.remove(index);                   // Удаляем задачу из списка
            System.out.println("Задача удалена успешно.");// Сообщение об успешной удалении
        } else {
            System.out.println("Некорректный индекс задачи.");// Сообщение об ошибке
        }
    }

    /**
     * Метод для показа всех текущих задач
     */
    private static void showTasks() {
        if (tasks.isEmpty()) {                     // Проверяем наличие задач
            System.out.println("Список задач пуст.");// Если нет задач, сообщаем об этом
            return;
        }

        // Проходимся по всем задачам и выводим их на экран
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));// Форматируем и выводим каждую задачу
        }
    }

    /**
     * Помощный метод для зачеркивания текста задачи
     *
     * @param s строка, которую нужно зачеркнуть
     * @return зачеркнутая строка
     */
    private static String strikeThrough(String s) {
        StringBuilder sb = new StringBuilder();    // Создаем объект StringBuilder для сборки строки

        // Проходимся по каждому символу строки и добавляем символ зачеркивания
        for (char c : s.toCharArray()) {
            sb.append(c).append('\u0336');         // Символ зачеркивания
        }

        return sb.toString();                      // Возвращаем зачеркнутую строку
    }
}


