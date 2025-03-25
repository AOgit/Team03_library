package service;

import model.Book;
import model.Role;
import model.User;
import utils.MyList;

public interface MainService {

    // ==================USERS========================
  
    public  boolean isLoggedIn();

    public boolean isAdmin();

    public boolean isSuperAdmin();

    public boolean changeUserRole(User user, Role role);

    User registerUser(String email, String password);

    boolean loginUser(String email, String password);

    void logout();

    public boolean changePassword(String oldPassword, String newPassword);

    User getActiveUser();
  
    MyList<User> getAllUsers();

    MyList<User> getAllReaders();

    User getUserByEmail(String email);

    boolean blockUser(User user);

    boolean unblockUser(User user);

    boolean deleteUser(User user);

    MyList<Book> getUserBooks();
    MyList<Book> getUserBooks(User user);

    // ==================USERS========================
    // ==================BOOKS========================

    Book addBook (String title, String author, int year, int pages, String genre);

    boolean editBook(int id, String title, String author, int year, int pages, String genre);

    boolean borrowBook(int bookId);

    boolean borrowBook(User user, int bookId);

    boolean returnBook(int bookId);

    boolean returnBook(User user, int bookId);

    MyList<Book> getBooksByTitle(String title);

    MyList<Book> getUserBooksByEmail(String email);

    MyList<Book> getBooksByAuthor(String author);

    MyList<Book> getBookByGenre(String genre);

    MyList<Book> getAllBooks();

    MyList<Book> getAvailableBooks();

    MyList<Book> getBorrowedBooks();

    Book getBookById(int bookId);

    boolean deleteBookById(int bookId);

    boolean deleteBook(Book book);
   
  // ==================BOOKS========================

}
