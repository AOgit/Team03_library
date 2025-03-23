package repository;

import model.Book;
import model.Role;
import model.User;
import utils.MyArrayList;
import utils.MyList;

import java.util.Arrays;

public class UserRepositoryImpl implements UserRepository {

    // Храним список пользователей
    private final MyList<User> users;

    public UserRepositoryImpl() {
        users = new MyArrayList<>();
        addUsers();
    }

    private void addUsers() {

       // Изначально в системе должен быть как минимум один SuperAdmin
        User superAdmin = new User("superAdmin@mail.de", "superAdmin");
        superAdmin.setRole(Role.SUPER_ADMIN);

        User admin = new User("admin@mail.de", "admin");
        admin.setRole(Role.ADMIN);

        User user = new User("user@mail.de", "user");

        users.addAll(superAdmin, admin, user);
    }


    @Override
    public User addUser(String email, String password) {
        User user = new User(email, password);
        users.add(user);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public MyList<Book> getUserBooksByEmail(String email) {
        if (getUserByEmail(email) != null) {
            return getUserByEmail(email).getUserBooks();
        }
        return null;
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                user.setPassword(newPassword);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateRole(String email, Role newRole) {
        // Стараемся переиспользовать методы и не повторяться
        User user = getUserByEmail(email);
        if (user != null) {
            user.setRole(newRole);
            return true;
        }
        return false;
    }


    @Override
    public boolean deleteUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return users.remove(user);
            }
        }
        return false;
    }

}
