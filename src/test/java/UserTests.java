import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.MainService;
import service.MainServiceImpl;
import utils.MyList;

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
        User superAdmin = new User(emailSuper, passwordSuper);
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
        User user = new User(email, password);

        service.deleteUser(user);
        assertEquals(size, allUsers.size());
    }

    @Test
    void GetAllUsersShouldFailForUser() {
        service.loginUser(email, password);
        assertNull(service.getAllUsers());
    }

    @Test
    void GetAllUsersShouldSuccessForAdmin() {

    }

    @Test
    void testGetAllReaders() {
        assertTrue(service.getAllReaders().isEmpty());
        assertEquals(0, service.getAllReaders().size());

        service.borrowBook(1);
        assertEquals(1, service.getAllReaders().size());
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
        User user = new User(email, password);
        assertFalse(user.isBlocked());
        service.blockUser(user);

        // TODO как лучше?
        assertTrue(user.isBlocked());
        assertTrue(service.getUserByEmail(email).isBlocked());
    }

    @Test
    void blockSuperAdminShouldFail() {
        service.loginUser(emailAdmin, passwordAdmin);
        User userSuper = new User(emailSuper, passwordSuper);
        assertFalse(userSuper.isBlocked());
        assertFalse(service.blockUser(userSuper), "Нельзя заблокировать Супер Админа!");
        assertFalse(userSuper.isBlocked());

    }

    @Test
    void blockUserShouldFailForUser() {
        service.loginUser(email, password);
        User userAdmin = new User(emailAdmin, passwordAdmin);

        assertFalse(userAdmin.isBlocked());
        assertFalse(service.blockUser(userAdmin));

        assertFalse(userAdmin.isBlocked());
    }


}
