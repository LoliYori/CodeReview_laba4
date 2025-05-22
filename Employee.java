// Класс Employee объявлен как public — это делает его доступным извне
public class Employee {

    // Поля объявлены private для инкапсуляции
    private String name;
    private Department department;
    private Employee boss;

    // Конструктор с валидацией имени и автоматическим добавлением в отдел
    public Employee(String name, Department department) {
        setName(name);
        setDepartment(department);
        this.boss = null;
        department.addEmployee(this);
    }

    // Геттеры

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public Employee getBoss() {
        return boss;
    }

    // Сеттеры

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым.");
        }
        if (!name.matches("[А-Яа-яA-Za-z\\s\\-]+")) {
            throw new IllegalArgumentException("Имя не должно содержать цифры или спецсимволы.");
        }
        this.name = name.trim();
    }

    public void setDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("Отдел не может быть null.");
        }
        this.department = department;
    }

    public void setBoss(Employee boss) {
        this.boss = boss;
    }

    // Переопределённый метод toString
    @Override
    public String toString() {
        if (boss == null) {
            return name + " — начальник отдела " + department.getName();
        } else {
            return name + " работает в отделе " + department.getName() + ", начальник — " + boss.getName();
        }
    }
}
