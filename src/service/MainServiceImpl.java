package service;

import model.Book;
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
        if (user == null) return false;

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
    public boolean borrowBook(int bookId) {
        Book book =  bookRepository.getBookById(bookId);
        if (book == null || book.isBorrowed()) return false;
        book.setIsBorrowed(true);
        return true;
    }

    @Override
    public boolean returnBook(int bookId) {
        Book book =  bookRepository.getBookById(bookId);
        if (book == null || !book.isBorrowed()) return false;
        book.setIsBorrowed(false);
        return true;
    }

    @Override
    public MyList<Book> getBooksByTitle(String title) {
        return bookRepository.getBooksByTitle(title);
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        return bookRepository.getBooksByAuthor(author);
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
    public Book addBook(String title, String author, int year, int pages) {
        return bookRepository.addBook(title, author, year, pages);
    }


    @Override
    public boolean editBook(Book book, String title, String author, int year, int pages) {
        if (book == null) return false;

        if (title != null) book.setTitle(title);
        if (author != null) book.setAuthor(author);
        if (year > 0) book.setYear(year);
        if (pages > 0) book.setPages(pages);
        return bookRepository.updateBook(book);
    }

}
