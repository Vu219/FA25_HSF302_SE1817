package javafx.demojavafxs3.controller;

import javafx.demojavafxs3.HelloApplication;
import javafx.demojavafxs3.entity.SonyAccounts;
import javafx.demojavafxs3.service.SonyAccountsService;
import javafx.demojavafxs3.service.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginController {

    private final SonyAccountsService sonyAccountsService;
    private final UserSession userSession;
    private final ConfigurableApplicationContext springContext;

    @FXML
    private Label messageText;
    @FXML
    private TextField txtPhone;
    @FXML
    private PasswordField txtPassword;

    @FXML
    protected void onLoginButtonClick() {
        String phone = txtPhone.getText();
        String password = txtPassword.getText();

        // Clear previous message
        clearMessage();

        if (phone.isEmpty() || password.isEmpty()) {
            showMessage("Vui lòng nhập số điện thoại và mật khẩu.");
            return;
        }

        SonyAccounts account = sonyAccountsService.getAccount(phone, password);

        if (account == null) {
            showMessage("Số điện thoại hoặc mật khẩu sai!");
        } else {
            // Clear message on success
            clearMessage();
            userSession.setLoggedInAccount(account);

            if (account.getRoleID() == 1 || account.getRoleID() == 2) {
                showProductWindow();
            } else {
                userSession.clearSession();
                showAlert(Alert.AlertType.ERROR, "Truy cập bị từ chối", "Bạn không có quyền truy cập chức năng này.");
                showMessage("Bạn không có quyền truy cập.");
            }
        }
    }

    private void showMessage(String message) {
        messageText.setText(message);
        messageText.setVisible(true);
        messageText.setManaged(true);
    }

    private void clearMessage() {
        messageText.setText("");
        messageText.setVisible(false);
        messageText.setManaged(false);
    }

    private void showProductWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("product-view.fxml"));
            fxmlLoader.setControllerFactory(springContext::getBean);
            Parent root = fxmlLoader.load();

            Stage productStage = new Stage();
            productStage.setTitle("Sony Product Management");
            productStage.setScene(new Scene(root));
            productStage.setMaximized(true);

            Stage loginStage = (Stage) txtPhone.getScene().getWindow();
            loginStage.close();

            productStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể tải màn hình quản lý sản phẩm.");
        }
    }

    @FXML
    public void onCancelClick(ActionEvent actionEvent) {
        System.exit(0);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}