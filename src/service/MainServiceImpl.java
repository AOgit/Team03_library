package service;

import model.Book;
import model.Role;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.MyList;
import utils.UserValidation;

public class MainServiceImpl implements MainService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private User activeUser;

    public MainServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }
  
    // ==================USERS========================

    @Override
    public User registerUser(String email, String password) {
        if (!UserValidation.isEmailValid(email)) {
            System.out.println("Email не прошел проверку!");
            return null;
        }

        if (!UserValidation.isPasswordValid(password)) {
            System.out.println("Пароль не прошел проверку");
            return null;
        }

        if (userRepository.getUserByEmail(email) != null) {
            System.out.println("Пользователь с таким email уже существует");
            return null;
        }

        User user = userRepository.addUser(email, password);
        return user;
    }

    @Override
    public boolean loginUser(String email, String password) {
        User user = userRepository.getUserByEmail(email);
        if (user == null || user.isBlocked()) return false;

        if (user.getPassword().equals(password)) {
            this.activeUser = user;
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        this.activeUser = null;
    }

    @Override
    public User getActiveUser() {
        return this.activeUser;
    }
  
    @Override
    public MyList<User> getAllUsers() {
        return null;
    }

    @Override
    public MyList<User> getAllReaders() {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public boolean blockUser(String email) {
        return false;
    }

    @Override
    public boolean unblockUser(String email) {
        return false;
    }

    @Override
    public MyList<Book> getUserBooks(String email) {
        return null;
    }

    @Override
    public boolean borrowBook(int bookId) {
        Book book =  bookRepository.getBookById(bookId);
        if (book == null || book.isBorrowed()) return false;
        book.setIsBorrowed(true);
        return bookRepository.updateBook(book);
    }
  
    @Override
    public boolean isLoggedIn(){
        return this.activeUser != null;
    }
  
    @Override
    public boolean isAdmin() {
        return isLoggedIn() && activeUser.getRole() == Role.ADMIN;
    }
  
    @Override
    public boolean isSuperAdmin() {
        return isLoggedIn() && activeUser.getRole() == Role.SUPER_ADMIN;
    }

    // ==================USERS========================
    // ==================BOOKS========================


    // Какие книги у пользователя


    @Override
    public MyList<Book> getBooksByTitle(String title) {
        return bookRepository.getBooksByTitle(title);
    }

    @Override
    public MyList<Book> getUserBooksByEmail(String email) {
        return null;
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        return bookRepository.getBooksByAuthor(author);
    }

    @Override
    public MyList<Book> getBookByGenre(String genre) {
        return bookRepository.getBookByGenre(genre);
    }

    @Override
    public MyList<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public MyList<Book> getAvailableBooks() {
        return bookRepository.getAvailableBooks();
    }

    @Override
    public MyList<Book> getBorrowedBooks() {
        return bookRepository.getBorrowedBooks();
    }

    @Override
    public Book getBookById(int bookId) {
        if (bookId < 0) return null;
        return bookRepository.getBookById(bookId);
    }

    @Override
    public boolean deleteBookById(int bookId) {
        if (bookId < 0) return false;
        return bookRepository.deleteById(bookId);
    }

    @Override
    public Book addBook(String title, String author, int year, int pages, String genre) {
        return bookRepository.addBook(title, author, year, pages, genre);
    }

    @Override
    public boolean editBook(int id, String title, String author, int year, int pages, String genre) {
        Book book = bookRepository.getBookById(id);
        if (book == null) return false;

        if (title != null) book.setTitle(title);
        if (author != null) book.setAuthor(author);
        if (genre != null) book.setGenre(genre);
        if (year > 0) book.setYear(year);
        if (pages > 0) book.setPages(pages);
        return bookRepository.updateBook(book);
    }

    @Override
    public boolean borrowBook(int bookId) {
        Book book =  bookRepository.getBookById(bookId);
        if (book == null || book.isBorrowed()) return false;
        book.setIsBorrowed(true);
        return bookRepository.updateBook(book);
    }

    @Override
    public boolean returnBook(int bookId) {
        Book book =  bookRepository.getBookById(bookId);
        if (book == null || !book.isBorrowed()) return false;
        book.setIsBorrowed(false);
        return bookRepository.updateBook(book);
    }

    // ==================BOOKS========================
}
