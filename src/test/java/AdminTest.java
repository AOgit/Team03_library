import model.Role;
import model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.UserRepositoryImpl;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

//class AdminTest {

//    private UserRepositoryImpl userRepository;
//
//    @BeforeEach
//    void setUp() {
//        userRepository = new UserRepositoryImpl();
//    }

//    @BeforeAll
//    static void addUsers() {
//
//        List<User> users = new ArrayList<>();
//
//        // Создаем пользователей
//        User superAdmin = new User("superAdmin@mail.de", "superAdmin");
//        superAdmin.setRole(Role.SUPER_ADMIN);
//
//        User admin = new User("admin@mail.de", "admin");
//        admin.setRole(Role.ADMIN);
//
//        User user = new User("user@mail.de", "user");
//
//        // Добавляем в список
//        users.addAll(List.of(superAdmin, admin, user));
//    }

