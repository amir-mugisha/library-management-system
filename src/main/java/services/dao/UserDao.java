package services.dao;

import services.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    User getUserById(int id);
    int checkUserExistence(String email, String password);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
}
