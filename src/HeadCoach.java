public class HeadCoach extends Coach {
    public HeadCoach(String name, int age, int experienceYears) {
        super(name, age, experienceYears);

    }

    @Override
    public double calculateMonthlySalary() {
        return 80000 + getExperienceYears() * 3000;
    }

    @Override
    public String getRole() {
        return "Head Coach";
    }
}
