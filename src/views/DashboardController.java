package src.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    public StackPane content;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("books.fxml")));
            content.getChildren().removeAll();
            content.getChildren().setAll(root);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addBook(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addBook.fxml")));
            content.getChildren().removeAll();
            content.getChildren().setAll(root);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

public void viewBooks(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("books.fxml")));
            content.getChildren().removeAll();
            content.getChildren().setAll(root);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewUsers(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("users.fxml")));
            content.getChildren().removeAll();
            content.getChildren().setAll(root);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addUser.fxml")));
            content.getChildren().removeAll();
            content.getChildren().setAll(root);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void login(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
            content.getChildren().removeAll();
            content.getChildren().setAll(root);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
