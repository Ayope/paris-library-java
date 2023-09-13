package repository;

import model.Book;
import repository.dbConnection.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class BookRepo {
    private static final Connection conn;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static ResultSet result;

    static {
        preparedStatement = null;
        result = null;
        try {
            conn = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean insert(Book book) throws SQLException {

        String sql = "INSERT INTO books (isbn, title, quantity, author) VALUES\n" +
                "(?, ?, ?, ?)";

        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,book.getIsbn());
        preparedStatement.setString(2,book.getTitle());
        preparedStatement.setInt(3,book.getQuantity());
        preparedStatement.setString(4,book.getAuthor());

        int rowAffected = preparedStatement.executeUpdate();

        return rowAffected == 1;
    }

    public static boolean update(Book book) throws SQLException {
        String sql = "UPDATE `books` SET " +
                "`isbn`= ? ,`title`= ? ,`quantity`= ? ,`author`= ? " +
                "WHERE id = ?";

        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,book.getIsbn());
        preparedStatement.setString(2,book.getTitle());
        preparedStatement.setInt(3,book.getQuantity());
        preparedStatement.setString(4,book.getAuthor());
        preparedStatement.setInt(5,book.getId());

        int rowAffected = preparedStatement.executeUpdate();

        return rowAffected == 1;
    }

    public static ResultSet getBooksList() throws SQLException {
        statement = conn.createStatement();
        String sql = "SELECT * FROM `books` WHERE books.quantity > 0";
        result = statement.executeQuery(sql);
        return result;
    }

    public static ResultSet getBook(int id) throws SQLException {
        statement = conn.createStatement();
        String sql = "SELECT * FROM `books` WHERE id = " + id;
        result = statement.executeQuery(sql);
        return result;
    }

    public static boolean deleteBook(int id) throws SQLException {
        String sql = "DELETE FROM `books` WHERE id = ?";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        int rowAffected = preparedStatement.executeUpdate();

        return rowAffected == 1;
    }

    public static ResultSet searchBooks(String searchWord) throws SQLException {
        statement = conn.createStatement();
        String sql = "SELECT * FROM `books` WHERE title LIKE '%" + searchWord + "%' OR author LIKE '%" + searchWord + "%'";
        result = statement.executeQuery(sql);
        return result;
    }
}

