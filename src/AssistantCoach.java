public class AssistantCoach extends Coach {
    private String specialty;

    public AssistantCoach(String name, int age, int experienceYears, String specialty) {
        super(name, age, experienceYears);
        if (specialty == null || specialty.isBlank()) {
            throw new IllegalArgumentException("Specialty cannot be null or blank");
        }
        this.specialty = specialty;
    }

    @Override
    public double calculateMonthlySalary() {
        return 30000 + getExperienceYears() * 1500;
    }

    @Override
    public String getRole() {
        return "Assistant Coach (" + specialty + ")";
    }
}
