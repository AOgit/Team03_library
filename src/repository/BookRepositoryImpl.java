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
    MyList<Book> books = new MyArrayList<>();

    // Объект, отвечающий за генерацию уникальных id
    private final AtomicInteger currentId = new AtomicInteger(1);

//    public BookRepositoryImpl(MyList<Book> books) {
//        this.books = books;
//        addStartBooks():
//    }

    private void addStartBooks() {

        books.addAll(
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
        int id = currentId.getAndIncrement();
        Book newBook = new Book(id, title, author, year, pages, genre);
        books.add(newBook);
        return newBook;
    }

    @Override
    public MyList<Book> getAllBooks() {
        return books;
    }

    @Override
    public Book getBookById(int id) {
        if (books == null) {
            System.out.println("Нет книг для поиска");
            return null;
        }
        for (Book book : books) {
            if (Objects.equals(book.getId(), id)) {
                return book;
            }
        }
        System.out.println("Книга не найдена");
        return null;
    }

    @Override
    public MyList<Book> getAvailableBooks() {
        MyList<Book> availableBooks = new MyArrayList<>();
        for (Book book : books) {
            if (!book.isBorrowed()) {
                availableBooks.add(book);
            }
            return availableBooks;
        }
        return null;
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
    public MyList<Book> getBooksByTitle(String title) {
        if (title == null) {
            System.out.println("Книга не найдена");
            return new MyArrayList<>();
        }
        MyList<Book> matchingBooks = new MyArrayList<>();
        for (Book book : books) {
            if (book.getTitle().contains(title)) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        if (author == null) {
            System.out.println("Автор не найден");
            return new MyArrayList<>();
        }
        MyList<Book> matchingAuthorBooks = new MyArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().contains(author)) {
                matchingAuthorBooks.add(book);
            }
        }
        return matchingAuthorBooks;

    }

    public MyList<Book> getBookByGenre (String genre) {
        if (genre == null) {
            System.out.println("Жанр не найден");
            return new MyArrayList<>();
        }
        MyList<Book> matchingBooksByGenre = new MyArrayList<>();
        for (Book book : books) {
            if (book.getGenre().contains(genre)) {
                matchingBooksByGenre.add(book);
            }
        }
        return matchingBooksByGenre;
    }


    @Override
    public void saveBook(Book book) {
        if (book == null) {
            System.out.println("Ошибка: неизвестная книга.");
            return;
        }
        for (int i = 0; i < books.size(); i++) {
            Book bk = books.get(i);
            if (bk.getId() == book.getId()) {
                books.set(i, book); // Обновляем книгу по индексу
                return;
            }
        }
        books.add(book);
    }

    @Override
    public void deleteById(int id) {

        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next().getId(), id)) {
                iterator.remove();
                return;
            }
        }
//        for (int i = 0; i < books.size(); i++) {
//            if (Objects.equals(books.get(i).getId(), id)) {
//                books.remove(i);
//                i--;
//           }
//      }
    }

    public void getCurrentReader(int id) {
        for (Book book : books) {
            if (Objects.equals(book.getId(), id)) {
                if (book.isBorrowed() && book.getReader() != null) {
                    User reader = book.getReader();
                    System.out.printf("Книга: %s (%d); читатель: %s\n", book.getTitle(), book.getId(), reader.getEmail());
                } else {
                    System.out.printf("Книга: %s (%d) доступна\n", book.getTitle(), book.getId());
                }
                return;
            }
        }
        System.out.println("Книга не найдена");
    }
}




