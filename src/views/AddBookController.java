package src.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AddBookController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public TextField titleTextField;

    public void addBook(ActionEvent event) throws IOException {
        String title  = titleTextField.getText();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("Main.fxml")));
        root = loader.load();
        MainController mainController = loader.getController();
        mainController.diplayLabel(title);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
