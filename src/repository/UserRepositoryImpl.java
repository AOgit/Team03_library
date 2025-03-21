package repository;

import model.Book;
import model.Role;
import model.User;
import utils.MyArrayList;
import utils.MyList;

public class UserRepositoryImpl implements UserRepository {

    // Храним список пользователей
    private final MyList<User> users;

    public UserRepositoryImpl() {
        users = new MyArrayList<>();
        addUsers();
    }

    private void addUsers() {
        User admin = new User("1", "1");
        admin.setRole(Role.ADMIN);

        User user = new User("2", "2");
        user.setRole(Role.USER);

        users.addAll(admin, user);
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
    public void saveUser(User user) {
        for (User us : users) {
            if (user.getEmail().equals(us.getEmail())) {
                us = user;
                return;
            }
        }
        users.add(user);
    }

    @Override
    public void deleteUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                users.remove(user);
                return;
            }
        }
    }
}
