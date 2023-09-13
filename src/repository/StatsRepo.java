package repository;

import repository.dbConnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

public class StatsRepo {

    public static ResultSet getStats() throws SQLException {
        Statement statement ;
        Connection conn = DatabaseConnection.getConnection();
        statement = conn.createStatement();

        String sql = "SELECT\n" +
                "(SELECT COUNT(*) FROM `reservation` WHERE reservation.status = \"emprunté\") AS \"borrowed\",\n" +
                "(SELECT COUNT(*) FROM `reservation` WHERE reservation.status = \"retourné\") AS \"returned\",\n" +
                "(SELECT COUNT(*) FROM `reservation` WHERE reservation.status = \"perdu\") AS \"lost\",\n" +
                "(SELECT COUNT(*) FROM `books`) AS \"total books\",\n" +
                "(SELECT COUNT(*) FROM `books` WHERE books.quantity > 0) AS \"available books\",\n" +
                "(SELECT COUNT(*) FROM `books` WHERE books.quantity = 0) AS \"unavailable books\",\n" +
                "(SELECT COUNT(*) FROM `users` WHERE users.role = \"borrower\") AS \"total borrowers\";\n";

        ResultSet result = null;
        result = statement.executeQuery(sql);
        return result;
    }
}
