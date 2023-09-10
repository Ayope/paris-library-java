package view;

import model.Book;
import repository.BookRepo;
import service.BookService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookView {
    private static Scanner scanner = new Scanner(System.in);
    public static void showBookManagementView() throws SQLException {
        System.out.println("----------------Book Management----------------");
        System.out.println("1-Add Book");
        System.out.println("2-Update Book");
        System.out.println("3-DeleteBook");
        System.out.println("4-List Books");
        System.out.println("5-Search a book");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice){
            case 1:
                AddBook();
                break;
            case 2:
                updateBook();
                break;
            case 3:
                deleteBook();
                break;
            case 4:
                listBooks(BookService.getBooksList());
                break;
            case 5:
                searchBooks();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    public static void AddBook() throws SQLException {
        System.out.println("----------------Add Book----------------");

        System.out.println("Insert isbn :");
        String isbn = scanner.nextLine();

        System.out.println("Insert title :");
        String title = scanner.nextLine();

        System.out.println("Insert author fullname:");
        String author = scanner.nextLine();

        System.out.println("Insert quantity of the book:");
        int quantity = scanner.nextInt();

        Book InsertedBook = new Book(isbn, title, quantity, author);

        if(BookService.save(InsertedBook, "insert")){
            System.out.println("Successfully Added");
        }else{
            System.out.println("Something went wrong! Try again");
        }
    }

    public static void listBooks(ArrayList<Book> books) throws SQLException {

        if (!books.isEmpty()) {
            System.out.println("----------------------------------------Books List-----------------------------------------------");
            System.out.printf("| %-5s | %-13s | %-30s | %-8s | %-20s |\n",
                    "ID", "ISBN", "Title", "Quantity", "Author");
            System.out.println("-------------------------------------------------------------------------------------------------");

            for (Book book : books) {
                System.out.printf("| %-5d | %-13s | %-30s | %-8d | %-20s |\n",
                        book.getId(), book.getIsbn(), book.getTitle(), book.getQuantity(), book.getAuthor());
            }
            System.out.println("-------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No books found.");
        }
    }

    public static void listBook(int id) throws SQLException{
        Book book = BookService.getBook(id);

        if (book != null) {
            System.out.println("----------------------------------------Books List-----------------------------------------------");
            System.out.printf("| %-5s | %-13s | %-30s | %-8s | %-20s |\n",
                    "ID", "ISBN", "Title", "Quantity", "Author");
            System.out.println("-------------------------------------------------------------------------------------------------");

            System.out.printf("| %-5d | %-13s | %-30s | %-8d | %-20s |\n",
                    book.getId(), book.getIsbn(), book.getTitle(), book.getQuantity(), book.getAuthor());

            System.out.println("-------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("Book not found.");
        }
    }

    public static void updateBook() throws SQLException {
        ArrayList<Book> books = BookService.getBooksList();
        listBooks(books);
        System.out.println("Enter the ID of the book you wish to update");
        int id = scanner.nextInt();
        Book choosedBook = BookService.getBook(id);
        listBook(id);
        scanner.nextLine();

        System.out.println("----------------Update Book----------------");

        System.out.println("Update isbn : (other way just click Enter)");
        String newIsbn = scanner.nextLine();

        System.out.println("Update title : (other way just click Enter)");
        String newTitle = scanner.nextLine();

        System.out.println("Update author fullname: (other way just click Enter)");
        String newAuthor = scanner.nextLine();

        System.out.println("Update quantity of the book: (other way just click Enter)");
        String quantityInput = scanner.nextLine();
        int newQuantity = choosedBook.getQuantity();

        if(!quantityInput.isEmpty()){
            try{
                newQuantity = Integer.parseInt(quantityInput);
            }catch (NumberFormatException e){
                System.out.println("Invalid quantity input. Keeping current value.");
            }
        }

        Book UpdatedBook = new Book(
                choosedBook.getId(),
                (newIsbn.isEmpty()) ? choosedBook.getIsbn() : newIsbn,
                (newTitle.isEmpty()) ? choosedBook.getTitle() : newTitle,
                newQuantity,
                (newAuthor.isEmpty()) ? choosedBook.getAuthor() : newAuthor);

        if(BookService.save(UpdatedBook, "update")){
            System.out.println("Successfully updated");
        }
    }

    public static void deleteBook() throws SQLException {
        ArrayList<Book> books = BookService.getBooksList();
        listBooks(books);

        System.out.println("Enter the ID of the book you wish to Delete");
        int id = scanner.nextInt();

        Book choosedBook = BookService.getBook(id);
        listBook(id);

        scanner.nextLine();

        System.out.println("Confirm deletion of the book: ");
        System.out.println("1-Confirm");
        System.out.println("2-Cancel");
        int choice = scanner.nextInt();

        if (choice == 1) {
            if (BookService.deleteBook(choosedBook.getId())) {
                System.out.println("Your book has been deleted successfully !");
            }
        } else {
            System.out.println("Ok your book is still safe in the library ;) ");
        }
    }

    public static void searchBooks() throws SQLException{
        System.out.println("----------------------------------------Search Book-----------------------------------------------");
        System.out.println("You can search a book by its title, author");
        System.out.println("Enter your search word: ");
        String searchWord = scanner.nextLine();
        if(!searchWord.isEmpty()){
            ArrayList<Book> resultBooks = BookService.searchBooks(searchWord);
            listBooks(resultBooks);
        }else {
            System.out.println("ok, good day!");
        }

    }
}
