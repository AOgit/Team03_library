package repository;

import model.Book;
import utils.MyArrayList;
import utils.MyList;

import java.util.concurrent.atomic.AtomicInteger;

public class BookRepositoryImpl implements BookRepository {

    // Все книги будут храниться в памяти нашего приложения
    MyList<Book> books;

    // Объект, отвечающий за генерацию уникальных id
    private final AtomicInteger currentId = new AtomicInteger(1);

    private void addStartBooks() {

        this.books.addAll(
                new Book(currentId.getAndIncrement(), "Война и мир", "Толстой Л.Н.", 1983, 5876),
                new Book(currentId.getAndIncrement(), "Зачарована Десна", "Довженко О.", 1983, 576),
                new Book(currentId.getAndIncrement(), "Три товарища", "Ремарк", 1936, 480),
                new Book(currentId.getAndIncrement(), "Вечера на хуторе близ Диканьки", "Гоголь Н.В.", 1961, 350)
        );
    }

    public BookRepositoryImpl() {
        this.books = new MyArrayList<>();
        addStartBooks();
    }

    @Override
    public Book addBook(String title, String author, int year, int pages) {
        Book book = new Book(currentId.getAndIncrement(), title, author, year, pages);
        this.books.add(book);
        return book;
    }

    @Override
    public MyList<Book> getAllBooks() {
        return this.books;
    }

    @Override
    public Book getBookById(int id) {
        for (Book book: this.books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    @Override
    public MyList<Book> getAvailableBooks() {
        MyList<Book> availableBooks = new MyArrayList<>();
        for (Book book: this.books) {
            if (!book.isBusy()) availableBooks.add(book);
        }
        return availableBooks;
    }

    @Override
    public MyList<Book> getBorrowedBooks() {
        MyList<Book> borrowedBooks = new MyArrayList<>();
        for (Book book: this.books) {
            if (book.isBusy()) borrowedBooks.add(book);
        }
        return borrowedBooks;
    }

    @Override
    public MyList<Book> getBooksByTitle(String title) {
        MyList<Book> passedBooks = new MyArrayList<>();
        for (Book book: this.books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) passedBooks.add(book);
        }
        return passedBooks;
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        MyList<Book> passedBooks = new MyArrayList<>();
        for (Book book: this.books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) passedBooks.add(book);
        }
        return passedBooks;
    }

    @Override
    public void saveBook(Book book) {
        for (Book bk: this.books) {
            if (book.getId() == bk.getId()){
                bk = book;
                return;
            }
        }
        books.add(book);
    }

    @Override
    public void deleteById(int id) {
        for (Book book: this.books) {
            if(book.getId() == id) {
              books.remove(book);
              return;
            }
        }
    }
}
