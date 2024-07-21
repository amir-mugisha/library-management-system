import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.User;
import services.dao.UserDaoService;
import utils.TestDBUtil;


import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserTests {

    @Mock
    private  Connection mockConnection;
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet rs;
    private UserDaoService userDaoService;

    @BeforeEach
    public void setUp() throws Exception {
        System.setProperty("env", "test");
        MockitoAnnotations.openMocks(this);
        Connection actualConnection = TestDBUtil.getConnection();

        try(Statement stmt = actualConnection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(255), email VARCHAR(255), password VARCHAR(255), phoneNumber VARCHAR(255))");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create USERS table: " + e.getMessage());
        }
        mockConnection = spy(actualConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(ps);
        userDaoService = new UserDaoService(mockConnection);

    }


    @Test
    public void itShouldAddUser() throws Exception {
        User user = new User("John Doe", "j@example.com", "password123", "1234567890");

        when(ps.executeUpdate()).thenReturn(1);

        userDaoService.addUser(user);

        verify(mockConnection, times(2)).prepareStatement(anyString());
        verify(ps).executeUpdate();
    }

    @Test
    void itShouldCheckUserExistence() throws Exception {
        String testEmail = "test@example.com";
        String testPassword = "password123";
        int expectedCount = 1;

        when(mockConnection.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(expectedCount);


        int actualCount = userDaoService.checkUserExistence(testEmail, testPassword);

        assertEquals(expectedCount, actualCount);

        verify(ps).executeQuery();
        verify(rs).getInt(1);
    }

    @Test
    public void itShouldGetAllUsers() throws Exception {
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(false);
        List<User> users = userDaoService.getAllUsers();

        assertEquals(1, users.size());

        verify(mockConnection, times(2)).prepareStatement(anyString());
        verify(ps).executeQuery();
        verify(rs, times(2)).next();
    }


    @Test
    public void itShouldUpdateUser() throws Exception {

        User testUser = new User("Jane Doe", "jane@example.com", "newpassword123", "0987654321");
        testUser.setId(1);

        String expectedSql = "UPDATE users SET name = ?, email = ?, password = ?, phoneNumber = ? WHERE id = ?";
        when(mockConnection.prepareStatement(expectedSql)).thenReturn(ps);

        userDaoService.updateUser(testUser);

        verify(mockConnection).prepareStatement(expectedSql);
        verify(ps).executeUpdate();
    }

    @Test
    public void itShouldDeleteUser() throws Exception {

            int testUserId = 1;
            String expectedSql = "DELETE FROM users WHERE id = ?";
            when(mockConnection.prepareStatement(expectedSql)).thenReturn(ps);


            userDaoService.deleteUser(testUserId);

            verify(mockConnection).prepareStatement(expectedSql);
            verify(ps).setInt(1, testUserId);
            verify(ps).executeUpdate();
    }


    @ParameterizedTest
    @CsvSource({
            "test@example.com, password123, 1",
            "nonexistent@example.com, wrongpassword, 0"
    })
    void testCheckUserExistence(String email, String password, int expectedCount) throws Exception {

        when(mockConnection.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(expectedCount > 0);
        when(rs.getInt(1)).thenReturn(expectedCount);


        int actualCount = userDaoService.checkUserExistence(email, password);

       assertEquals(expectedCount, actualCount, "The count should match the expected value.");

        verify(ps).setString(1, email);
        verify(ps).setString(2, password);
        verify(ps).executeQuery();
        verify(rs, times(expectedCount > 0 ? 1 : 0)).getInt(1);
    }

    @AfterEach
    public void tearDown() throws Exception {
        System.setProperty("env", "prod");
        mockConnection.close();
    }
}