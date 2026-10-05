import java.util.ArrayList;

public class Club {
    public static final int MAX_PLAYERS = 12;

    private String name;
    private double budget;
    private ArrayList<Employee> employees;

    public Club(String name, double budget) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (budget <= 0) {
            throw new IllegalArgumentException("Budget cannot be negative and zero");
        }
        this.name = name;
        this.budget = budget;
        this.employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }

    public ArrayList<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    public void addEmployee(Employee e) throws RosterException {
        if (e == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        if (e instanceof HeadCoach && getHeadCoach() != null) {
            throw new RosterException("Club already has a head coach");
        }
        if (e instanceof Player) {
            Player newPlayer = (Player) e;
            ArrayList<Player> players = getPlayers();
            if (players.size() >= MAX_PLAYERS) {
                throw new RosterException("Roster is full (" + MAX_PLAYERS + " players)");
            }
            for (Player p : players) {
                if (p.getJerseyNumber() == newPlayer.getJerseyNumber()) {
                    throw new RosterException("Jersey number " + p.getJerseyNumber() + " is already taken");
                }
            }
        }
        employees.add(e);
    }

    public Employee removeEmployee(int id) throws RosterException {
        Employee toRemove = null;
        for (Employee e : employees) {
            if (e.getId() == id) {
                toRemove = e;
                break;
            }
        }
        if (toRemove == null) {
            throw new RosterException("No employee with id " + id);
        }
        employees.remove(toRemove);
        return toRemove;
    }

    public Employee findById(int id) throws RosterException {
        Employee toFind = null;
        for (Employee e : employees) {
            if (e.getId() == id) {
                toFind = e;
                break;
            }
        }
        if (toFind == null) {
            throw new RosterException("No employee with id " + id);
        }
        return toFind;
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (Employee e : employees) {
            if (e instanceof Player) {
                players.add((Player) e);
            }
        }
        return players;
    }

    public HeadCoach getHeadCoach() {
        for (Employee e : employees) {
            if (e instanceof HeadCoach) {
                return (HeadCoach) e;
            }
        }
        return null;
    }

    public double getTotalPayroll() {
        double total = 0;
        for (Employee e : employees) {
            total += e.calculateMonthlySalary();
        }
        return total;
    }

    public boolean isOverBudget() {
        return getTotalPayroll() > budget;
    }

    public Employee getHighestPaid() {
        if (employees.isEmpty()) {
            return null;
        }
        Employee highestPaid = employees.get(0);
        for (Employee e : employees) {
            if (e.calculateMonthlySalary() > highestPaid.calculateMonthlySalary()) {
                highestPaid = e;
            }
        }
        return highestPaid;
    }

    public Player getYoungestPlayer() {
        ArrayList<Player> players = getPlayers();
        if (players.isEmpty()) {
            return null;
        }
        Player youngest = players.get(0);
        for (Player p : players) {
            if (p.getAge() < youngest.getAge()) {
                youngest = p;
            }
        }
        return youngest;
    }

    public Player[] getPlayersSortedByPpg() {
        ArrayList<Player> players = getPlayers();
        Player[] sortedPlayers = players.toArray(new Player[0]);

        for (int i = 0; i < sortedPlayers.length - 1; i++) {
            for (int j = 0; j < sortedPlayers.length - i - 1; j++) {
                if (sortedPlayers[j].getPointsPerGame() < sortedPlayers[j + 1].getPointsPerGame()) {
                    Player temp = sortedPlayers[j];
                    sortedPlayers[j] = sortedPlayers[j + 1];
                    sortedPlayers[j + 1] = temp;
                }
            }
        }
        return sortedPlayers;
    }
}
