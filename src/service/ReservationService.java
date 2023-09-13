package service;

import model.Book;
import model.Borrower;
import model.Reservation;
import model.User;
import repository.BookRepo;
import repository.ReservationRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ReservationService {
    public static boolean addReservation(Reservation reservation, int borrowingDays) throws SQLException {
        LocalDate borrowingDate = LocalDate.now();
        LocalDate returnDate = borrowingDate.plusDays(borrowingDays);
        int borrowerId = reservation.getborrower().getId();
        int bookId = reservation.getbook().getId();

        Reservation finalReservation = new Reservation(
                reservation.getborrower(),
                reservation.getbook(),
                borrowingDate,
                returnDate,
                "emprunté");

        return ReservationRepo.addReservation(finalReservation);
    }

    public static ArrayList<Reservation> getReservations(int id) throws SQLException{
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        ResultSet results;
        if(id != 0){
            results = ReservationRepo.getBorrowerReservations(id);
        }else{
            results = ReservationRepo.getReservations();
        }
        Borrower borrower = null;
        Book book = null;

        while(results.next()){
            borrower = new Borrower(
                    results.getString("fullname"),
                    results.getString("CIN"),
                    results.getString("email"),
                    results.getString("telephone")
            );
            book = new Book(
                    results.getString("isbn"),
                    results.getString("title")
            );

            reservations.add(new Reservation(
                results.getInt("id"),
                borrower,
                book,
                results.getDate("borrowing_date").toLocalDate(),
                results.getDate("return_date").toLocalDate(),
                results.getString("status")
            ));
        }

        return reservations;
    }

    public static boolean returnBook(Reservation reservation) throws SQLException {
        return ReservationRepo.returnBook(reservation.getId());
    }
}
