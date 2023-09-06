public class BorrowedBook {
    private int borrowerId;
    private int bookId;
    private String borrowingDate;
    private String returnDate;
    private String status;

    public BorrowedBook(int borrowerId, int bookId, String borrowingDate, String returnDate, String status) {
        this.borrowerId = borrowerId;
        this.bookId = bookId;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
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
