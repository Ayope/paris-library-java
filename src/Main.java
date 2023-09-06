import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        boolean exit = false;
        boolean logged = false;

        while (!exit) {
            while (!logged && !exit) {
                System.out.println("------------Simple Auth------------");
                System.out.println("1-Register");
                System.out.println("2-Login");
                System.out.println("0-Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("------------Register------------");
                        System.out.println("Enter your fullname: ");
                        String fullname = scanner.nextLine();
                        System.out.println("Enter your email: ");
                        String email = scanner.nextLine();
                        System.out.println("Enter your password: ");
                        String password = scanner.nextLine();

                        admin.register(fullname, email, password);
                        break;

                    case 2:
                        System.out.println("------------Login------------");
                        System.out.println("Enter your fullname: ");
                        fullname = scanner.nextLine();
                        System.out.println("Enter your password: ");
                        password = scanner.nextLine();

                        logged = admin.login(fullname, password);

                        break;

                    case 0:
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }

            while (logged && !exit) {
                System.out.println("------------Library App------------");
                System.out.println("1-Logged Out");
                System.out.println("0-Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        logged = false;
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }

        System.out.println("Exiting the program.");
    }
}
