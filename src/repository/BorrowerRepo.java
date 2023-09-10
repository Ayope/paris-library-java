package repository;

import model.Borrower;
import model.User;

import java.sql.SQLException;

public class BorrowerRepo extends UserRepo{
    public static Boolean register(User user) throws SQLException {
        String sql = "INSERT INTO `users`(`fullname`, `email`, `password`, `role`) VALUES (?,?,?,?)";

        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,user.getFullname());
        preparedStatement.setString(2,user.getEmail());
        preparedStatement.setString(3,user.getPassword());
        preparedStatement.setString(4,user.getRole());

        int rowAffected = preparedStatement.executeUpdate();

        return rowAffected == 1;
    }

    public static boolean saveUserBorrowingCredentials(Borrower borrower) throws SQLException {
        String sql = "UPDATE `users` SET `telephone`= ?,`CIN`= ? WHERE id = ?";

        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,borrower.getTelephone());
        preparedStatement.setString(2,borrower.getCIN());
        preparedStatement.setInt(3,borrower.getId());

        int rowAffected = preparedStatement.executeUpdate();

        return rowAffected == 1;
    }
}



