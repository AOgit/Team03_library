package repository;

import model.Book;
import model.Role;
import model.User;
import utils.MyArrayList;
import utils.MyList;

import java.util.Arrays;
import java.util.Iterator;

public class UserRepositoryImpl implements UserRepository {

    // Храним список пользователей
    private final MyList<User> users;

    public UserRepositoryImpl() {
        users = new MyArrayList<>();
//        addUsers();
    }

    private void addUsers() {

       // Изначально в системе должен быть как минимум один SuperAdmin
        User superAdmin = new User("superAdmin@mail.de", "superAdmin");
        superAdmin.setRole(Role.SUPER_ADMIN);
        User admin = new User("admin@mail.de", "admin");
        admin.setRole(Role.ADMIN);
        User user = new User("user@mail.de", "user");
        admin.setRole(Role.USER);

        users.addAll(superAdmin, admin, user);
    }

    @Override
    public User addUser(String email, String password) {
        User user = new User(email, password);
        users.add(user);
        return user;
    }


    @Override
    public MyList<User> getAllUsers() {
        return this.users;
    }
    @Override

    public MyList<User> getAllReaders() {
       MyList<User> readers = new MyArrayList<>();
        for (User user : users) {
            if (!user.getUserBooks().isEmpty()) readers.add(user);
        }
        return readers;
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
    public boolean update(User user) {
        for (int i = 0; i < users.size(); i++) {
            User us = users.get(i);
            if (us.getEmail().equals(user.getEmail())) {
                users.set(i, user); // Обновляем книгу по индексу
                return true;
            }
        }
        return false;
    }

//
//    @Override
//    public boolean updatePassword(String email, String newPassword) {
//        for (User user : users) {
//            if (user.getEmail().equals(email)) {
//                user.setPassword(newPassword);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean updateRole(String email, Role newRole) {
//        // Стараемся переиспользовать методы и не повторяться
//        User user = getUserByEmail(email);
//        if (user != null) {
//            user.setRole(newRole);
//            return true;
//        }
//        return false;
//    }
//

//    @Override
//    public boolean deleteUser(String email) {
//        for (User user : users) {
//            if (user.getEmail().equals(email)) {
//                return users.remove(user);
//            }
//        }
//        return false;
//    }
    @Override
    public boolean delete(User user) {
        return this.users.remove(user);
    }


}
