package service;

import model.Book;
import repository.BookRepository;
import utils.MyList;

public class MainServiceImpl implements MainService {

    private final BookRepository bookRepository;

    public MainServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean borrowBook(int bookId) {
        Book book =  bookRepository.getBookById(bookId);
        if (book == null || book.isBusy()) return false;
        book.setBusy(true);
        return true;
    }

    @Override
    public boolean returnBook(int bookId) {
        Book book =  bookRepository.getBookById(bookId);
        if (book == null || !book.isBusy()) return false;
        book.setBusy(false);
        return true;
    }

    @Override
    public void editBook(Book book) {

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
}
