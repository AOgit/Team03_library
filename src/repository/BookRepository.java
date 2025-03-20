package repository;

import model.Book;
import utils.MyList;

public interface BookRepository {

    Book addBook(String title, String author, int year, int pages);

    // получить список всех книг
    MyList<Book> getAllBooks();

    // получение сущности по id
    Book getBookById(int id);

    // Получить список только свободных книг
    MyList<Book> getAvailableBooks();

    // Получить список всех занятых книг
    MyList<Book> getBorrowedBooks();

    // получить список книг по названию
    MyList<Book> getBooksByTitle(String title);

    // получить список книг по автору
    MyList<Book> getBooksByAuthor(String author);

    // Update
    // Сохранить обновленный объект
    void saveBook(Book book);

    // Delete
    void deleteById(int id);





}
