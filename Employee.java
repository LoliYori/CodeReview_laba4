// Класс Employee объявлен как public — это делает его доступным извне
public class Employee {

    // Поля объявлены private для инкапсуляции — скрываем внутреннее состояние
    private String name;
    private Department department;
    private Employee boss;

    // Конструктор с валидацией имени и автоматическим добавлением в отдел
    public Employee(String name, Department department) {
        setName(name); 
        setDepartment(department);  
        this.boss = null;  
        if (this.department != null) {
            this.department.addEmployee(this);
        }
    }

    // Геттеры — публичные методы для доступа к полям класса

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public Employee getBoss() {
        return boss;
    }

    // Сеттеры — публичные методы для изменения полей класса с валидацией

    public void setName(String name) {
        // Проверка, что имя не пустое и не null
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Ошибка: имя не может быть пустым. Установлено имя 'Без имени'.");
            this.name = "Без имени"; 
            return;
        }
        // Проверка, что имя не содержит цифр и спецсимволов
        if (!name.matches("[А-Яа-яA-Za-z\\s\\-]+")) {
            System.out.println("Ошибка: имя не должно содержать цифры или спецсимволы. Установлено имя 'Без имени'.");
            this.name = "Без имени";
            return;
        }
        this.name = name.trim(); 
    }

    public void setDepartment(Department department) {
        // Проверка, что отдел не null
        if (department == null) {
            System.out.println("Ошибка: отдел не может быть null. Отдел не установлен.");
            this.department = null;
            return;
        }
        this.department = department;
    }

    public void setBoss(Employee boss) {
        // Просто устанавливаем начальника без дополнительной логики
        this.boss = boss;
    }

    // Переопределённый метод toString для удобного отображения информации о сотруднике
    @Override
    public String toString() {
        if (boss == null) {
            return name + " — начальник отдела " + (department != null ? department.getName() : "неизвестен");
        } else {
            return name + " работает в отделе " + (department != null ? department.getName() : "неизвестен") + ", начальник — " + boss.getName();
        }
    }
}
