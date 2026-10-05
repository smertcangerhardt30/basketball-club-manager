public abstract class Employee {
    private static int nextId = 1;

    private final int id;

    private String name;
    private int age;

    public Employee(String name, int age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (age < 16 || age > 75) {
            throw new IllegalArgumentException("Age must be between 16 and 75");
        }
        this.name = name;
        this.age = age;
        id = nextId++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract double calculateMonthlySalary();

    public abstract String getRole();

    public String toString() {
        return String.format("#%d %s (%s, %d)", id, name, getRole(), age);
    }
}
