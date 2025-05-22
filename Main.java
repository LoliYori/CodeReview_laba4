import static java.lang.System.out;

import java.util.Scanner;

/**
 * Основной класс программы, демонстрирующий работу с именами, временем и сотрудниками.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            out.println("\nВыберите задание:");
            out.println("1 - Работа с именами");
            out.println("2 - Перевод секунд в время");
            out.println("3 - Ввод времени в разных форматах");
            out.println("4 - Работа с сотрудниками и отделами");
            out.println("0 - Выход");
            int choice = getInt("Ваш выбор: ");

            switch (choice) {
                case 1 -> runNameTask();
                case 2 -> runTimeFromSecondsTask();
                case 3 -> runCustomTimeInputTask();
                case 4 -> runDepartmentTask();
                case 0 -> {
                    out.println("Выход из программы.");
                    return;
                }
                default -> out.println("Ошибка: выберите номер от 0 до 4.");
            }
        }
    }

    private static void runNameTask() {
        out.println("Введите Фамилию, Имя и Отчество через пробел: ");
        Name customName;
        while (true) {
            String inputName = scanner.nextLine().trim();
            if (inputName.isEmpty()) {
                out.println("Ошибка: ввод не должен быть пустым.");
                continue;
            }

            String[] nameParts = inputName.split(" ");
            if (nameParts.length < 1 || nameParts.length > 3) {
                out.println("Ошибка: введите от одного до трёх слов (Фамилия Имя Отчество).");
                continue;
            }

            boolean valid = true;
            for (String part : nameParts) {
                if (!part.matches("[А-Яа-яA-Za-zёЁ]+")) {
                    out.println("Ошибка: имя не должно содержать цифры или спецсимволы (" + part + ").");
                    valid = false;
                    break;
                }
            }
            if (!valid) continue;

            customName = switch (nameParts.length) {
                case 3 -> new Name(nameParts[0], nameParts[1], nameParts[2]);
                case 2 -> new Name(nameParts[0], nameParts[1]);
                case 1 -> new Name(nameParts[0]);
                default -> null;
            };
            break;
        }
        out.println("\nСозданное имя:");
        out.println(customName);
    }

    private static void runTimeFromSecondsTask() {
        int seconds;
        while (true) {
            String input = getLine("Введите количество секунд с начала суток: ");
            if (!input.matches("\\d+")) {
                out.println("Ошибка: введите неотрицательное целое число.");
                continue;
            }
            seconds = Integer.parseInt(input);
            break;
        }
        Time timeFromSeconds = new Time(seconds);
        out.println("\nТекущее время из секунд:");
        out.println(timeFromSeconds);
    }

    private static void runCustomTimeInputTask() {
        Time customTime;
        out.println("Введите (часы минуты секунды) через пробел или общее количество секунд:");

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                out.println("Ошибка: ввод не должен быть пустым.");
                continue;
            }

            if (input.contains(" ")) {
                String[] parts = input.split(" ");
                if (parts.length != 3) {
                    out.println("Ошибка: введите ровно три значения (часы минуты секунды).");
                    continue;
                }

                try {
                    int h = Integer.parseInt(parts[0]);
                    int m = Integer.parseInt(parts[1]);
                    int s = Integer.parseInt(parts[2]);
                    if (h < 0 || m < 0 || s < 0) {
                        out.println("Ошибка: значения не могут быть отрицательными.");
                        continue;
                    }
                    customTime = new Time(h, m, s);
                    break;
                } catch (NumberFormatException e) {
                    out.println("Ошибка: введите только целые числа.");
                }
            } else {
                if (!input.matches("\\d+")) {
                    out.println("Ошибка: введите неотрицательное число секунд.");
                    continue;
                }
                int seconds = Integer.parseInt(input);
                customTime = new Time(seconds);
                break;
            }
        }

        out.println("\nТекущее время:");
        out.println(customTime);
    }

    private static void runDepartmentTask() {
        String[] employeeNames;
        while (true) {
            out.println("Введите имена сотрудников через пробел:");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                out.println("Ошибка: список не должен быть пустым.");
                continue;
            }

            employeeNames = input.split(" ");
            boolean valid = true;
            for (String name : employeeNames) {
                if (!name.matches("[А-Яа-яA-Za-zёЁ]+")) {
                    out.println("Ошибка: имя \"" + name + "\" содержит недопустимые символы.");
                    valid = false;
                    break;
                }
            }
            if (valid) break;
        }

        Department itDepartment = new Department("IT");
        for (String name : employeeNames) {
            new Employee(name, itDepartment);
        }

        Employee boss = null;
        while (boss == null) {
            out.println("Введите имя начальника отдела:");
            String bossName = scanner.nextLine().trim();
            for (Employee emp : itDepartment.getEmployees()) {
                if (emp.getName().equalsIgnoreCase(bossName)) {
                    boss = emp;
                    itDepartment.setBoss(boss);
                    break;
                }
            }
            if (boss == null) {
                out.println("Ошибка: сотрудник с таким именем не найден.");
            }
        }

        for (Employee emp : itDepartment.getEmployees()) {
            if (!emp.equals(boss)) {
                emp.setBoss(boss);
            }
        }

        out.println("\nСотрудники отдела " + itDepartment.getName() + ":");
        for (Employee emp : itDepartment.getEmployees()) {
            out.println(emp);
        }

        out.println("\nВведите имя сотрудника, чтобы показать всех сотрудников его отдела:");
        String searchName = scanner.nextLine().trim();
        boolean found = false;
        for (Employee emp : itDepartment.getEmployees()) {
            if (emp.getName().equalsIgnoreCase(searchName)) {
                out.println("Сотрудники отдела " + itDepartment.getName() + ":");
                for (Employee e : itDepartment.getEmployees()) {
                    out.println(e);
                }
                found = true;
                break;
            }
        }
        if (!found) {
            out.println("Ошибка: сотрудник не найден.");
        }
    }

    /**
     * Безопасный ввод целого числа с повтором в случае ошибки.
     */
    private static int getInt(String prompt) {
        int result;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                result = scanner.nextInt();
                scanner.nextLine(); // очищаем буфер
                return result;
            } else {
                out.println("Ошибка: введите целое число.");
                scanner.nextLine(); // очищаем ввод
            }
        }
    }

    /**
     * Считывание строки с выводом приглашения.
     */
    private static String getLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
