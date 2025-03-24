import model.Book;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.MainService;
import service.MainServiceImpl;
import utils.MyList;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests {
    private User superAdmin;
    private User user;
    private Book book1;
    private UserRepository userRepository;
    private BookRepository bookRepository;
    private MainService service;
    private final String emailSuper = "superAdmin@mail.de";
    private final String passwordSeuper = "superAdmin";

    private final String email = "user@mail.de";
    private final String password = "user";

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl();
        bookRepository = new BookRepositoryImpl();
        service = new MainServiceImpl(bookRepository, userRepository);
        superAdmin = new User(emailSuper, passwordSeuper);
        user = new User(email, password);
        service.loginUser(emailSuper, passwordSeuper);

        book1 = new Book(1, "Java для чайников", "Берд Б.", 2013, 368, "сказка");
    }

    @Test
    void createUser() {
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
    void getAllUsers() {
        MyList<User> allUsers = service.getAllUsers();
        assertNotNull(allUsers);
        int size = allUsers.size();
        service.registerUser("email@gmail.com", "Password111_");
        assertEquals(size + 1, allUsers.size());

        service.deleteUser(user);
        assertEquals(size, allUsers.size());
    }

    @Test
    void getAllReaders() {
        assertTrue(service.getAllReaders().isEmpty());
        assertEquals(0, service.getAllReaders().size());

        service.borrowBook(1);
        assertEquals(1, service.getAllReaders().size());
    }



}
