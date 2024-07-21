package services.dao;

import services.User;
import services.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoService implements UserDao{
    private Connection conn;

    public UserDaoService(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addUser(User user) {
        try {
            String sql = "INSERT INTO users (name, email, password, phoneNumber) VALUES (?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getPhoneNumber());
            ps.executeUpdate();

        }catch (Exception e){
            throw new RuntimeException("Failed to add user " + e.getMessage());
        }
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public int checkUserExistence(String email, String password) {
        int count = 0;
        try{
            String sql = "SELECT COUNT(1) FROM users WHERE email = ? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                count =  rs.getInt(1);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try{
            String sql = "SELECT * FROM Users";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setIsActive(rs.getBoolean("isActive"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        }
        catch (Exception e){
            throw new RuntimeException("Failed to fetch all books " + e.getMessage());
        }

        return users;
    }

    @Override
    public void updateUser(User user) {
        try {
            String sql = "UPDATE users SET name = ?, email = ?, password = ?, phoneNumber = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhoneNumber());
            ps.setInt(5, user.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user " + e.getMessage());
        }
    }
}
