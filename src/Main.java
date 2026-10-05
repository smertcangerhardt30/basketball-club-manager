public class Main {
    public static void main(String[] args) {

        // ===== KURULUM =====
        Club club = new Club("Ankara Eagles", 400000);

        Player ali = new Player("Ali Kaya", 28, 5, "PG", 18.5, 60000);
        Player burak = new Player("Burak Demir", 22, 11, "SG", 9.0, 25000);
        Player can = new Player("Can Ozturk", 31, 7, "SF", 14.2, 45000);
        Player deniz = new Player("Deniz Arslan", 19, 23, "C", 6.5, 15000);
        HeadCoach murat = new HeadCoach("Murat Yilmaz", 52, 20);
        AssistantCoach selin = new AssistantCoach("Selin Aksoy", 35, 6, "Defense");
        Physio emre = new Physio("Emre Sahin", 40);

        for (int i = 0; i < 12; i++) {
            emre.addSession();
        }

        try {
            club.addEmployee(ali);
            club.addEmployee(burak);
            club.addEmployee(can);
            club.addEmployee(deniz);
            club.addEmployee(murat);
            club.addEmployee(selin);
            club.addEmployee(emre);
        } catch (RosterException e) {
            System.out.println("Setup failed: " + e.getMessage());
            return;
        }

        // ===== A) ÇALIŞANLAR =====
        printHeader("A) EMPLOYEES");
        for (Employee e : club.getEmployees()) {
            System.out.printf("%s | Salary: %.0f%n", e, e.calculateMonthlySalary());
        }

        // ===== B) KULÜP ANALİZİ =====
        printHeader("B) CLUB ANALYSIS");
        System.out.printf("Total payroll: %.0f%n", club.getTotalPayroll());
        System.out.println("Over budget: " + club.isOverBudget());
        System.out.println("Highest paid: " + club.getHighestPaid().getName());
        System.out.println("Youngest player: " + club.getYoungestPlayer().getName());
        System.out.println("Player count: " + club.getPlayers().size());
        System.out.println("Head coach: " + club.getHeadCoach().getName());

        Player[] sorted = club.getPlayersSortedByPpg();
        System.out.print("Players by PPG: ");
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i].getName());
            if (i < sorted.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();

        // ===== C) RECURSION =====
        printHeader("C) RECURSION");
        System.out.printf("projectSalary(100000, 10, 3): %.0f%n", ClubUtils.projectSalary(100000, 10, 3));
        System.out.printf("projectSalary(100000, 10, 0): %.0f%n", ClubUtils.projectSalary(100000, 10, 0));
        System.out.printf("sumPayroll: %.0f%n", ClubUtils.sumPayroll(club.getEmployees(), 0));
        System.out.println("combinations(12, 5): " + ClubUtils.combinations(12, 5));
        System.out.println("combinations(4, 2): " + ClubUtils.combinations(4, 2));
        System.out.println("countPlayers: " + ClubUtils.countPlayers(club.getEmployees(), 0));

        // ===== D) ANTRENMAN PROGRAMI =====
        printHeader("D) TRAINING SCHEDULE");
        TrainingSchedule schedule = new TrainingSchedule();
        schedule.addSession(0, 0, "Shooting");
        schedule.addSession(0, 2, "Video analysis");
        schedule.addSession(2, 0, "Gym");
        schedule.addSession(2, 1, "Team practice");
        schedule.addSession(2, 2, "Recovery");
        schedule.addSession(4, 1, "Team practice");
        schedule.addSession(5, 0, "Scrimmage");

        System.out.println(schedule);
        System.out.println("Total sessions: " + schedule.countSessions());
        System.out.println("Sessions on Wed: " + schedule.countSessionsOnDay(2));
        System.out.println("Busiest day: " + schedule.getBusiestDay());
        schedule.removeSession(0, 0);
        System.out.println("After removing Mon morning: " + schedule.countSessions());

        // ===== E) HATA TESTLERİ =====
        printHeader("E) ERROR TESTS");

        try {
            new Player("Test", 25, 10, "XX", 5.0, 10000);
            System.out.println("1. NO ERROR (wrong!)");
        } catch (IllegalArgumentException e) {
            System.out.println("1. " + e.getMessage());
        }

        try {
            new Player("Test", 15, 10, "PG", 5.0, 10000);
            System.out.println("2. NO ERROR (wrong!)");
        } catch (IllegalArgumentException e) {
            System.out.println("2. " + e.getMessage());
        }

        try {
            club.addEmployee(new HeadCoach("Test Coach", 45, 10));
            System.out.println("3. NO ERROR (wrong!)");
        } catch (RosterException e) {
            System.out.println("3. " + e.getMessage());
        }

        try {
            club.addEmployee(new Player("Test Player", 25, 5, "PG", 5.0, 10000));
            System.out.println("4. NO ERROR (wrong!)");
        } catch (RosterException e) {
            System.out.println("4. " + e.getMessage());
        }

        try {
            club.removeEmployee(999);
            System.out.println("5. NO ERROR (wrong!)");
        } catch (RosterException e) {
            System.out.println("5. " + e.getMessage());
        }

        try {
            club.addEmployee(null);
            System.out.println("6. NO ERROR (wrong!)");
        } catch (IllegalArgumentException e) {
            System.out.println("6. " + e.getMessage());
        } catch (RosterException e) {
            System.out.println("6. Wrong exception type: " + e.getMessage());
        }

        try {
            schedule.addSession(7, 0, "Gym");
            System.out.println("7. NO ERROR (wrong!)");
        } catch (IllegalArgumentException e) {
            System.out.println("7. " + e.getMessage());
        }

        try {
            schedule.addSession(2, 1, "Gym");
            System.out.println("8. NO ERROR (wrong!)");
        } catch (IllegalStateException e) {
            System.out.println("8. " + e.getMessage());
        }

        try {
            for (int i = 0; i < 8; i++) {
                club.addEmployee(new Player("Reserve " + (i + 1), 20, 30 + i, "SF", 2.0, 10000));
            }
            System.out.println("9. Roster size: " + club.getPlayers().size());
            club.addEmployee(new Player("Reserve 9", 20, 38, "SF", 2.0, 10000));
            System.out.println("9. NO ERROR (wrong!)");
        } catch (RosterException e) {
            System.out.println("9. " + e.getMessage());
        }
    }

    private static void printHeader(String title) {
        System.out.println();
        System.out.println("===== " + title + " =====");
    }
}
