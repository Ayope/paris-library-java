package service;

import repository.StatsRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class StatsService {
    public static Map<String, Integer>  getStats() throws SQLException {
        Map<String, Integer> stats = new LinkedHashMap<>();

        ResultSet result = StatsRepo.getStats();
        if(result.next()){
            stats.put("books", result.getInt("total books"));
            stats.put("borrowed", result.getInt("borrowed"));
            stats.put("returned", result.getInt("returned"));
            stats.put("lost", result.getInt("lost"));
            stats.put("available books", result.getInt("available books"));
            stats.put("unavailable books", result.getInt("unavailable books"));
            stats.put("borrowers", result.getInt("total borrowers"));
        }

        return stats;
    }
}
