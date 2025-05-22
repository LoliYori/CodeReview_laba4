import java.util.ArrayList;
import java.util.List;

// Класс Department открыт (public) для доступа извне
public class Department {

    // Поля инкапсулированы как private
    private String name;
    private Employee boss;
    private List<Employee> employees;

    // Конструктор открыт — делает явным, что им можно пользоваться извне
    public Department(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название отдела не может быть пустым");
        }
        this.name = name;
        this.employees = new ArrayList<>();
    }

    // Публичный геттер для имени
    public String getName() {
        return name;
    }

    // Публичный геттер для начальника
    public Employee getBoss() {
        return boss;
    }

    // Публичный сеттер для назначения начальника
    public void setBoss(Employee boss) {
        this.boss = boss;
    }

    // Метод добавления сотрудника в список
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Возвращает сам список сотрудников без защиты от изменений
    public List<Employee> getEmployees() {
        return employees;
    }

    // Переопределённый toString для вывода информации об отделе
    @Override
    public String toString() {
        return "Отдел " + name + ", начальник отдела " +
                (boss != null ? boss.getName() : "не назначен");
    }
}
