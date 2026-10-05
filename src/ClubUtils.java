import java.util.ArrayList;

public class ClubUtils {
    public static double projectSalary(double salary, double raisePercent, int years) {
        if (years < 0) {
            throw new IllegalArgumentException("Years cannot be negative");
        }
        if (years == 0) {
            return salary;
        }
        salary += salary * (raisePercent / 100);
        return projectSalary(salary, raisePercent, years - 1);
    }

    public static double sumPayroll(ArrayList<Employee> list, int index) {
        if (index < 0 || index > list.size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        if (index == list.size()) {
            return 0;
        }
        return list.get(index).calculateMonthlySalary() + sumPayroll(list, index + 1);
    }

    public static long combinations(int n, int k) {
        if (n < 0 || k < 0 || k > n) {
            throw new IllegalArgumentException("Invalid values for n and k");
        }
        if (k == 0 || k == n) {
            return 1;
        }
        return combinations(n - 1, k - 1) + combinations(n - 1, k);
    }

    public static int countPlayers(ArrayList<Employee> list, int index) {
        if (index < 0 || index > list.size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        if (index == list.size()) {
            return 0;
        }
        if (list.get(index) instanceof Player) {
            return 1 + countPlayers(list, index + 1);
        }
        return countPlayers(list, index + 1);
    }

}
