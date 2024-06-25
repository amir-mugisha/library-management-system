package src.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import src.services.dao.UserDaoService;

public class LoginController {
    @FXML
    public Button loginButton;
    @FXML
    public Label warningLabel;
    @FXML
    public TextField emailTextField;
    @FXML
    public PasswordField passwordPasswordField;

    final UserDaoService userService = new UserDaoService();

    public void login(ActionEvent event){
        String email = emailTextField.getText();
        String password = passwordPasswordField.getText();

        int count = userService.checkUserExistence(email);

        if(email.isBlank() && password.isBlank()){
            warningLabel.setText("Please fill all fields");
            return;
        }else if(count == 0){
                warningLabel.setText("User does not exist");
                return;
        }
        else {
            warningLabel.setText("Login successful");
        }
    }
}
