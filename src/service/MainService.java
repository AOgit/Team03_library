package service;

import model.Book;
import utils.MyList;

public interface MainService {

    boolean borrowBook(int bookId);

    boolean returnBook(int bookId);

    void editBook(Book book);

    MyList<Book> getBooksByTitle(String title);

    // получить список книг по автору
    MyList<Book> getBooksByAuthor(String author);

    MyList<Book> getAllBooks();

    MyList<Book> getAvailableBooks();

    MyList<Book> getBorrowedBooks();

    Book addBook (String title, String author, int year, int pages);




}
