package repository;

import model.User;
import repository.dbConnection.DatabaseConnection;

import java.sql.*;

public class UserRepo {
    protected static final Connection conn;
    protected static Statement statement;
    protected static PreparedStatement preparedStatement;
    protected static ResultSet result;

    static {
        preparedStatement = null;
        result = null;
        try {
            conn = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet login(User user) throws SQLException {
        statement = conn.createStatement();
        String sql = "SELECT * FROM `users` WHERE email = '" + user.getEmail() + "' AND password = '" + user.getPassword() + "'";
        result = statement.executeQuery(sql);
        return result;
    }
}
