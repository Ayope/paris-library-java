package view;

import service.StatsService;

import java.sql.SQLException;
import java.util.Map;

public class StatsView {
    public static void showStats() throws SQLException {
        Map<String, Integer> stats = StatsService.getStats();

        System.out.println("-------------STATISTIQUES-------------");

        System.out.printf("%-20s %-10s\n", "Category", "Count");
        System.out.println("--------------------------------------");

        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            String category = entry.getKey();
            int count = entry.getValue();
            System.out.printf("%-20s %-10d\n", category, count);
        }

        System.out.println("--------------------------------------");
    }
}
