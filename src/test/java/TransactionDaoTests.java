import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.Transaction;
import services.dao.TransactionDaoService;
import utils.TestDBUtil;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransactionDaoTests {

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet rs;
    private TransactionDaoService transactionDaoService;

    @BeforeEach
    public void setUp() throws Exception {
        System.setProperty("env", "test");
        MockitoAnnotations.openMocks(this);
        Connection actualConnection = TestDBUtil.getConnection();

        try (Statement stmt = actualConnection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS Transaction (id SERIAL PRIMARY KEY, book_id INT, user_id INT, status VARCHAR(255), dueDate DATE, created_at DATE)");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create TRANSACTION table: " + e.getMessage());
        }
        mockConnection = spy(actualConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(ps);
        transactionDaoService = new TransactionDaoService(mockConnection);
    }

    @Test
    public void itShouldAddTransaction() throws Exception {
        Transaction transaction = new Transaction(1, 1, java.time.LocalDate.now());

        when(ps.executeUpdate()).thenReturn(1);

        transactionDaoService.addTransaction(transaction);

        verify(mockConnection, times(2)).prepareStatement(anyString());
        verify(ps).executeUpdate();
    }



    @Test
    public void itShouldUpdateTransaction() throws Exception {
        Transaction transaction = new Transaction(1, 1, java.time.LocalDate.now());

        when(ps.executeUpdate()).thenReturn(1);

        transactionDaoService.updateTransaction(transaction);

        verify(mockConnection, times(2)).prepareStatement(anyString());
        verify(ps).executeUpdate();
    }

    @Test
    public void itShouldDeleteTransaction() throws Exception {
        int testId = 1;

        when(ps.executeUpdate()).thenReturn(1);

        transactionDaoService.deleteTransaction(testId);

        verify(mockConnection, times(2)).prepareStatement(anyString());
        verify(ps).setInt(1, testId);
        verify(ps).executeUpdate();
    }

    @AfterEach
    public void tearDown() throws Exception {
        System.setProperty("env", "prod");
        mockConnection.close();
    }
}