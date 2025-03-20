package service;

import model.Book;
import model.User;

public interface MainService {

    User registerUser(String email, String password);

    boolean loginUser(String email, String password);

    void logout();

    boolean borrowBook(int bookId);

    boolean returnBook(int bookId);

    void editBook(Book book);

    User getActiveUser();

}
