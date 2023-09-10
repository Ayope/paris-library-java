package view;

import model.Book;
import model.Borrower;
import model.Reservation;
import model.User;
import service.BookService;
import service.BorrowerService;
import service.ReservationService;

import java.sql.SQLException;
import java.util.Scanner;

public class ReservationView {
    private static Scanner scanner = new Scanner(System.in);
    public static void showReservationInterface(int loggedUserId) throws SQLException {
        BookView.listBooks(BookService.getBooksList());
        System.out.println("Choose which book you like to borrow by providing its ID:");
        int id = scanner.nextInt(); // check if the id is in the list or not
        Book borrowedBook = BookService.getBook(id);

        while (borrowedBook == null){
            System.out.println("Not in the list! choose another ID:");
            id = scanner.nextInt();
            borrowedBook = BookService.getBook(id);
        }

        System.out.println("For how many Days you wish borrow this book");
        System.out.println("1- 15 days");
        System.out.println("2- 21 days");
        System.out.println("3- 30 days");
        int borrowingDays = scanner.nextInt();
        while (borrowingDays != 1 && borrowingDays != 2 && borrowingDays != 3) {
            System.out.println("Not in the list! choose an exist choice:");
            borrowingDays = scanner.nextInt();
        }
        switch (borrowingDays){
            case 1 -> borrowingDays = 15;
            case 2 -> borrowingDays = 21;
            case 3 -> borrowingDays = 30;
        }
        scanner.nextLine();

        System.out.println("Ok to complete the process you should enter:");
        System.out.println("1-Phone number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("2-your CIN");
        String CIN = scanner.nextLine();
        Borrower borrower = new Borrower(loggedUserId, phoneNumber, CIN);

        Reservation reservation = new Reservation(borrower, borrowedBook);
        if(BorrowerService.saveUserBorrowingCredentials(borrower)){
            ReservationService.addReservation(reservation, borrowingDays);
        }else{
            System.out.println("Something is wrong with your credentials, try again");
        }
    }
}
