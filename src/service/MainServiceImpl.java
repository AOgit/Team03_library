package service;

import model.Book;
import model.User;
import utils.MyList;

public class MainServiceImpl implements MainService {
    @Override
    public User registerUser(String email, String password) {
        return null;
    }

    @Override
    public boolean loginUser(String email, String password) {
        return false;
    }

    @Override
    public void logout() {

    }

    @Override
    public boolean borrowBook(int bookId) {
        return false;
    }

    @Override
    public boolean returnBook(int bookId) {
        return false;
    }

    @Override
    public void editBook(String title, String author, int year, int pages) {

    }

    @Override
    public MyList<Book> getBooksByTitle(String title) {
        return null;
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        return null;
    }

    @Override
    public MyList<Book> getAllBooks() {
        return null;
    }

    @Override
    public MyList<Book> getAvailableBooks() {
        return null;
    }

    @Override
    public MyList<Book> getBorrowedBooks() {
        return null;
    }


    @Override
    public User getActiveUser() {
        return null;
    }

    @Override
    public Book addBook(String title, String author, int year, int pages) {
        return null;
    }
}
