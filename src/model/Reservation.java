package model;

import java.time.LocalDate;

public class Reservation {
    private Borrower borrower;
    private Book book;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private String status;

    public Reservation(Borrower borrower, Book book, LocalDate borrowingDate, LocalDate returnDate, String status) {
        this.borrower = borrower;
        this.book = book;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Reservation(Borrower borrower, Book book) {
        this.borrower = borrower;
        this.book = book;
    }

    public Borrower getborrower() {
        return borrower;
    }

    public void setborrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public Book getbook() {
        return book;
    }

    public void setbook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
