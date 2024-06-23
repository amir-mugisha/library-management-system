package src.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    Label titleLabel;

    public void displayTitle(String title){
        titleLabel.setText(title);
    }

}
