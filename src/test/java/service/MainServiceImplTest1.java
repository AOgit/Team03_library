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

class MainServiceImplTest1 {

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

        userRepository.addUser("userr@mail.de", "User1234!!");
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
        userRepository.addUser("admin1@mail.de", "Admin1234!!");
        User adminUser = userRepository.getUserByEmail("admin1@mail.de");
        adminUser.setRole(Role.ADMIN);

//        mainService.loginUser("admin@mail.de", "admin");
        mainService.loginUser("admin1@mail.de", "Admin1234!!");

        assertTrue(mainService.isAdmin(), "Админ должен быть распознан как админ");
        assertTrue(mainService.isLoggedIn(), "Пользователь вошел");
        assertFalse(mainService.isSuperAdmin(), "Админ не должен быть супер-админом");
    }

    @Test
    void isSuperAdmin() {

        userRepository.addUser("Superadmin1@mail.de", "SuperAdmin1234!!");
        User adminUser = userRepository.getUserByEmail("Superadmin1@mail.de");
        adminUser.setRole(Role.SUPER_ADMIN);

//        mainService.loginUser("admin@mail.de", "admin");
        mainService.loginUser("Superadmin1@mail.de", "SuperAdmin1234!!");

        assertTrue(mainService.isSuperAdmin(), "Супер-админ должен быть распознан как супер-админ");
        assertTrue(mainService.isLoggedIn(), "Система должна считать, что пользователь вошел");

    }

    @Test
    void getUserByEmail() {

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
    void blockUser() {
    }

    @Test
    void unblockUser() {
    }

    @Test
    void getUserBooks() {
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