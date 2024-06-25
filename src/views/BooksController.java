package src.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import src.services.Book;
import src.services.dao.BookDaoService;

import java.net.URL;
import java.util.ResourceBundle;

public class BooksController implements Initializable {

    @FXML
    public TableView<Book> table;

    @FXML
    public TableColumn<Book, String> author;

    @FXML
    public TableColumn<Book, Integer> copies;

    @FXML
    public TableColumn<Book, String> publisher;

    @FXML
    public TableColumn<Book, String> title;

    private BookDaoService bookService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookService = new BookDaoService();

        title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        publisher.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
        author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        copies.setCellValueFactory(new PropertyValueFactory<Book, Integer>("availableCopies"));

        loadBooks();
    }

    private void loadBooks() {
        ObservableList<Book> books = FXCollections.observableArrayList(bookService.getAllBooks());
        table.setItems(books);
    }
}
