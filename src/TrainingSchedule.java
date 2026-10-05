public class TrainingSchedule {
    public static final String[] DAYS = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
    public static final int SLOTS = 3;

    private String[][] sessions;

    public TrainingSchedule() {
        sessions = new String[DAYS.length][SLOTS];
    }

    public void addSession(int day, int slot, String activity) {
        if (day < 0 || day >= DAYS.length) {
            throw new IllegalArgumentException("Invalid day index");
        }
        if (slot < 0 || slot >= SLOTS) {
            throw new IllegalArgumentException("Invalid slot index");
        }
        if (activity == null || activity.isBlank()) {
            throw new IllegalArgumentException("Activity cannot be null or blank");
        }
        if (sessions[day][slot] != null) {
            throw new IllegalStateException("Slot already occupied");
        }
        sessions[day][slot] = activity;
    }

    public void removeSession(int day, int slot) {
        if (day < 0 || day >= DAYS.length) {
            throw new IllegalArgumentException("Invalid day index");
        }
        if (slot < 0 || slot >= SLOTS) {
            throw new IllegalArgumentException("Invalid slot index");
        }
        if (sessions[day][slot] == null) {
            throw new IllegalStateException("Slot is already empty");
        }
        sessions[day][slot] = null;
    }

    public int countSessionsOnDay(int day) {
        if (day < 0 || day >= DAYS.length) {
            throw new IllegalArgumentException("Invalid day index");
        }
        int count = 0;
        for (int slot = 0; slot < SLOTS; slot++) {
            if (sessions[day][slot] != null) {
                count++;
            }
        }
        return count;
    }

    public int countSessions() {
        int totalCount = 0;
        for (int day = 0; day < DAYS.length; day++) {
            totalCount += countSessionsOnDay(day);
        }
        return totalCount;
    }

    public String getBusiestDay() {
        int maxCount = 0;
        String busiestDay = "None";
        for (int day = 0; day < DAYS.length; day++) {
            int count = countSessionsOnDay(day);
            if (count > maxCount) {
                maxCount = count;
                busiestDay = DAYS[day];
            }
        }
        return busiestDay;
    }

    @Override
    public String toString() {
        String result = "";
        for (int day = 0; day < DAYS.length; day++) {
            result += DAYS[day] + ": ";
            for (int slot = 0; slot < SLOTS; slot++) {
                if (sessions[day][slot] != null) {
                    result += sessions[day][slot];
                } else {
                    result += "-";
                }
                if (slot < SLOTS - 1) {
                    result += " | ";
                }
            }
            if (day < DAYS.length - 1) {
                result += "\n";
            }
        }
        return result;
    }
}
