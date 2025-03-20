package repository;

import model.Book;
import utils.MyList;

import java.util.concurrent.atomic.AtomicInteger;

public class BookRepositoryImpl implements BookRepository {

    // Все книги будут храниться в памяти нашего приложения
    MyList<Book> books;

    // Объект, отвечающий за генерацию уникальных id
    private final AtomicInteger currenId = new AtomicInteger(1);

    private void addStartBooks() {

        books.addAll(
                new Book(currenId.getAndIncrement(), "Война и мир", "Толстой Л.Н.", 1983, 5876),
                new Book(currenId.getAndIncrement(), "Зачарована Десна", "Довженко О.", 1983, 576),
                new Book(currenId.getAndIncrement(), "Три товарища", "Ремарк", 1936, 480)
        );
    }

    @Override
    public Book addBook(String title, String author, int year, int pages) {
        return null;
    }

    @Override
    public MyList<Book> getAllBooks() {
        return null;
    }

    @Override
    public Book getBookById(int id) {
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
    public MyList<Book> getBooksByTitle(String title) {
        return null;
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        return null;
    }

    @Override
    public void saveBook(Book book) {

    }

    @Override
    public void deleteById(int id) {

    }
}
