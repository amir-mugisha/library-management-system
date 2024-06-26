package src.views;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TransactionsController {
    @FXML
    public TableColumn<?, ?> bookId;

    @FXML
    public TableColumn<?, ?> dueDate;

    @FXML
    public TableColumn<?, ?> issueDate;

    @FXML
    public TableColumn<?, ?> phoneNumber;

    @FXML
    public TableView<?> table;

    @FXML
    public TableColumn<?, ?> userId;
}
