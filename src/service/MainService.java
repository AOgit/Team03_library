package service;

import model.Book;

public interface MainService {

    boolean borrowBook(int bookId);

    boolean returnBook(int bookId);

    void editBook(Book book);


}
