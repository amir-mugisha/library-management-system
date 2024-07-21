import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javafx.event.ActionEvent;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.dao.BookDaoService;
import services.utils.DBUtil;
import utils.TestDBUtil;
import views.AddBookController;
import services.Book;

@Disabled
public class AddBookControllerTest {

    @InjectMocks
    private AddBookController controller;

    @Mock
    private BookDaoService bookService;



    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        bookService = new BookDaoService(DBUtil.getConnection());
        controller = new AddBookController();
        controller.bookService = bookService;
    }

    @Test
    public void testAddBookValidInput() throws Exception {
        controller.titleTextField.setText("Test Title");
        controller.authorTextField.setText("Test Author");

        ActionEvent event = mock(ActionEvent.class);

        controller.addBook(event);

        verify(bookService, times(1)).addBook(any(Book.class));
    }

    @Test
    public void testAddBookInvalidInput() {

        assertEquals("Please fill all fields", controller.warningLabel.getText());
    }

}