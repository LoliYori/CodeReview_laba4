/* Продолжение задания №Name */

// Класс Name представляет полное имя человека и предоставляет разные способы его создания.
public class Name {
	private String lastName;
	private String firstName;
	private String middleName;

	// Конструктор с тремя частями имени.
	public Name(String lastName, String firstName, String middleName) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
	}

	// Конструктор только с фамилией.
	public Name(String lastName) {
		this(lastName, null, null);
	}

	// Конструктор с фамилией и именем.
	public Name(String lastName, String firstName) {
		this(lastName, firstName, null);
	}

	// Геттеры сделаны public — по умолчанию package-private видимость нарушает инкапсуляцию и неочевидна.
	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	@Override
	public String toString() {
		// Собираем строку полного имени, пропуская null-значения.
		StringBuilder sb = new StringBuilder();

		if (lastName != null) {
			sb.append(lastName);
		}

		if (firstName != null) {
			if (sb.length() > 0) {
				sb.append(" ");
			}
			sb.append(firstName);
		}

		if (middleName != null) {
			if (sb.length() > 0) {
				sb.append(" ");
			}
			sb.append(middleName);
		}

		return sb.toString();
	}
}
