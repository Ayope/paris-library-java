package repository;

import model.Book;
import repository.dbConnection.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookRepo {
    private static Connection conn;

//    public BookRepo() throws SQLException {
//        conn = DatabaseConnection.getConnection();
//    }

    public static boolean insert(Book book) throws SQLException {
        conn = DatabaseConnection.getConnection();

        String sql = "INSERT INTO books (isbn, title, quantity, author_id) VALUES\n" +
                "(?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,book.getIsbn());
        statement.setString(2,book.getTitle());
        statement.setInt(3,book.getQuantity());
        statement.setInt(4,1);

        int rowAffected = statement.executeUpdate();

        if(rowAffected == 1){
            return true;
        }

        return false;
    }
}
