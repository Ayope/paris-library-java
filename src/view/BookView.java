package view;

import model.Book;
import service.BookService;

import java.sql.SQLException;
import java.util.Scanner;

public class BookView {
    private static Scanner scanner = new Scanner(System.in);
    public static void showBookManagementView() throws SQLException {
        System.out.println("----------------Book Management----------------");
        System.out.println("1-Add Book");
        System.out.println("2-Update Book");
        System.out.println("3-DeleteBook");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice){
            case 1:
                AddBook();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    public static void AddBook() throws SQLException {
        System.out.println("----------------Add Book----------------");

        System.out.println("Insert isbn :");
        String isbn = scanner.nextLine();

        System.out.println("Insert title :");
        String title = scanner.nextLine();

        System.out.println("Insert author fullname:");
        String author = scanner.nextLine();

        System.out.println("Inset the author's email:");
        String authorEmail = scanner.nextLine();

        System.out.println("Insert quantity of the book:");
        int quantity = scanner.nextInt();

        Book InsertedBook = new Book(isbn, title, quantity);

        if(BookService.checkBookInputs(InsertedBook)){
            System.out.println("Successfully Added");
        }
        /* create an object of the data and pass it to the service
            - review it in the service
            - send it to the repository and then to database
            - if the Service returns true show a message
        */
    }
}
