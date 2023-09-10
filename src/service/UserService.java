package service;

import model.User;
import repository.UserRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    // Define the regular expressions as constants
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PASSWORD_REGEX = "^[a-zA-Z0-9]{8}$";

    public static boolean validateInputs(User user) {
        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
        Matcher emailMatcher = emailPattern.matcher(user.getEmail());

        if (!emailMatcher.find()) {
            return false;
        }

        Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);
        Matcher passwordMatcher = passwordPattern.matcher(user.getPassword());
        return passwordMatcher.find();
    }

    public static User login(User user) throws SQLException {
        User returnedUser = null;
        if(validateInputs(user)){
            ResultSet result = UserRepo.login(user);
            if(result.next()){
                return returnedUser = new User(
                        result.getInt("id"),
                        result.getString("fullname"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("role")
                );
            }
        }
        return null;
    }
}
