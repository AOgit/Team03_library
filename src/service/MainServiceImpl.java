package service;

import model.Book;
import model.Role;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.MyArrayList;
import utils.MyList;
import utils.StringHashing;
import utils.UserValidation;

public class MainServiceImpl implements MainService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private User activeUser;

    public MainServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;

        // для демонстрации добавим книг одному из юзеров
          User reader = userRepository.getUserByEmail("reader@mail.de");
          borrowBook(reader, 1);
          borrowBook(reader, 3);
    }
  
    // ==================USERS========================

    @Override
    public User registerUser(String email, String password) {
        if (!UserValidation.isEmailValid(email)) {
            System.out.println("Email не прошел проверку!");
            return null;
        }

        if (!UserValidation.isPasswordValid(password)) {
            System.out.println("Пароль не прошел проверку");
            return null;
        }

        if (userRepository.getUserByEmail(email) != null) {
            System.out.println("Пользователь с таким email уже существует");
            return null;
        }

        String hashPassword = StringHashing.hashPassword(password);
        User user = userRepository.addUser(email, hashPassword);

        // делаем автологин после успешной регистрации
        loginUser(email, password);

        return user;
    }

    @Override
    public boolean loginUser(String email, String password) {
        User user = userRepository.getUserByEmail(email);
        if (user == null || user.isBlocked()) return false;

        String hashPassword = StringHashing.hashPassword(password);

        if (user.getPassword().equals(hashPassword)) {
            this.activeUser = user;
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        this.activeUser = null;
    }

    @Override
    public User getActiveUser() {
        return this.activeUser;
    }
  
    @Override
    public MyList<User> getAllUsers() {
        if (!isAdmin() && !isSuperAdmin()) return null;
        return userRepository.getAllUsers();
    }

    @Override
    public MyList<User> getAllReaders() {
        if (!isAdmin() && !isSuperAdmin()) return null;
        return userRepository.getAllReaders();
    }

    @Override
    public User getUserByEmail(String email) {
        if (!isAdmin() && !isSuperAdmin()) return null;
        return userRepository.getUserByEmail(email);
    }

    @Override
    public boolean blockUser(User user) {
        Role role = user.getRole();
        if (!isAdmin() && !isSuperAdmin() || role == Role.SUPER_ADMIN) return false;
        if (isAdmin() && role != Role.USER ) {
            System.out.println("Не хватает прав для блокировки!");
            return false;
        }
        user.setBlocked(true);
        return userRepository.update(user);
    }

    @Override
    public boolean unblockUser(User user) {
        Role role = user.getRole();
        if (!isAdmin() && !isSuperAdmin()) return false;
        if (isAdmin() && role != Role.USER ) {
            System.out.println("Не хватает прав для разблокировки!");
            return false;
        }
        user.setBlocked(false);
        return userRepository.update(user);
    }

    @Override
    public MyList<Book> getUserBooks() {
        User user = getActiveUser();
        return getUserBooks(user);
    }

    @Override
    public MyList<Book> getUserBooks(User user) {
       if (user == null)  return new MyArrayList<>();
       return user.getUserBooks();
    }


    @Override
    public boolean isLoggedIn(){
        return this.activeUser != null;
    }

    @Override
    public boolean isAdmin() {
        return isLoggedIn() && activeUser.getRole() == Role.ADMIN;
    }
  
    @Override
    public boolean isSuperAdmin() {
        return isLoggedIn() && activeUser.getRole() == Role.SUPER_ADMIN;
    }

    public boolean changeUserRole(User user, Role role) {
        if (!isSuperAdmin() || user.getRole() == Role.SUPER_ADMIN) return false;
        if (role == Role.SUPER_ADMIN) {
            System.out.println("Супер администратор может быть в системе только один");
            return false;
        }
        user.setRole(role);
        return userRepository.update(user);
    }

    public boolean deleteUser(User user) {
        Role role = user.getRole();
        if (!isAdmin() && !isSuperAdmin() || role == Role.SUPER_ADMIN) return false;
        if (isAdmin() && role != Role.USER ) {
            System.out.println("Администраторы могут удалять только простых пользователей");
            return false;
        }
        return userRepository.delete(user);
    }

    // ==================USERS========================
    // ==================BOOKS========================



    @Override
    public MyList<Book> getBooksByTitle(String title) {
        return bookRepository.getBooksByTitle(title);
    }

    @Override
    public MyList<Book> getUserBooksByEmail(String email) {
        if (getUserByEmail(email) != null) {
            return getUserByEmail(email).getUserBooks();
        }
        return null;
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        return bookRepository.getBooksByAuthor(author);
    }

    @Override
    public MyList<Book> getBookByGenre(String genre) {
        return bookRepository.getBookByGenre(genre);
    }

    @Override
    public MyList<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public MyList<Book> getAvailableBooks() {
        return bookRepository.getAvailableBooks();
    }

    @Override
    public MyList<Book> getBorrowedBooks() {
        return bookRepository.getBorrowedBooks();
    }

    @Override
    public Book getBookById(int bookId) {
        if (bookId < 0) return null;
        return bookRepository.getBookById(bookId);
    }

    @Override
    public boolean deleteBookById(int bookId) {
        if (!isAdmin() && !isSuperAdmin()) return false;
        if (bookId < 0) return false;
        Book book = bookRepository.getBookById(bookId);

        if (book.isBorrowed()) {
            System.out.println("Книга находится у читателя!");
            return false;
        }
        return deleteBook(book);
    }

    @Override
    public boolean deleteBook(Book book) {
        if (!isAdmin() && !isSuperAdmin()) return false;
        return  bookRepository.deleteBook(book);
    }

    @Override
    public Book addBook(String title, String author, int year, int pages, String genre) {
        if (!isAdmin() && !isSuperAdmin()) return null;
        return bookRepository.addBook(title, author, year, pages, genre);
    }

    @Override
    public boolean editBook(int id, String title, String author, int year, int pages, String genre) {
        if (!isAdmin() && !isSuperAdmin()) return false;
        Book book = bookRepository.getBookById(id);
        if (book == null) return false;

        if (title != null) book.setTitle(title);
        if (author != null) book.setAuthor(author);
        if (genre != null) book.setGenre(genre);
        if (year > 0) book.setYear(year);
        if (pages > 0) book.setPages(pages);
        return bookRepository.updateBook(book);
    }

    @Override
    public boolean borrowBook(int bookId) {
        User user = this.getActiveUser();
        return borrowBook(user, bookId);
    }

    @Override
    public boolean borrowBook(User user, int bookId) {
        Book book =  bookRepository.getBookById(bookId);
        if (user == null || book == null || book.isBorrowed()) return false;
        user.addUserBook(book);
        book.setIsBorrowed(true);
        return userRepository.update(user) && bookRepository.updateBook(book);
    }

    @Override
    public boolean returnBook(int bookId) {
        User user = this.getActiveUser();
        MyList<Book> borrowedBooks = user.getUserBooks();
        if (borrowedBooks.isEmpty()) return false;
        return returnBook(user, bookId);
    }

    @Override
    public boolean returnBook(User user, int bookId) {
        Book book =  bookRepository.getBookById(bookId);
        if (user == null || book == null || !book.isBorrowed()) return false;
        user.removeUserBook(book);
        book.setIsBorrowed(false);
        return userRepository.update(user) && bookRepository.updateBook(book);
    }

    // ==================BOOKS========================
}
