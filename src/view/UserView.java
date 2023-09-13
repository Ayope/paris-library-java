package view;

import model.Book;
import model.Borrower;
import model.User;
import service.BorrowerService;
import service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserView {
    private static Scanner scanner = new Scanner(System.in);
    private static String email;
    private static String password;

    public static User login() throws SQLException {
        System.out.println("------------Login------------");
        System.out.println("Enter your email: ");
        email = scanner.nextLine();
        System.out.println("Enter your password: ");
        password = scanner.nextLine();

        User user = new User(email, password);
        return UserService.login(user);
    }

//    public static void register() throws SQLException {
//        System.out.println("------------Register------------");
//        System.out.println("Enter your fullname: ");
//        String fullname = scanner.nextLine();
//        System.out.println("Enter your email: ");
//        String email = scanner.nextLine();
//        System.out.println("Enter your password: (8 chars minimum!)");
//        String password = scanner.nextLine();
//
//        User user = new User(fullname ,email, password, "borrower");
//
//        if (BorrowerService.register(user)) {
//            System.out.println("You Registered Successfully");
//        } else {
//            System.out.println("Something went wrong (check your inputs and try again!)");
//        }
//
//    }

    public static void listBorrowers() throws SQLException {
        ArrayList<Borrower> borrowers = BorrowerService.getBorrowers();
        if (!borrowers.isEmpty()) {
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.printf("%-2s | %-20s | %-15s | %-30s | %-15s%n", "ID", "Full Name", "CIN", "Email", "Telephone");
            System.out.println("-------------------------------------------------------------------------------------------------");
            for (Borrower borrower : borrowers) {
                System.out.printf("%-2d | %-20s | %-15s | %-30s | %-15s%n", borrower.getId(), borrower.getFullname(), borrower.getCIN(), borrower.getEmail(), borrower.getTelephone());
            }
            System.out.println("-------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No borrower found.");
        }
    }
}
