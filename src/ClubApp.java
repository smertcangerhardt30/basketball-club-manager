import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ClubApp {

    private static Scanner scanner = new Scanner(System.in);

    // ======================================================
    // GİRİŞ OKUMA (ScoreboardApp ile aynı)
    // ======================================================

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private static int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (use a dot, e.g. 7.5).");
            }
        }
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // ======================================================
    // DEMO VERİSİ
    // ======================================================

    private static Club createDemoClub() {
        Club club = new Club("Ankara Eagles", 400000);

        Physio physio = new Physio("Emre Sahin", 40);
        for (int i = 0; i < 12; i++) {
            physio.addSession();
        }

        try {
            club.addEmployee(new Player("Ali Kaya", 28, 5, "PG", 18.5, 60000));
            club.addEmployee(new Player("Burak Demir", 22, 11, "SG", 9.0, 25000));
            club.addEmployee(new Player("Can Ozturk", 31, 7, "SF", 14.2, 45000));
            club.addEmployee(new Player("Deniz Arslan", 19, 23, "C", 6.5, 15000));
            club.addEmployee(new HeadCoach("Murat Yilmaz", 52, 20));
            club.addEmployee(new AssistantCoach("Selin Aksoy", 35, 6, "Defense"));
            club.addEmployee(physio);
        } catch (RosterException e) {
            System.out.println("Could not load demo data: " + e.getMessage());
        }
        return club;
    }

    // ======================================================
    // MENÜ İŞLEMLERİ
    // ======================================================

    private static void handleListEmployees(Club club) {
        ArrayList<Employee> employees = club.getEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees.");
            return;
        }
        for (Employee e : employees) {
            System.out.printf("%s | Salary: %.0f%n", e, e.calculateMonthlySalary());
        }
    }

    private static void handleAddPlayer(Club club) throws RosterException {
        String name = readText("Name: ");
        int age = readInt("Age: ");
        int jerseyNumber = readInt("Jersey number: ");
        String position = readText("Position (PG/SG/SF/PF/C): ").toUpperCase();
        double ppg = readDouble("Points per game: ");
        double salary = readDouble("Contract salary: ");

        Player player = new Player(name, age, jerseyNumber, position, ppg, salary);
        club.addEmployee(player);
        System.out.println("Player added with id " + player.getId());
    }

    private static void handleRemoveEmployee(Club club) throws RosterException {
        int id = readInt("Employee id: ");
        Employee removed = club.removeEmployee(id);
        System.out.println("Removed: " + removed);
    }

    private static void handlePayrollReport(Club club) {
        double payroll = club.getTotalPayroll();
        double budget = club.getBudget();

        System.out.printf("Total payroll:    %.0f%n", payroll);
        System.out.printf("Monthly budget:   %.0f%n", budget);
        System.out.printf("Remaining budget: %.0f%n", budget - payroll);
        System.out.println("Over budget: " + club.isOverBudget());

        Employee top = club.getHighestPaid();
        if (top != null) {
            System.out.printf("Highest paid: %s (%.0f)%n", top.getName(), top.calculateMonthlySalary());
        }
    }

    private static void handlePlayersByPpg(Club club) {
        Player[] sorted = club.getPlayersSortedByPpg();
        if (sorted.length == 0) {
            System.out.println("No players.");
            return;
        }
        for (int i = 0; i < sorted.length; i++) {
            System.out.printf(Locale.US, "%d. %s - %.1f PPG%n",
                    i + 1, sorted[i].getName(), sorted[i].getPointsPerGame());
        }
    }

    private static int readDay() {
        String[] days = TrainingSchedule.DAYS;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < days.length; i++) {
            sb.append(i).append(" = ").append(days[i]);
            if (i < days.length - 1) {
                sb.append(", ");
            }
        }
        System.out.println(sb);
        return readIntInRange("Day: ", 0, days.length - 1);
    }

    private static int readSlot() {
        System.out.println("0 = Morning, 1 = Afternoon, 2 = Evening");
        return readIntInRange("Slot: ", 0, TrainingSchedule.SLOTS - 1);
    }

    private static void handleSchedule(TrainingSchedule schedule) {
        while (true) {
            System.out.println();
            System.out.println("--- Training Schedule ---");
            System.out.println("1. Show");
            System.out.println("2. Add session");
            System.out.println("3. Remove session");
            System.out.println("0. Back");
            int choice = readIntInRange("Choice: ", 0, 3);

            if (choice == 0) {
                return;
            }

            try {
                if (choice == 1) {
                    System.out.println(schedule);
                    System.out.println("Busiest day: " + schedule.getBusiestDay());
                } else if (choice == 2) {
                    int day = readDay();
                    int slot = readSlot();
                    String activity = readText("Activity: ");
                    schedule.addSession(day, slot, activity);
                    System.out.println("Session added.");
                } else {
                    int day = readDay();
                    int slot = readSlot();
                    schedule.removeSession(day, slot);
                    System.out.println("Session removed.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void handleSalaryProjection(Club club) throws RosterException {
        int id = readInt("Employee id: ");
        Employee employee = club.findById(id);

        double raise = readDouble("Yearly raise (%): ");
        int years = readIntInRange("Years (0-40): ", 0, 40);

        double current = employee.calculateMonthlySalary();
        double projected = ClubUtils.projectSalary(current, raise, years);
        System.out.printf("%s: %.0f now -> %.0f in %d years%n",
                employee.getName(), current, projected, years);
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("===== CLUB MANAGER =====");
        System.out.println("1. List employees");
        System.out.println("2. Add player");
        System.out.println("3. Remove employee");
        System.out.println("4. Payroll report");
        System.out.println("5. Players by PPG");
        System.out.println("6. Training schedule");
        System.out.println("7. Salary projection");
        System.out.println("0. Exit");
    }

    // ======================================================
    // MAIN
    // ======================================================

    public static void main(String[] args) {
        Club club = createDemoClub();
        TrainingSchedule schedule = new TrainingSchedule();

        System.out.println("=== " + club.getName() + " - Club Manager ===");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choice: ");

            try {
                switch (choice) {
                    case 1:
                        handleListEmployees(club);
                        break;
                    case 2:
                        handleAddPlayer(club);
                        break;
                    case 3:
                        handleRemoveEmployee(club);
                        break;
                    case 4:
                        handlePayrollReport(club);
                        break;
                    case 5:
                        handlePlayersByPpg(club);
                        break;
                    case 6:
                        handleSchedule(schedule);
                        break;
                    case 7:
                        handleSalaryProjection(club);
                        break;
                    case 0:
                        running = false;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (RosterException | IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
