package repository;

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
        // Todo добавить роль админа
        User user = new User("2", "2");
        // Todo добавить роль пользователя
        users.addAll(admin, user);
    }


    @Override
    public User addUser(String email, String password) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        return false;
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void deleteUser(String email) {

    }
}
