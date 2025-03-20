package repository;

import model.User;

public interface UserRepository {

    // create
    User addUser(String email, String password);

    // Read
    User getUserByEmail(String email);

    // Update by email and password
    boolean updatePassword(String email, String newPassword);

    // Delete User
    void deleteUser(String email);



    // Обсуждаемые методы:

//    boolean isEmailExist(String email);

    // Update User
//    void saveUser(User user);




}
