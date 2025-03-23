package repository;

import model.Book;
import model.Role;
import model.User;
import utils.MyList;

public interface UserRepository {

    // create
    User addUser(String email, String password);

    // Read
    MyList<User> getAllUsers();
    MyList<User> getAllReaders();
    User getUserByEmail(String email);

    // Update
    boolean update(User user);

    // Update by email and password
    boolean updatePassword(String email, String newPassword);

    // Сохранить изменения о пользователе
    boolean updateRole(String email, Role newrole);

    // Delete User
    boolean deleteUser(String email);

}
