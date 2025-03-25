package service;

import model.Book;
import model.Role;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import utils.MyList;

import static org.junit.jupiter.api.Assertions.*;

class MainServiceImplTest {

    private BookRepository bookRepository;
    private UserRepository userRepository;
    private MainServiceImpl mainService;
    private User superAdmin;
    private User admin;
    private User user;
//    private final String emailSuper = "superAdmin@mail.de";
//    private final String passwordSuper = "superAdmin";
//    private final String emailAdmin = "admin@mail.de";
//    private final String passwordAdmin = "admin";
//    private final String email = "user@mail.de";
//    private final String password = "user";


    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl();
        bookRepository = new BookRepositoryImpl();
        mainService = new MainServiceImpl(bookRepository, userRepository);

//  superAdmin = new User("superAdmin@mail.de", "SuperAdmin1!");
//  superAdmin.setRole(Role.SUPER_ADMIN);
//
//  admin = new User("admin@mail.de", "Admin1234!");
//  admin.setRole(Role.ADMIN);
//
//  user = new User("user@mail.de", "User1234!");
//  user.setRole(Role.USER);

    }

// @Test
// void testAddUser() {
//
//  assertNotNull(superAdmin, "SuperAdmin не был добавлен!");
//  assertNotNull(admin, "Admin не был добавлен!");
//  assertNotNull(user, "User не был добавлен!");
//
//  assertEquals(Role.SUPER_ADMIN, superAdmin.getRole(), "Роль для SuperAdmin неверна!");
//  assertEquals(Role.ADMIN, admin.getRole(), "Роль для Admin неверна!");
//  assertEquals(Role.USER, user.getRole(), "Роль для User неверна!");
// }

    @Test
    void testAddUserDuplicateEmail() {
        userRepository.addUser("user@mail.de", "User1234!");

        User user = userRepository.getUserByEmail("user@mail.de");
        assertNotNull(user, "Пользователь с таким email не найден!");
    }

    // Падает
    @Test
    void testLoginSuccess() {
        boolean result = mainService.loginUser("user@mail.de", "user");
        System.out.println("Результат логина: " + result);
        assertTrue(result, "Пользователь должен успешно войти!");
    }

    @Test
    void logout() {

        mainService.registerUser("userr@mail.de", "User1234!!");
        mainService.loginUser("userr@mail.de", "User1234!!");

        assertNotNull(mainService.getActiveUser(), "Активный пользователь должен быть не null");
        mainService.logout();

        assertNull(mainService.getActiveUser(), "После выхода активный пользователь должен быть null");
    }

    @Test
    void testIsLoggedIn() {
        assertFalse(mainService.isLoggedIn(), "До входа в систему пользователь не должен быть залогинен");

        mainService.loginUser("user@mail.de", "user");
        assertTrue(mainService.isLoggedIn(), "После входа в систему пользователь должен быть залогинен");

        mainService.logout();
        assertFalse(mainService.isLoggedIn(), "После выхода пользователь должен быть разлогинен");
    }

    @Test
    void testIsAdmin() {
//        userRepository.addUser("admin1@mail.de", "Admin1234!!");
        mainService.registerUser("admin1@mail.de", "Admin1234!!");
        User adminUser = userRepository.getUserByEmail("admin1@mail.de");
        adminUser.setRole(Role.ADMIN);

//        mainService.loginUser("admin@mail.de", "admin");
        mainService.loginUser("admin1@mail.de", "Admin1234!!");

        assertTrue(mainService.isAdmin(), "Админ должен быть распознан как админ");
        assertTrue(mainService.isLoggedIn(), "Пользователь вошел");
        assertFalse(mainService.isSuperAdmin(), "Админ не должен быть супер-админом");
    }

    @Test
    @Disabled
    void getUserByEmail() {

    }
    @Test
    @Disabled
    void getActiveUser() {
    }

    @Test
    @Disabled
    void getAllUsers() {
    }

    @Test
    @Disabled
    void getAllReaders() {
    }


    @Test
    @Disabled
    void blockUser() {
    }

    @Test
    @Disabled
    void unblockUser() {
    }

    @Test
    @Disabled
    void getUserBooks() {
    }



    @Test
    @Disabled
    void changeUserRole() {
    }

    @Test
    @Disabled
    void deleteUser() {
    }

    @Test
    @Disabled
    void getBooksByTitle() {
    }

    @Test
    @Disabled
    void getUserBooksByEmail() {
    }

    @Test
    @Disabled
    void getBooksByAuthor() {
    }

    @Test
    @Disabled
    void getBookByGenre() {
    }

    @Test
    @Disabled
    void getAllBooks() {
    }

    @Test
    @Disabled
    void getAvailableBooks() {
    }

    @Test
    @Disabled
    void getBorrowedBooks() {
    }

    @Test
    @Disabled
    void getBookById() {
    }

    @Test
    void deleteBookById_shouldFailForUser() {
        mainService.loginUser("user@mail.de", "user");

        MyList<Book> listBeforeDelete = mainService.getAllBooks();
        int initialSize = listBeforeDelete.size();

        boolean deleteResult = mainService.deleteBookById(2);

        MyList<Book> listAfterDelete = mainService.getAllBooks();
        int newSize = listAfterDelete.size();

        assertFalse(deleteResult, "Метод удаления должен вернуть false");
        assertEquals(initialSize, newSize, "Размер списка не должен измениться");
        assertNotNull(bookRepository.getBookById(2), "Книга с id 2 НЕ должна быть удалена");
    }

    @Test
    void deleteBookById_shouldSucceedForAdmin() {
        mainService.loginUser("admin@mail.de", "admin");

        MyList<Book> listBeforeDelete = mainService.getAllBooks();
        int initialSize = listBeforeDelete.size();

        boolean deleteResult = mainService.deleteBookById(2);

        MyList<Book> listAfterDelete = mainService.getAllBooks();
        int newSize = listAfterDelete.size();
        assertTrue(deleteResult, "Метод удаления должен вернуть true");
        assertEquals(initialSize - 1, newSize, "Размер списка должен уменьшиться на 1");
        assertNull(bookRepository.getBookById(2), "Книга с id 2 должна быть удалена");
    }


        @Test
        void addBook () {
            mainService.loginUser("admin@mail.de", "admin");

            MyList<Book> listBeforeAdd = mainService.getAllBooks();
            int initialSize1 = listBeforeAdd.size();

            mainService.addBook("Три поросенка", "Михалков С.", 1933, 32, "сказка");
            MyList<Book> listAfterAdd = mainService.getAllBooks();
            int newSize1 = listAfterAdd.size();

            assertEquals(initialSize1 + 1, newSize1, "Размер списка книг должен увеличиться после добавления");

            Book lastBook = listAfterAdd.get(newSize1 - 1);
            assertEquals("Три поросенка", lastBook.getTitle(), "Название книги должно быть 'Три поросенка'");
            assertEquals("Михалков С.", lastBook.getAuthor(), "Автор книги должен быть 'Михалков С.'");
            assertEquals(1933, lastBook.getYear(), "Год выпуска должен быть 1933");
            assertEquals(32, lastBook.getPages(), "Количество страниц должно быть 32");
            assertEquals("сказка", lastBook.getGenre(), "Жанр книги должен быть 'сказка'");
        }

        @Test
        void editBook_shouldFailForUser () {
            Book bookBeforeEdit = bookRepository.getBookById(1);
            mainService.loginUser("user@mail.de", "user");
            mainService.editBook(1, "Python", "Author", 1999, 300, "IT");

            Book bookAfterEdit = bookRepository.getBookById(1);
//        assertEquals(bookBeforeEdit, bookAfterEdit, "Юзер не может редактировать книги!");
            assertNotEquals("Python", bookAfterEdit.getTitle(), "Название книги должно измениться");
        }

        @Test
        void editBook_shouldSucceedForAdmin () {
            mainService.loginUser("admin@mail.de", "admin");

            Book bookBeforeEdit = bookRepository.getBookById(1);
            boolean editResult = mainService.editBook(1, "Python", "Author", 1999, 300, "IT");
            assertTrue(editResult, "Админ должен успешно редактировать книгу!");

            Book bookAfterEdit = bookRepository.getBookById(1);
            assertNotEquals(bookBeforeEdit, bookAfterEdit, "Админ может редактировать книги!");

            assertEquals("Python", bookAfterEdit.getTitle(), "Название книги должно измениться");
            assertEquals("Author", bookAfterEdit.getAuthor(), "Автор книги должен измениться");
            assertEquals(1999, bookAfterEdit.getYear(), "Год выпуска книги должен измениться");
            assertEquals(300, bookAfterEdit.getPages(), "Количество страниц книги должно измениться");
            assertEquals("IT", bookAfterEdit.getGenre(), "Жанр книги должен измениться");
        }

        @Test
        void borrowBook_ShouldFailForNotRegisteredUser () {
            mainService.loginUser("user11@mail.de", "user");
            boolean result = mainService.borrowBook(1);

            assertFalse(result, "Незарегистрированный пользователь не должен брать книгу!");

        }
        @Test
        void borrowBook_ShouldFailForNonExistentBook () {
            mainService.loginUser("user@mail.de", "user");

            boolean result = mainService.borrowBook(999);
            assertFalse(result, "Нельзя взять несуществующую книгу!");
        }

        @Test
        void borrowBook_ShouldSucceedWhenBookIsAvailable () {
            mainService.loginUser("user@mail.de", "user");
            Book book = mainService.getBookById(2);
            // в демонстрационных целях в сервисе книги 1 и 3 уже взята пользователем reader
            boolean result = mainService.borrowBook(2);

            assertTrue(result, "Книга должна быть успешно взята!");

            MyList<Book> userBooks = mainService.getUserBooks();

            assertTrue(userBooks.contains(book), "Книга должна быть в списке книг пользователя!");
            assertTrue(book.isBorrowed(), "Книга должна быть помечена как занятая!");
        }

        @Test
        void returnBook_ShouldFailForNonExistentBook() {
            mainService.loginUser("user@mail.de", "user");
            boolean result = mainService.returnBook(999);
            assertFalse(result, "Нельзя вернуть несуществующую книгу!");

        }
    }