package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.Transaction;
import services.User;
import services.dao.TransactionDaoService;
import services.utils.DBUtil;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {
    @FXML
    public TableColumn<Transaction, Integer> bookId;

    @FXML
    public TableColumn<Transaction, Date> dueDate;

    @FXML
    public TableColumn<Transaction, Date> issueDate;

    @FXML
    public TableColumn<Transaction, String> status;

    @FXML
    public TableView<Transaction> table;

    @FXML
    public TableColumn<Transaction, Integer> userId;

    private TransactionDaoService transactionService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transactionService = new TransactionDaoService(DBUtil.getConnection());

        bookId.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        userId.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        issueDate.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadTansactions();
    }

    private void loadTansactions() {
        ObservableList<Transaction> transactions = FXCollections.observableArrayList(transactionService.getAllTransactions());
        table.setItems(transactions);
    }
}
