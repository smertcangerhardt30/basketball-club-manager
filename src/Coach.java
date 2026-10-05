public abstract class Coach extends Employee {
    private int experienceYears;

    public Coach(String name, int age, int experienceYears) {
        super(name, age);
        if (experienceYears < 0) {
            throw new IllegalArgumentException("Experience years cannot be negative");
        }
        this.experienceYears = experienceYears;
    }

    public int getExperienceYears() {
        return experienceYears;
    }
}
