package src.services.dao;

import src.services.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    User getUserById(int id);
    int checkUserExistence(String email);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
}
