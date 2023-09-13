package model;

import java.util.ArrayList;

public class Borrower extends User{
    private String telephone;
    private String CIN;
    private ArrayList<Book> books;

    public Borrower(String fullname, String email, String telephone,  String cin) {
        super(fullname, email, "borrower");
        this.CIN = cin;
        this.telephone = telephone;
    }

    public Borrower(int id, String fullname, String cin, String email, String telephone) {
        super(id, fullname, email);
        this.CIN = cin;
        this.telephone = telephone;
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
