package service;

import model.Book;
import model.User;
import utils.MyList;

public interface MainService {

    User registerUser(String email, String password);

    boolean loginUser(String email, String password);

    void logout();

    User getActiveUser();

    // Может все-таки стоит вместо boolean isBusy, в модель Book добавить поле BorrowedBy??
    // С типом User?
    Book addBook (String title, String author, int year, int pages, String genre);

    boolean editBook(int id, String title, String author, int year, int pages);

    boolean borrowBook(int bookId);

    boolean returnBook(int bookId);

    MyList<Book> getBooksByTitle(String title);

    // получить список книг по автору
    MyList<Book> getBooksByAuthor(String author);

    MyList<Book> getBookByGenre(String genre);

    MyList<Book> getAllBooks();

    MyList<Book> getAvailableBooks();

    MyList<Book> getBorrowedBooks();

}
