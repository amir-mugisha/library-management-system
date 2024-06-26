package src.views;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AddUserController {
    @FXML
    public TextField emailTextField;

    @FXML
    public TextField nameTextField;

    @FXML
    public PasswordField passwordPasswordField;

    @FXML
    public TextField publicationYearTextField;

    @FXML
    public ChoiceBox<?> roleChoiceBox;

    @FXML
    public Label warningLabel;
}
