package repository;

import model.Book;
import model.User;
import utils.MyArrayList;
import utils.MyList;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class BookRepositoryImpl implements BookRepository {

    // Все книги будут храниться в памяти нашего приложения
    private final MyList<Book> books;

    // Объект, отвечающий за генерацию уникальных id
    private final AtomicInteger currentId = new AtomicInteger(1);

    public BookRepositoryImpl() {
        this.books = new MyArrayList<>();
        addStartBooks();
    }

    private void addStartBooks() {

        books.addAll(
                new Book(currentId.getAndIncrement(), "Java для чайников", "Берд Б.", 2013, 368, "сказка"),
                new Book(currentId.getAndIncrement(), "Война и мир", "Толстой Л.Н.", 1983, 5876, "роман"),
                new Book(currentId.getAndIncrement(), "Зачарована Десна", "Довженко О.", 1983, 576, "автобиографическая повесть"),
                new Book(currentId.getAndIncrement(), "Грозовой перевал", "Бронте Э.", 1847, 384, "роман"),
                new Book(currentId.getAndIncrement(), "Сто лет одиночества", "Маркес Г.Г.", 1967, 480, "роман"),
                new Book(currentId.getAndIncrement(), "Три товарища", "Ремарк Э.М.", 1936, 480, "роман"),
                new Book(currentId.getAndIncrement(), "Повелитель мух", "Голдинг У.", 1954, 320, "роман-антиутопия")

        );
    }

    @Override
    public Book addBook(String title, String author, int year, int pages, String genre) {
        Book newBook = new Book(currentId.getAndIncrement(), title, author, year, pages, genre);
        books.add(newBook);
        return newBook;
    }

    @Override
    public MyList<Book> getAllBooks() {
        return books;
    }

    @Override
    public Book getBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    @Override
    public MyList<Book> getAvailableBooks() {
        MyList<Book> availableBooks = new MyArrayList<>();
        for (Book book : books) {
            if (!book.isBorrowed()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    @Override
    public MyList<Book> getBorrowedBooks() {
        MyList<Book> borrowedBooks = new MyArrayList<>();
        for (Book book : books) {
            if (book.isBorrowed()) {
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }

    @Override
    public MyList<Book> findBooksByTitleOrAuthor(String search) {
        MyList<Book> matchingBooks = new MyArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(search.toLowerCase())
                || book.getAuthor().toLowerCase().contains(search.toLowerCase()))
                matchingBooks.add(book);
        }
        return matchingBooks;
    }

    @Override
    public MyList<Book> getBooksByTitle(String title) {
        MyList<Book> matchingBooks = new MyArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) matchingBooks.add(book);
        }
        return matchingBooks;
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        MyList<Book> matchingAuthorBooks = new MyArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) matchingAuthorBooks.add(book);
        }
        return matchingAuthorBooks;
    }
    @Override
    public MyList<Book> getBookByGenre (String genre) {
        MyList<Book> matchingBooksByGenre = new MyArrayList<>();
        for (Book book : books) {
            if (book.getGenre().toLowerCase().contains(genre.toLowerCase())) matchingBooksByGenre.add(book);
        }
        return matchingBooksByGenre;
    }

    @Override
    public boolean updateBook(Book book) {
        for (int i = 0; i < books.size(); i++) {
            Book bk = books.get(i);
            if (bk.getId() == book.getId()) {
                books.set(i, book); // Обновляем книгу по индексу
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        // Ок, через метод итератор так через итератор
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public String getCurrentReader(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                if (book.isBorrowed() && book.getReader() != null) {
                    return book.getReader().getEmail();
     //               System.out.printf("Книга: %s (%d); читатель: %s\n", book.getTitle(), book.getId(), reader.getEmail());
                } else {
                    return null;
    //                System.out.printf("Книга: %s (%d) доступна\n", book.getTitle(), book.getId());
                }
            }
        }
//        System.out.println("Книга не найдена");
        return null;
    }
}




