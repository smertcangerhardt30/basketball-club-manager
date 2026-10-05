public class Physio extends Employee {
    private int sessionsThisMonth;

    public Physio(String name, int age) {
        super(name, age);
        this.sessionsThisMonth = 0;
    }

    public void addSession() {
        sessionsThisMonth++;
    }

    public int getSessionsThisMonth() {
        return sessionsThisMonth;
    }

    @Override
    public double calculateMonthlySalary() {
        return 20000 + getSessionsThisMonth() * 500;
    }

    @Override
    public String getRole() {
        return "Physio";
    }
}
