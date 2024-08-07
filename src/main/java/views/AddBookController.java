package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Book;
import services.dao.BookDaoService;
import services.utils.DBUtil;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public BookDaoService bookService;

    @FXML
    public TextField titleTextField;

    @FXML
    public TextField authorTextField;

    @FXML
    public TextField publisherTextField;

    @FXML
    public TextField publicationYearTextField;

    @FXML
    public TextField copiesTextField;

    @FXML
    public TextField isbnTextField;

    @FXML
    public TextField synopsisTextField;

    @FXML
    public Label warningLabel;

    private DashboardController dashboardController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookService = new BookDaoService(DBUtil.getConnection());
        dashboardController = new DashboardController();

    }



    public void addBook(ActionEvent event) throws Exception {
        try{
            String title  = titleTextField.getText();
            String author = authorTextField.getText();
            String publisher = publisherTextField.getText();
            String publicationYear = publicationYearTextField.getText();
            int copies = Integer.parseInt(copiesTextField.getText());
            String isbn = isbnTextField.getText();
            String synopsis = synopsisTextField.getText();

            if(title.isEmpty() || author.isEmpty() || publisher.isEmpty() || publicationYear.isEmpty() || isbn.isEmpty() || synopsis.isEmpty()){
                warningLabel.setText("Please fill all fields");
                return;
            }

            Book book = new Book(title, author, publisher, Integer.parseInt(publicationYear), copies, Long.parseLong(isbn),synopsis);

            bookService.addBook(book);

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

}
