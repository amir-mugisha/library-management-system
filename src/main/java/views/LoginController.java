package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.dao.UserDaoService;
import services.utils.DBUtil;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class LoginController {
    @FXML
    public Button loginButton;
    @FXML
    public Label warningLabel;
    @FXML
    public TextField emailTextField;
    @FXML
    public PasswordField passwordPasswordField;

    final Stage PrimaryStage = new Stage();

    final UserDaoService userService = new UserDaoService(DBUtil.getConnection());

    public void login(ActionEvent event) throws IOException {
        String email = emailTextField.getText();
        String password = passwordPasswordField.getText();

        if(email.isEmpty() || password.isEmpty()){
            warningLabel.setText("Please fill all fields");
            return;
        }

        int count = userService.checkUserExistence(email, password);

       if(count == 0){
                warningLabel.setText("User does not exist");
                return;
        }
        else {
            loadDashboard(PrimaryStage);
        }
    }


    private void loadDashboard(Stage PrimaryStage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
            Parent root = loader.load();

            Stage currentStage = (Stage) loginButton.getScene().getWindow();


            // Set the scene
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();

            // Handle exception (e.g., show error message)
        }
    }
}
