package model;

import java.util.ArrayList;

public class Borrower extends User{
    private String telephone;
    private String CIN;
    private ArrayList<Book> books;

    public Borrower(int id, String telephone, String CIN) {
        super(id);
        this.telephone = telephone;
        this.CIN = CIN;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

}
