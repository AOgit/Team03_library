package repository;

import model.Book;
import utils.MyArrayList;
import utils.MyList;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class BookRepositoryImpl implements BookRepository {

    // Все книги будут храниться в памяти нашего приложения
    MyList<Book> books;

    // Объект, отвечающий за генерацию уникальных id
    private final AtomicInteger currentId = new AtomicInteger(1);

//    public BookRepositoryImpl(MyList<Book> books) {
//        this.books = books;
//    }

    private void addStartBooks() {

        books.addAll(
                new Book(currentId.getAndIncrement(), "Война и мир", "Толстой Л.Н.", 1983, 5876),
                new Book(currentId.getAndIncrement(), "Зачарована Десна", "Довженко О.", 1983, 576),
                new Book(currentId.getAndIncrement(), "Грозовой перевал", "Бронте Э.", 1847, 384),
                new Book(currentId.getAndIncrement(), "Сто лет одиночества", "Маркес Г.Г.", 	1967, 480),
                new Book(currentId.getAndIncrement(), "Три товарища", "Ремарк Э.М.", 1936, 480),
                new Book(currentId.getAndIncrement(), "Повелитель мух", "Голдинг У.", 1954, 320)

        );
    }

    @Override
    public Book addBook(String title, String author, int year, int pages) {
        int id = currentId.getAndIncrement();
        Book newBook = new Book(id, title, author, year, pages);
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
            if(Objects.equals(book.getId(), id)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public MyList<Book> getAvailableBooks() {
        MyList<Book> availableBooks = new MyArrayList<>();
        for (Book book : books) {
        if (book.isBorrowed()) {
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
            if (!book.isBorrowed()) {
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }

    @Override
    public MyList<Book> getBooksByTitle(String title) {
        MyList<Book> matchingBooks = new MyArrayList<>();
        for(Book book : books) {
            if(book.getTitle().contains(title)) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }
// TODO добавить инфо о читателе
    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        MyList<Book> matchingAuthorBooks = new MyArrayList<>();
        for (Book book : books) {
            if(book.getAuthor().contains(author)) {
                matchingAuthorBooks.add(book);
            }
        }
        return matchingAuthorBooks;

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
            if(Objects.equals(iterator.next().getId(), id)) {
                iterator.remove();
            }
        }
//        for (int i = 0; i < books.size(); i++) {
//            if (Objects.equals(books.get(i).getId(), id)) {
//                books.remove(i);
//                i--;
//           }
//      }
    }
}
