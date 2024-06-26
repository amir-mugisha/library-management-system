package src.views;

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
import src.services.dao.UserDaoService;

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

    final UserDaoService userService = new UserDaoService();

    public void login(ActionEvent event) throws IOException {
        String email = emailTextField.getText();
        String password = passwordPasswordField.getText();

        if(email.isBlank() || password.isBlank()){
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

            DashboardController dashboardController = loader.getController();
            dashboardController.initialize(null,null);

            // Set the scene
            Scene scene = new Scene(root);
            PrimaryStage.setScene(scene);
            PrimaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();

            // Handle exception (e.g., show error message)
        }
    }
}
