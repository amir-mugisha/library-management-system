package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Book;
import services.User;
import services.dao.UserDaoService;
import services.utils.DBUtil;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {
    private UserDaoService userService;
    private DashboardController dashboardController;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public TextField emailTextField;

    @FXML
    public TextField nameTextField;

    @FXML
    public PasswordField passwordPasswordField;

    @FXML
    public TextField phoneNumberTextField;

    @FXML
    public ChoiceBox<?> roleChoiceBox;

    @FXML
    public Label warningLabel;

    public void addUser(ActionEvent event) throws Exception {
        try{
            String email  = emailTextField.getText();
            String name = nameTextField.getText();
            String password = passwordPasswordField.getText();
            String phoneNumber = phoneNumberTextField.getText();

            if(email.isEmpty() || name.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()){
                warningLabel.setText("Please fill all fields");
                return;
            }

            User user = new User(name, email, password, phoneNumber);

            userService.addUser(user);

            Parent dashboard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(dashboard);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
            warningLabel.setText("An error occurred");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userService = new UserDaoService(DBUtil.getConnection());
        dashboardController = new DashboardController();
    }
}
