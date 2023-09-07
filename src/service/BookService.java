package service;

import model.Book;
import repository.BookRepo;

import java.sql.SQLException;

public class BookService {
    public static boolean checkBookInputs(Book book) throws SQLException {
//        if(book.getIsbn().length() == 13){
//        }

        if(BookRepo.insert(book)){
            return true;
        }
        return false;
    }
}
