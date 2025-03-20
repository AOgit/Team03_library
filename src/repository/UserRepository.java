package repository;

import model.User;

public interface UserRepository {
    // create
    User addUser(String email, String password);

    // Read
    User getUserByEmail(String email);

    // Update by email and password
    boolean updatePassword(String email, String newPassword);

    // Update User
    void saveUser(User user);

    // Delete User
    void deleteUser(String email);

//    boolean isEmailExist(String email);







}
