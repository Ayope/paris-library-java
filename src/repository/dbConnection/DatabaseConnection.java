package repository.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    private static String dbUrl = "jdbc:mysql://localhost:3306/paris_library1";
    private static String username = "root";
    private static String password = "";

    public static Connection getConnection() throws SQLException{
        try{
            connection = DriverManager.getConnection(dbUrl, username, password);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return connection;
    }
}
