public class Book {
    private int id;
    private String isbn;
    private String title;
    private String quantity;
    private Author author;

    public Book(int id, String isbn, String title, String quantity) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
