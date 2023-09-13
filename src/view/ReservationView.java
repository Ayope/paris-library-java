package view;

import model.Book;
import model.Borrower;
import model.Reservation;
import model.User;
import service.BookService;
import service.BorrowerService;
import service.ReservationService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservationView {
    private static Scanner scanner = new Scanner(System.in);
    public static void addReservation() throws SQLException {
        Borrower borrower = null;
        Borrower insertedBorrower = null;
        Reservation reservation = null;

        BookView.listBooks(BookService.getBooksList());
        System.out.println("Choose which book you like to lent by providing its ID:");
        int id = scanner.nextInt();
        Book borrowedBook = BookService.getBook(id);

        while (borrowedBook == null){
            System.out.println("Not in the list! choose another ID:");
            id = scanner.nextInt();
            borrowedBook = BookService.getBook(id);
        }

        System.out.println("For how many Days you wish lent this book");
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


        System.out.println("Ok to complete the process you should : ");
        System.out.println("1-Choose an exist borrower");
        System.out.println("2-Add a new borrower ");
        int choice;
        do{
            System.out.println("choose your choice: ");
            choice = scanner.nextInt();
        }while (choice != 1 && choice != 2);
        scanner.nextLine();

        if(choice == 1){
            UserView.listBorrowers();
            do{
                System.out.println("Choose borrower by providing its ID: ");
                int borrowerId = scanner.nextInt();
                borrower = BorrowerService.getBorrower(borrowerId);
                reservation = new Reservation(borrower, borrowedBook);
            }while (borrower == null);
        }else{
            System.out.println("Enter the user credentials");
            System.out.println("Enter borrower's fullname: ");
            String fullname = scanner.nextLine();
            System.out.println("Enter borrower's email: ");
            String email = scanner.nextLine();
            System.out.println("Phone number:");
            String phoneNumber = scanner.nextLine();
            System.out.println("borrower's CIN");
            String CIN = scanner.nextLine();
            borrower = new Borrower(fullname, email, phoneNumber, CIN);
            insertedBorrower = BorrowerService.register(borrower);
            reservation = new Reservation(insertedBorrower, borrowedBook);
            if(insertedBorrower != null){
                System.out.println("User Created...!");
            }else {
                System.out.println("something is wrong with the user credentials");
            }

        }

//        reservation = new Reservation(insertedBorrower, borrowedBook);

        if(ReservationService.addReservation(reservation, borrowingDays)){
            System.out.println("You lent the book successfully!");
        } else{
            System.out.println("Something is wrong with credentials, try again");
        }
    }

    public static void returnBook() throws SQLException {
        listReservations();
        System.out.println("Enter the ID of reservation you wish to cancel: ");
        int id = scanner.nextInt();

        while(id == 0){
            System.out.println("Doesn't exist enter an exist ID");
            id = scanner.nextInt();
        }

        ArrayList<Reservation> reservations = ReservationService.getReservations(id);

        while(reservations.isEmpty()){
            System.out.println("Doesn't exist enter an exist ID");
            id = scanner.nextInt();
        }

        if(ReservationService.returnBook(reservations.get(0))){
            System.out.println("Canceled successfully!");
        }else{
            System.out.println("You can't cancel an expired reservation!");
        }
    }

    public static void listReservations() throws SQLException {
        ArrayList<Reservation> reservations = ReservationService.getReservations(0);

        System.out.println("+----+----------------------+---------------+--------------------------------+---------------+---------------+--------------------------------+---------------+---------------+------------+");
        System.out.println("| ID |      Full Name      |      CIN      |             Email              |   Telephone   |      ISBN     |            Title               | Borrowing Date|  Return Date  |   Status   |");
        System.out.println("+----+----------------------+---------------+--------------------------------+---------------+---------------+--------------------------------+---------------+---------------+------------+");

        for (Reservation reservation : reservations) {
            System.out.printf("| %-2d | %-20s | %-13s | %-30s | %-13s | %-13s | %-30s | %-13s | %-13s | %-10s |%n",
                    reservation.getId(),
                    reservation.getborrower().getFullname(),
                    reservation.getborrower().getCIN(),
                    reservation.getborrower().getEmail(),
                    reservation.getborrower().getTelephone(),
                    reservation.getbook().getIsbn(),
                    reservation.getbook().getTitle(),
                    reservation.getBorrowingDate(),
                    reservation.getReturnDate(),
                    reservation.getStatus());
            System.out.println("+----+----------------------+---------------+--------------------------------+---------------+---------------+--------------------------------+---------------+---------------+------------+");
        }

    }
}
