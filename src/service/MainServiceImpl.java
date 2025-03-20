package service;

import model.Book;
import model.User;

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
    public void editBook(Book book) {

    }

    @Override
    public User getActiveUser() {
        return null;
    }
}
