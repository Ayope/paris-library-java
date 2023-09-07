package model;

public class Reservation {
    private Borrower borrower;
    private Book book;
    private String borrowingDate;
    private String returnDate;
    private String status;

    public Reservation(Borrower borrower, Book book, String borrowingDate, String returnDate, String status) {
        this.borrower = borrower;
        this.book = book;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.status = status;
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

    public String getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(String borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
