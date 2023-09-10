package service;

import model.Book;
import model.Borrower;
import model.Reservation;
import model.User;
import repository.ReservationRepo;

import java.sql.SQLException;
import java.time.LocalDate;
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

        ReservationRepo.addReservation(finalReservation);

        return false;
    }
}
