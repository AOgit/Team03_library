package repository;

import model.Book;
import model.User;
import utils.MyList;

public interface BookRepository {

    Book addBook(String title, String author, int year, int pages, String genre);

    // получить список всех книг
    MyList<Book> getAllBooks();

    // получение сущности по id
    Book getBookById(int id);

    // Получить список только свободных книг
    MyList<Book> getAvailableBooks();

    // Получить список всех занятых книг
    MyList<Book> getBorrowedBooks();

    MyList<Book> findBooksByTitleOrAuthor(String search);

    // получить список книг по названию
    MyList<Book> getBooksByTitle(String title);

    // получить список книг по автору
    MyList<Book> getBooksByAuthor(String author);

    MyList<Book> getBookByGenre (String genre);

    // Update
    boolean updateBook(Book book);

    // получить читателя по книге
    String getCurrentReader(int id);

    // Delete
    boolean deleteBook(Book book);






}
