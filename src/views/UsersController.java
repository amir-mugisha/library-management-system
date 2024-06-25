package src.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import src.services.Book;
import src.services.User;
import src.services.dao.UserDaoService;

import java.net.URL;
import java.util.ResourceBundle;

public class UsersController implements Initializable {
    @FXML
    public TableColumn<User, String> email;

    @FXML
    public TableColumn<User, String> isActive;

    @FXML
    public TableColumn<User, String> name;

    @FXML
    public TableColumn<User, String> phoneNumber;

    @FXML
    public TableView<User> table;

    private UserDaoService userService;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userService = new UserDaoService();

        name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<User, String>("phoneNumber"));
        isActive.setCellValueFactory(new PropertyValueFactory<User, String>("isActive"));

        loadUsers();


    }

    private void loadUsers() {
        ObservableList<User> users = FXCollections.observableArrayList(userService.getAllUsers());
        table.setItems(users);
    }
}
