package service;

import model.Book;
import model.Role;
import model.User;
import utils.MyList;

public interface MainService {

    // ==================USERS========================
    User registerUser(String email, String password);

    boolean loginUser(String email, String password);

    void logout();

    User getActiveUser();

    public  boolean isLoggedIn();

    public boolean isAdmin();

    public boolean isSuperAdmin();

    // ==================USERS========================
    // ==================BOOKS========================
    Book addBook (String title, String author, int year, int pages, String genre);

    boolean editBook(int id, String title, String author, int year, int pages);

    boolean borrowBook(int bookId);

    boolean returnBook(int bookId);

    MyList<Book> getBooksByTitle(String title);

    MyList<Book> getUserBooksByEmail(String email);

    MyList<Book> getBooksByAuthor(String author);

    MyList<Book> getAllBooks();

    MyList<Book> getAvailableBooks();

    MyList<Book> getBorrowedBooks();

    // ==================BOOKS========================
}
