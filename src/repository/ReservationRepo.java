package repository;

import model.Reservation;
import repository.dbConnection.DatabaseConnection;

import java.sql.*;

public class ReservationRepo {
    private static final Connection conn;
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
}
