package service;

import model.Role;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;

import static org.junit.jupiter.api.Assertions.*;

class MainServiceImplTest {

    private BookRepository bookRepository;
    private UserRepository userRepository;
    private MainServiceImpl mainService;
    private User superAdmin;
    private User admin;
    private User user;


 @BeforeEach
 void setUp() {
  userRepository = new UserRepositoryImpl();
  bookRepository = new BookRepositoryImpl();
  mainService = new MainServiceImpl(bookRepository, userRepository);

  superAdmin = new User("superAdmin@mail.de", "SuperAdmin1!");
  superAdmin.setRole(Role.SUPER_ADMIN);

  admin = new User("admin@mail.de", "Admin1234!");
  admin.setRole(Role.ADMIN);

  user = new User("user@mail.de", "User1234!");
  user.setRole(Role.USER);

 }

 @Test
 void testAddUser() {

  assertNotNull(superAdmin, "SuperAdmin не был добавлен!");
  assertNotNull(admin, "Admin не был добавлен!");
  assertNotNull(user, "User не был добавлен!");

  assertEquals(Role.SUPER_ADMIN, superAdmin.getRole(), "Роль для SuperAdmin неверна!");
  assertEquals(Role.ADMIN, admin.getRole(), "Роль для Admin неверна!");
  assertEquals(Role.USER, user.getRole(), "Роль для User неверна!");
 }

 @Test
 void testAddUserDuplicateEmail() {
  userRepository.addUser("user@mail.de", "User1234!");

  User user = userRepository.getUserByEmail("user@mail.de");
  assertNotNull(user, "Пользователь с таким email не найден!");
 }

 // Падает
 @Test
  void testLoginSuccess() {
  boolean result = mainService.loginUser("user@mail.de", "User1234!");
  System.out.println("Результат логина: " + result);
  assertTrue(result, "Пользователь должен успешно войти!");
 }

 @Test
 void logout() {

  userRepository.addUser("userr@mail.de", "User1234!!");
  mainService.loginUser("userr@mail.de", "User1234!!");

  assertNotNull(mainService.getActiveUser(), "Активный пользователь должен быть не null");
  mainService.logout();

  assertNull(mainService.getActiveUser(), "После выхода активный пользователь должен быть null");
}

    @Test
    void getActiveUser() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getAllReaders() {
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    void blockUser() {
    }

    @Test
    void unblockUser() {
    }

    @Test
    void getUserBooks() {
    }

    @Test
    void isLoggedIn() {
    }

    @Test
    void isAdmin() {
    }

    @Test
    void isSuperAdmin() {
    }

    @Test
    void changeUserRole() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getBooksByTitle() {
    }

    @Test
    void getUserBooksByEmail() {
    }

    @Test
    void getBooksByAuthor() {
    }

    @Test
    void getBookByGenre() {
    }

    @Test
    void getAllBooks() {
    }

    @Test
    void getAvailableBooks() {
    }

    @Test
    void getBorrowedBooks() {
    }

    @Test
    void getBookById() {
    }

    @Test
    void deleteBookById() {
    }

    @Test
    void addBook() {
    }

    @Test
    void editBook() {
    }

    @Test
    void borrowBook() {
    }

    @Test
    void returnBook() {
    }
}