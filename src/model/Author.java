//package model;
//
//import java.util.ArrayList;
//import java.util.Objects;
//
//public class Author {
//    private int id;
//    private String fullname;
//    private String email;
//    private ArrayList<Book> books;
//
//    public Author(String fullname, String email) {
//        this.fullname = fullname;
//        this.email = email;
//    }
//
//    public String getFullname() {
//        return fullname;
//    }
//
//    public void setFullname(String fullname) {
//        this.fullname = fullname;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Author author = (Author) o;
//        return id == author.id && Objects.equals(fullname, author.fullname) && Objects.equals(email, author.email) && Objects.equals(books, author.books);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, fullname, email, books);
//    }
//
//    @Override
//    public String toString() {
//        return "Author{" +
//                "id=" + id +
//                "fullname='" + fullname + '\'' +
//                ", email='" + email + '\'' +
//                ", books=" + books +
//                '}';
//    }
//}
