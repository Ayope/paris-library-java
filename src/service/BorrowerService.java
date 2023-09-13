package service;

import model.Borrower;
import model.User;
import repository.BookRepo;
import repository.BorrowerRepo;
import repository.UserRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BorrowerService extends UserService{
    private static final String TELEPHONE_REGEX = "^(05|06|07)[0-9]{8}$";
    private static final String CIN_REGEX = "^[A-Z][0-9]{6}$";
    public static Borrower register(Borrower borrower) throws SQLException {
        Borrower insertedBorrower = null;
        if(validateInputs(borrower)){
            Pattern telPattern = Pattern.compile(TELEPHONE_REGEX);
            Matcher telMatcher = telPattern.matcher(borrower.getTelephone());
            if (!telMatcher.find()) {
                return null;
            }
            Pattern CINPattern = Pattern.compile(CIN_REGEX);
            Matcher CINMatcher = CINPattern.matcher(borrower.getCIN());
            if(CINMatcher.find()){
                ResultSet results = BorrowerRepo.register(borrower);
                if(results.next()){
                    insertedBorrower = new Borrower(
                            results.getInt("id"),
                            results.getString("fullname"),
                            results.getString("CIN"),
                            results.getString("email"),
                            results.getString("telephone")
                    );
                }
                return insertedBorrower;
            }
        }

        return null;
    }

//    public static boolean saveUserBorrowingCredentials(Borrower borrower) throws SQLException {
//        Pattern telPattern = Pattern.compile(TELEPHONE_REGEX);
//        Matcher telMatcher = telPattern.matcher(borrower.getTelephone());
//        if (!telMatcher.find()) {
//            return false;
//        }
//        Pattern CINPattern = Pattern.compile(CIN_REGEX);
//        Matcher CINMatcher = CINPattern.matcher(borrower.getCIN());
//        if(CINMatcher.find()){
//            return BorrowerRepo.saveUserBorrowingCredentials(borrower);
//        }
//
//        return false;
//    }

    public static ArrayList<Borrower> getBorrowers() throws SQLException {
        ArrayList<Borrower> borrowers = new ArrayList<Borrower>();
        ResultSet results = BorrowerRepo.getBorrowers();

        while(results.next()){
            borrowers.add(new Borrower(
                    results.getInt("id"),
                    results.getString("fullname"),
                    results.getString("cin"),
                    results.getString("email"),
                    results.getString("telephone")
            ));
        }

        return borrowers;
    }

    public static Borrower getBorrower(int id) throws SQLException {
        ResultSet results = BorrowerRepo.getBorrower(id);
        Borrower borrower = null;
        if (results.next()){
            borrower = new Borrower(
                    results.getInt("id"),
                    results.getString("fullname"),
                    results.getString("cin"),
                    results.getString("email"),
                    results.getString("telephone")
            );
        }

        return borrower;
    }
}
