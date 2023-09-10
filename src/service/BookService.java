package service;

import model.Book;
import repository.BookRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookService {
    private static ResultSet result;

    public static boolean save(Book book, String method) throws SQLException {
        if(checkBookInputs(book)){
            if(Objects.equals(method, "insert")){
                return BookRepo.insert(book);
            }else{
                return BookRepo.update(book);
            }
        }else{
            return false;
        }
    }

    public static boolean checkBookInputs(Book book) throws SQLException {
        if(book.getQuantity() > 0){
            if(book.getIsbn().length() == 13){
                String regex = "^[0-9]{3}-[0-9]{9}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(book.getIsbn());
                return matcher.find();
            }
        }

        return false;
    }

    public static ArrayList<Book> getBooksList() throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        result = BookRepo.getBooksList();

        while (result.next()){
            books.add(new Book(
                    result.getInt("id"),
                    result.getString("isbn"),
                    result.getString("title"),
                    result.getInt("quantity"),
                    result.getString("author")
            ));
        }

        return books;
    }

    public static Book getBook(int id) throws SQLException {
        Book book = null;
        result = BookRepo.getBook(id);

        if(result.next()){
            book = new Book(
                result.getInt("id"),
                result.getString("isbn"),
                result.getString("title"),
                result.getInt("quantity"),
                result.getString("author")
            );
        }else {
            return null;
        }

        return book;
    }

    public static boolean deleteBook(int id) throws SQLException {
        return BookRepo.deleteBook(id);
    }

    public static ArrayList<Book> searchBooks(String searchWord) throws SQLException{
        ArrayList<Book> books = new ArrayList<>();
        result = BookRepo.searchBooks(searchWord);

        while (result.next()){
            books.add(new Book(
                    result.getInt("id"),
                    result.getString("isbn"),
                    result.getString("title"),
                    result.getInt("quantity"),
                    result.getString("author")
            ));
        }

        return books;
    }
}
