import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.Book;
import services.dao.BookDaoService;
import utils.TestDBUtil;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookDaoServiceTests {

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet rs;
    private BookDaoService bookDaoService;

    @BeforeEach
    public void setUp() throws Exception {
        System.setProperty("env", "test");
        MockitoAnnotations.openMocks(this);
        Connection actualConnection = TestDBUtil.getConnection();

        try (Statement stmt = actualConnection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS book (id SERIAL PRIMARY KEY, title VARCHAR(255), author VARCHAR(255), publisher VARCHAR(255), publication_year INT, available_copies INT, isbn BIGINT, synopsis TEXT)");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create BOOKS table: " + e.getMessage());
        }
        mockConnection = spy(actualConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(ps);
        bookDaoService = new BookDaoService(mockConnection);
    }

    @Test
    public void itShouldAddBook() throws Exception {
        Book book = new Book("Test Title", "Test Author", "Test Publisher", 2020, 5, 1234567890123L, "Test Synopsis");

        when(ps.executeUpdate()).thenReturn(1);

        bookDaoService.addBook(book);

        verify(mockConnection, times(2)).prepareStatement(anyString());
        verify(ps).executeUpdate();
    }

    @Test
    public void itShouldGetAllBooks() throws Exception {
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(false); // Simulate one book found

        List<Book> books = bookDaoService.getAllBooks();

        assertEquals(1, books.size());
        verify(mockConnection, times(2)).prepareStatement(anyString());
        verify(ps).executeQuery();
    }

    @Test
    public void itShouldUpdateBook() throws Exception {
        Book book = new Book("Updated Title", "Updated Author", "Updated Publisher", 2021, 10, 1234567890123L, "Updated Synopsis");

        when(ps.executeUpdate()).thenReturn(1);

        bookDaoService.updateBook(book);

        verify(mockConnection, times(2)).prepareStatement(anyString());
        verify(ps).executeUpdate();
    }

    @Test
    public void itShouldGetSingleBook() throws Exception {
        long testIsbn = 1234567890123L;

        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);

        Book actualBook = bookDaoService.getSingleBook(testIsbn);

        verify(ps).executeQuery();
        verify(rs).next();
    }


    @Test
    public void itShouldDeleteBook() throws Exception {
        long testIsbn = 1234567890123L;

        when(ps.executeUpdate()).thenReturn(1);

        bookDaoService.deleteBook(testIsbn);

        verify(mockConnection, times(2)).prepareStatement(anyString());
        verify(ps).setLong(1, testIsbn);
        verify(ps).executeUpdate();
    }

    @AfterEach
    public void tearDown() throws Exception {
        System.setProperty("env", "prod");
        mockConnection.close();
    }
}