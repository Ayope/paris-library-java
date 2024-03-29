package model;

import java.util.ArrayList;
import java.util.Objects;

public class Book {
    private int id;
    private String isbn;
    private String title;
    private int quantity;
    private String author;
    private ArrayList<Borrower> borrowers;

    public Book(int id, String isbn, String title, int quantity, String author) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.quantity = quantity;
        this.author = author;
    }

    public Book(String isbn, String title, int quantity, String author) {
        this.isbn = isbn;
        this.title = title;
        this.quantity = quantity;
        this.author = author;
    }

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", quantity='" + quantity + '\'' +
                ", author=" + author +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title) && Objects.equals(quantity, book.quantity) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, title, quantity, author);
    }
}
