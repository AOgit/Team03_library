import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserTest {
    User user;
    final static String START_EMAIL = "john@test.com";
    final static String START_PASSWORD = "qwerty1QS%";

    @BeforeEach
    void setUP() {
        this.user = new User(START_EMAIL, START_PASSWORD);
    }

    @ParameterizedTest
    @ValueSource(strings = {"wr234234@csdf.we", "werwer@tww.com.de"})
    void testValidEmailSet(String validEmail) {
        user.setEmail(validEmail);
        Assertions.assertEquals(validEmail, user.getEmail());
    }

    @ParameterizedTest
    @ValueSource(strings = {"notvalidemail.de", "notnalid@ema@il.de", "notvalid@emaild.e", "notvalid@em%ail.de"})
    void testNotValidEmailSet(String NotValidEmail) {
        user.setEmail(NotValidEmail);
        Assertions.assertNotEquals(NotValidEmail, user.getEmail());
        Assertions.assertEquals(START_EMAIL, user.getEmail());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Pa$$w-1rd", "qwerty.D1e"/*, "Pass"*/})
    void testValidPasswordSet(String validPassword) {
        user.setPassword(validPassword);
        Assertions.assertEquals(validPassword, user.getPassword());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Pa$$w-1r", "PA$$W0RD", "pa$$w0rd", "Passw0rd", "Pa$$word"/*, "Pa$$w0rd"*/})
    void testNotValidPasswordSet(String notValidPassword) {
        user.setPassword(notValidPassword);
        Assertions.assertNotEquals(notValidPassword, user.getPassword());
        Assertions.assertEquals(START_PASSWORD, user.getPassword());
    }

}