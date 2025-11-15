package javafx.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    protected void onHelloButtonClick() {
        if(txtUsername.getText().equals("admin") && txtPassword.getText().equals("admin")) {
            welcomeText.setText("Welcome: " + txtUsername.getText());
        } else {
            welcomeText.setText("Input Username and Password");
        }
    }
}
