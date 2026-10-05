import java.util.Locale;

public class Player extends Employee {
    private static final String[] VALID_POSITIONS = { "PG", "SG", "SF", "PF", "C" };

    private int jerseyNumber;
    private String position;
    private double pointsPerGame;
    private double contractSalary;

    public Player(String name, int age, int jerseyNumber, String position, double pointsPerGame,
            double contractSalary) {
        super(name, age);
        if (jerseyNumber < 0 || jerseyNumber > 99) {
            throw new IllegalArgumentException("Jersey number must be between 0 and 99");
        }
        if (!isValidPosition(position)) {
            throw new IllegalArgumentException("Invalid position: " + position);
        }
        if (pointsPerGame < 0) {
            throw new IllegalArgumentException("Points per game cannot be negative");
        }
        if (contractSalary <= 0) {
            throw new IllegalArgumentException("Contract salary cannot be negative or zero");
        }
        this.jerseyNumber = jerseyNumber;
        this.position = position;
        this.pointsPerGame = pointsPerGame;
        this.contractSalary = contractSalary;
    }

    private static boolean isValidPosition(String position) {
        for (String validPosition : VALID_POSITIONS) {
            if (validPosition.equals(position)) {
                return true;
            }
        }
        return false;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public String getPosition() {
        return position;
    }

    public double getPointsPerGame() {
        return pointsPerGame;
    }

    @Override
    public double calculateMonthlySalary() {
        return contractSalary + pointsPerGame * 1000;
    }

    @Override
    public String getRole() {
        return "Player";
    }

    public String toString() {
        return super.toString() + String.format(Locale.US, " | #%d %s | %.1f PPG",
                jerseyNumber, position, pointsPerGame);
    }
}
