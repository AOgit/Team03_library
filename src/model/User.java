package model;

import utils.MyArrayList;
import utils.MyList;

import java.util.Objects;

public class User {
    private String email;
    private String password;
    private  MyList<Book> userBooks;
    private Role role;
    private boolean blocked;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.userBooks = new MyArrayList<>();
        this.role = Role.USER;
    }


    @Override
    public String toString() {
        return String.format("User => email: %s, password: %s. userBooks: %d, role: %s. %s", email, password, userBooks.size(), role, blocked);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MyList<Book> getUserBooks() {
        return userBooks;
    }

    public void setUserBooks(MyList<Book> userBooks) {
        this.userBooks = userBooks;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return blocked;
    }

    public void setActive(boolean active) {
        this.blocked = active;
    }
}
