package service;

import model.Borrower;
import model.User;
import repository.BorrowerRepo;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BorrowerService extends UserService{
    private static final String TELEPHONE_REGEX = "^(05|06|07)[0-9]{8}$";
    private static final String CIN_REGEX = "^[A-Z][0-9]{6}$";
    public static boolean register(User user) throws SQLException {
        if(validateInputs(user)){
            return BorrowerRepo.register(user);
        }
        return false;
    }

    public static boolean saveUserBorrowingCredentials(Borrower borrower) throws SQLException {
        Pattern telPattern = Pattern.compile(TELEPHONE_REGEX);
        Matcher telMatcher = telPattern.matcher(borrower.getTelephone());
        if (!telMatcher.find()) {
            return false;
        }
        Pattern CINPattern = Pattern.compile(CIN_REGEX);
        Matcher CINMatcher = CINPattern.matcher(borrower.getCIN());
        if(CINMatcher.find()){
            return BorrowerRepo.saveUserBorrowingCredentials(borrower);
        }
        
        return false;
    }
}
