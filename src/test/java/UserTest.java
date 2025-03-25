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

class UserTest {
    User user;
    final static String START_EMAIL = "john@test.com";
    final static String START_PASSWORD = "qwerty1QS%";
    final static String ADMIN_EMAIL = "admin@mail.de";
    final static String ADMIN_PASSWORD = "admin";
    private UserRepository userRepository;
    private BookRepository bookRepository;
    private MainService service;

    @BeforeEach
    void setUP() {
    userRepository = new UserRepositoryImpl();
    bookRepository = new BookRepositoryImpl();
    service = new MainServiceImpl(bookRepository, userRepository);
//        service.registerUser(START_EMAIL, START_PASSWORD);
    }

    @ParameterizedTest
    @ValueSource(strings = {"wr234234@csdf.we", "werwer@tww.com.de"})
    void testValidEmailSet(String validEmail) {
        service.registerUser(validEmail, "Pa$$w0rd");
        service.loginUser(ADMIN_EMAIL, ADMIN_PASSWORD);
        Assertions.assertEquals(validEmail, service.getUserByEmail(validEmail).getEmail());
    }

//    @ParameterizedTest
//    @ValueSource(strings = {"notvalidemail.de", "notnalid@ema@il.de", "notvalid@emaild.e", "notvalid@em%ail.de"})
//    void testNotValidEmailSet(String NotValidEmail) {
//        User user = service.registerUser(NotValidEmail, "Pa$$w0rd");
//        service.loginUser(ADMIN_EMAIL, ADMIN_PASSWORD);
//        Assertions.assertNotEquals(NotValidEmail, service.getUserByEmail(NotValidEmail).getEmail());
//        Assertions.assertEquals(START_EMAIL, user.getEmail());
//    }

//    @ParameterizedTest
//    @ValueSource(strings = {"Pa$$w-1rd", "qwerty.D1e"/*, "Pass"*/})
//    void testValidPasswordSet(String validPassword) {
//        user.setPassword(validPassword);
//        Assertions.assertEquals(validPassword, user.getPassword());
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"Pa$$w-1r", "PA$$W0RD", "pa$$w0rd", "Passw0rd", "Pa$$word"/*, "Pa$$w0rd"*/})
//    void testNotValidPasswordSet(String notValidPassword) {
//        user.setPassword(notValidPassword);
//        Assertions.assertNotEquals(notValidPassword, user.getPassword());
//        Assertions.assertEquals(START_PASSWORD, user.getPassword());
//    }

}