package src.services.dao;

import src.services.Book;
import src.services.User;
import src.services.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoService implements UserDao{
    private static Connection conn;

    public UserDaoService() {
        conn = DBUtil.getConnection();
    }

    @Override
    public void addUser(User user) {
        try {
            String sql = "INSERT INTO users (name, email, role, password, phoneNumber, isActive) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3,user.getRole());
            ps.setString(4,user.getPassword());
            ps.setString(5,user.getPhoneNumber());
            ps.setString(6, user.getIsActive());
            ps.executeUpdate();

        }catch (Exception e){
            throw new RuntimeException("Failed to add book " + e.getMessage());
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
                user.setIsActive(rs.getString("isActive"));
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

    }

    @Override
    public void deleteUser(int id) {

    }
}
