// Класс Time объявлен как public — это делает его доступным извне и соответствует требованиям Google Style
public class Time {

    // Поле инкапсулирует общее количество секунд с начала суток (0–86399)
    private int seconds;

    // Конструктор по количеству секунд — позволяет задавать время напрямую
    public Time(int seconds) {
        setSeconds(seconds);
    }

    // Конструктор по часам, минутам и секундам — более привычный способ задания времени
    public Time(int hours, int minutes, int seconds) {
        setTime(hours, minutes, seconds);
    }

    // Геттер для часов — позволяет получить часы в формате 0–23
    public int getHours() {
        return seconds / 3600;
    }

    // Геттер для минут — возвращает остаточные минуты (0–59)
    public int getMinutes() {
        return (seconds % 3600) / 60;
    }

    // Геттер для секунд — возвращает остаточные секунды (0–59)
    public int getSeconds() {
        return seconds % 60;
    }

    // Сеттер для общего количества секунд с начала суток — с нормализацией и защитой от отрицательных значений
    public void setSeconds(int seconds) {
        this.seconds = Math.floorMod(seconds, 86400);
    }

    // Сеттер для времени по компонентам — с базовой валидацией
    public void setTime(int hours, int minutes, int seconds) {
        if (hours < 0 || minutes < 0 || seconds < 0) {
            throw new IllegalArgumentException("Отрицательное время не допускается.");
        }
        int totalSeconds = hours * 3600 + minutes * 60 + seconds;
        this.seconds = Math.floorMod(totalSeconds, 86400);
    }

    // Переопределённый метод toString — вывод в формате HH:MM:SS
    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", getHours(), getMinutes(), getSeconds());
    }
}
