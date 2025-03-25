import model.Book;
import model.Role;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.MainService;
import service.MainServiceImpl;
import utils.MyList;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests {
    private UserRepository userRepository;
    private BookRepository bookRepository;
    private MainService service;

    private final String emailSuper = "superAdmin@mail.de";
    private final String passwordSuper = "superAdmin";

    private final String email = "user@mail.de";
    private final String password = "user";

    private final String emailAdmin = "admin@mail.de";
    private final String passwordAdmin = "admin";

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl();
        bookRepository = new BookRepositoryImpl();
        service = new MainServiceImpl(bookRepository, userRepository);
        service.loginUser(emailSuper, passwordSuper);
    }

    @Test
    void createUser() {
        User superAdmin = service.getUserByEmail(emailSuper);
        assertNotNull(superAdmin);
    }

    @Test
    void testLogout() {
        System.out.println(service.getActiveUser());
        service.logout();
        Assertions.assertNull(service.getActiveUser());
        System.out.println(service.getActiveUser());
    }

    @Test
    void testGetActiveUser() {
        assertNotNull(service.getActiveUser());

        service.logout();
        assertNull(service.getActiveUser());

        service.loginUser(email, password);
        assertNotNull(service.getActiveUser());
    }

    @Test
    void testGetAllUsers() {
        MyList<User> allUsers = service.getAllUsers();
        assertNotNull(allUsers);
        int size = allUsers.size();
        service.registerUser("email@gmail.com", "Password111_");
        assertEquals(size + 1, allUsers.size());

        service.loginUser(emailSuper, passwordSuper);
        User user = service.getUserByEmail(email);

        service.deleteUser(user);
        assertEquals(size, allUsers.size());
    }

    @Test
    void GetAllUsersShouldFailForUser() {
        service.loginUser(email, password);
        assertNull(service.getAllUsers());
    }

    @Test
    void testGetAllReaders() {
        User user = service.getUserByEmail(email);
        assertEquals(1, service.getAllReaders().size());

        service.borrowBook(user, 2);
        assertEquals(2, service.getAllReaders().size());
    }

    @Test
    void GetAllReadersShouldFailForUser() {
        service.loginUser(email, password);
        assertNull(service.getAllReaders());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "email.com", "незарегистрированный email"})
    void testGetUserByInvalidEmail(String emails) {
        assertNull(service.getUserByEmail(emails), "Если такой email есть, то пользователь != null.");
    }

    @ParameterizedTest
    @ValueSource(strings = {email, emailSuper, emailAdmin})
    void testGetUserByValidEmail(String emails) {
        assertNotNull(service.getUserByEmail(emails));
    }

    @Test
    void getUserByEmailShouldFailForUser() {
        service.loginUser(email, password);
        assertNull(service.getUserByEmail(email));
    }

    @Test
    void testBlockUser() {
        User user = service.getUserByEmail(email);
        assertFalse(user.isBlocked());
        service.blockUser(user);
        assertTrue(user.isBlocked());
    }

    @Test
    void blockSuperAdminShouldFail() {
        service.loginUser(emailAdmin, passwordAdmin);
        User userSuper = service.getUserByEmail(emailSuper);
        assertFalse(userSuper.isBlocked());
        assertFalse(service.blockUser(userSuper), "Нельзя заблокировать Супер Админа!");
        assertFalse(userSuper.isBlocked());

    }

    @Test
    void blockUserShouldFailForUser() {
        User userAdmin = service.getUserByEmail(emailAdmin);
        service.loginUser(email, password);

        assertFalse(userAdmin.isBlocked());
        assertFalse(service.blockUser(userAdmin));
        assertFalse(userAdmin.isBlocked());
    }

    @Test
    void testUnBlockUser() {
        User user = service.getUserByEmail(email);
        service.blockUser(user);
        assertTrue(user.isBlocked());
        service.unblockUser(user);
        assertFalse(user.isBlocked());
    }

    @Test
    void unBlockSuperAdminShouldFail() {
        service.loginUser(emailAdmin, passwordAdmin);
        User userSuper = service.getUserByEmail(emailSuper);
        userSuper.setBlocked(true);
        assertFalse(service.unblockUser(userSuper));
        assertTrue(userSuper.isBlocked());
    }

    @Test
    void unBlockUserShouldFailForUser() {
        User userAdmin = service.getUserByEmail(emailAdmin);
        userAdmin.setBlocked(true);
        service.loginUser(email, password);

        assertTrue(userAdmin.isBlocked());
        assertFalse(service.unblockUser(userAdmin));
        assertTrue(userAdmin.isBlocked());
    }

    @Test
    void testGetUserBooks() {
        User user = service.getUserByEmail(email);
        assertTrue(service.getUserBooks(user).isEmpty());
        assertEquals(0, service.getUserBooks(user).size());
        user.addUserBook(new Book(1, "book1", "author!", 1, 2, "genre"));
        user.addUserBook(new Book(2, "book2", "author2!", 2, 3, "genre2"));
        assertEquals(2, service.getUserBooks(user).size());
    }

    @Test
    @Disabled
    void testGetUserBooksShouldFailForUser() {
        User user = service.getUserByEmail(email);
        service.loginUser(email, password);
        assertNull(service.getUserBooks(user));
    }

    @Test
    void testChangeUserRole() {
        User user = service.getUserByEmail(email);
        assertEquals(Role.USER, user.getRole());

        service.changeUserRole(user, Role.USER);
        assertEquals(Role.USER, user.getRole());

        assertTrue(service.changeUserRole(user, Role.ADMIN));
        assertEquals(Role.ADMIN, user.getRole());
    }

//    static Stream<Role> testDataRoles() {
//        return Stream.of(Role.USER, Role.ADMIN, Role.SUPER_ADMIN);
//    }

    @Test
    void ChangeRoleShouldFailForUser() {
        User userAdmin = service.getUserByEmail(emailAdmin);
        service.loginUser(email, password);
        assertFalse(service.changeUserRole(userAdmin, Role.USER));
        assertEquals(Role.ADMIN, userAdmin.getRole());
    }

    @Test
    void ChangeSuperAdminRoleShouldFail() {
        User userSuper = service.getUserByEmail(emailSuper);
        assertEquals(Role.SUPER_ADMIN, userSuper.getRole());
        assertFalse(service.changeUserRole(userSuper, Role.ADMIN), "Нельзя поменять роль у СуперАдмина!");
        assertEquals(Role.SUPER_ADMIN, userSuper.getRole());
    }

    @Test
    void testDeleteUser() {
        User user = service.getUserByEmail(email);
        assertTrue(service.deleteUser(user));
        assertNull(service.getUserByEmail(email));
    }

    @Test
    void deleteAdminShouldFailForUser() {
        User userAdmin = service.getUserByEmail(emailAdmin);
        service.loginUser(email, password);
        assertFalse(service.deleteUser(userAdmin));
        service.loginUser(emailSuper, passwordSuper);
        assertNotNull(service.getUserByEmail(emailAdmin));
    }

    @Test
    void deleteSuperAdminShouldFail() {
        User userSuper = service.getUserByEmail(emailSuper);
        assertFalse(service.deleteUser(userSuper));
        assertNotNull(service.getUserByEmail(emailSuper));
    }

}
