package view;

import model.User;
import service.BookService;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        boolean logged = false;
        User loggedUser = null;
        String role = null;
        int id = 0;

        while (!exit) {
            while (!logged && !exit) {
                System.out.println("------------Authentication------------");
                System.out.println("1-Register");
                System.out.println("2-Login");
                System.out.println("0-Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> UserView.register();
                    case 2 -> {
                        loggedUser = UserView.login();
                        if (loggedUser != null) {
                            role = loggedUser.getRole();
                            id = loggedUser.getId();
                            logged = true;
                            System.out.println("Welcome back !");
                        } else {
                            System.out.println("Something went wrong (check your inputs and try again!)");
                        }
                    }
                    case 0 -> exit = true;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }

            if(Objects.equals(role, "admin")){
                while (logged && !exit) {
                    System.out.println("------------Library App------------");
                    System.out.println("1-Logged Out");
                    System.out.println("2-Manage Books");
                    System.out.println("0-Exit");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1 -> logged = false;
                        case 2 -> BookView.showBookManagementView();
                        case 0 -> exit = true;
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                }
            }else {
                while (logged && !exit) {
                    System.out.println("------------Library App------------");
                    System.out.println("1-Logged Out");
                    System.out.println("2-Consult the list of books");
                    System.out.println("3-Borrow a book");
                    System.out.println("0-Exit");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1 -> logged = false;
                        case 2 -> BookView.listBooks(BookService.getBooksList());
                        case 3 -> ReservationView.showReservationInterface(id);
                        case 0 -> exit = true;
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
        }

        System.out.println("Exiting the program.");
    }
}
