package repository;

import model.Borrower;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BorrowerRepo extends UserRepo{
    public static ResultSet register(Borrower borrower) throws SQLException {
        String sql = "INSERT INTO `users`(`fullname`, `email`, `telephone`, `CIN`, `role`) VALUES (?,?,?,?,?)";
        ResultSet results = null;

        preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1,borrower.getFullname());
        preparedStatement.setString(2,borrower.getEmail());
        preparedStatement.setString(3,borrower.getTelephone());
        preparedStatement.setString(4,borrower.getCIN());
        preparedStatement.setString(5,borrower.getRole());

        int rowAffected = preparedStatement.executeUpdate();

        if(rowAffected == 1){
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                int generatedUserId = generatedKeys.getInt(1);
                results = getBorrower(generatedUserId);
                return results;
            }
        }

        return null;


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

    public static ResultSet getBorrowers() throws SQLException {
        statement = conn.createStatement();
        String sql = "SELECT * FROM `users` WHERE role = 'borrower' ";
        result = statement.executeQuery(sql);
        return result;
    }

    public static ResultSet getBorrower(int id) throws SQLException{
        statement = conn.createStatement();
        String sql = "SELECT * FROM `users` WHERE id = "+ id + " AND role = 'borrower'" ;
        result = statement.executeQuery(sql);
        return result;
    }
}



