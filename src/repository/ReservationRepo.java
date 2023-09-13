package repository;

import model.Reservation;
import repository.dbConnection.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class ReservationRepo {
    private static final Connection conn;
    private static PreparedStatement preparedStatement;
    protected static Statement statement;
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
    public static boolean addReservation(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO `reservation`(`borrower_id`, `book_id`, `borrowing_date`, `return_date`, `status`) " +
                "VALUES (?,?,?,?,?);";

        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,reservation.getborrower().getId());
        preparedStatement.setInt(2,reservation.getbook().getId());
        preparedStatement.setDate(3,Date.valueOf(reservation.getBorrowingDate()));
        preparedStatement.setDate(4,Date.valueOf(reservation.getReturnDate()));
        preparedStatement.setString(5, reservation.getStatus());

        int rowAffected = preparedStatement.executeUpdate();

        return rowAffected == 1;
    }

    public static boolean returnBook(int id) throws SQLException {
        String sql = "UPDATE `reservation` SET `status`=? WHERE id=?;";

        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, "retourne");
        preparedStatement.setInt(2, id);

        int rowAffected = preparedStatement.executeUpdate();

        return rowAffected == 1;
    }

    public static ResultSet getReservations() throws SQLException {
        statement = conn.createStatement();
        String sql = "SELECT reservation.id, users.fullname, users.CIN, users.email, users.telephone,\n" +
                "books.isbn, books.title, reservation.borrowing_date, reservation.return_date, reservation.status\n" +
                "FROM reservation\n" +
                "INNER JOIN users ON users.id = reservation.borrower_id\n" +
                "INNER JOIN books ON books.id = reservation.book_id\n" +
                "WHERE reservation.return_date > CURDATE() AND reservation.status ='emprunté';\n";
        result = statement.executeQuery(sql);
        return result;
    }

    public static ResultSet getBorrowerReservations(int id) throws SQLException {
        statement = conn.createStatement();
        String sql = "SELECT reservation.id, users.fullname, users.CIN, users.email, users.telephone,\n" +
                "books.isbn, books.title, reservation.borrowing_date, reservation.return_date, reservation.status\n" +
                "FROM reservation\n" +
                "INNER JOIN users ON users.id = reservation.borrower_id\n" +
                "INNER JOIN books ON books.id = reservation.book_id\n" +
                "WHERE reservation.return_date > CURDATE() AND reservation.id = " + id;
        result = statement.executeQuery(sql);
        return result;
    }
}
