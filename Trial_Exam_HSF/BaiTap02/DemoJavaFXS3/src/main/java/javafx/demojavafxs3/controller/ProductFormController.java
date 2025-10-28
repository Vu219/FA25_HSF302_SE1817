package javafx.demojavafxs3.controller;

import javafx.demojavafxs3.entity.SonyCategories;
import javafx.demojavafxs3.entity.SonyProducts;
import javafx.demojavafxs3.service.SonyCategoriesService;
import javafx.demojavafxs3.service.SonyProductsService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductFormController {

    private final SonyProductsService sonyProductsService;
    private final SonyCategoriesService sonyCategoriesService;

    @FXML private Label lblTitle;
    @FXML private TextField txtName;
    @FXML private TextField txtPrice;
    @FXML private TextField txtStock;
    @FXML private ComboBox<SonyCategories> comboCategory;
    @FXML private Label lblNameError;
    @FXML private Label lblPriceError;
    @FXML private Label lblStockError;
    @FXML private Label lblCategoryError;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;

    private boolean isEditMode = false;
    private Long editingProductId;
    private ProductController parentController;

    @FXML
    public void initialize() {
        loadCategories();
        addNumericInputValidation(txtPrice);
        addNumericInputValidation(txtStock);

        clearAllErrors();

        setupErrorClearingListeners();
    }


    public void initData(SonyProducts product, ProductController parentController) {
        this.parentController = parentController;

        if (product == null) {
            isEditMode = false;
            lblTitle.setText("Tạo Sản phẩm Mới");
        } else {
            isEditMode = true;
            editingProductId = product.getProductID();
            lblTitle.setText("Chỉnh sửa Sản phẩm");

            txtName.setText(product.getProductName());
            txtPrice.setText(String.valueOf(product.getPrice()));
            txtStock.setText(String.valueOf(product.getStock()));
            comboCategory.setValue(product.getCategory());
        }
    }

    private void loadCategories() {
        List<SonyCategories> categories = sonyCategoriesService.getAllCategories();
        comboCategory.setItems(FXCollections.observableArrayList(categories));
        comboCategory.setConverter(new StringConverter<>() {
            @Override public String toString(SonyCategories c) {
                return (c != null) ? c.getCateName() : "";
            }
            @Override public SonyCategories fromString(String s) {
                return null;
            }
        });

        comboCategory.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                clearError(lblCategoryError);
            }
        });
    }

    @FXML
    private void handleSave() {
        clearAllErrors();

        if (!validateInput()) {
            return;
        }

        try {
            SonyProducts productData = new SonyProducts();
            productData.setProductName(txtName.getText().trim());
            productData.setPrice(Integer.parseInt(txtPrice.getText()));
            productData.setStock(Integer.parseInt(txtStock.getText()));
            productData.setCategory(comboCategory.getValue());

            if (isEditMode) {
                sonyProductsService.updateProduct(editingProductId, productData);
            } else {
                sonyProductsService.addProduct(productData);
            }

            if (parentController != null) {
                parentController.onProductSaved(!isEditMode);
            }

            closeWindow();

        } catch (NumberFormatException e) {
            showError(lblPriceError, "Giá phải là số nguyên hợp lệ.");
        } catch (Exception e) {
            showError(lblNameError, "Lỗi khi lưu: " + e.getMessage());
        }
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }

    private boolean validateInput() {
        boolean isValid = true;

        String name = txtName.getText().trim();
        if (name.isEmpty()) {
            showError(lblNameError, "Tên sản phẩm không được để trống.");
            isValid = false;
        } else if (name.length() < 5 || name.length() > 50) {
            showError(lblNameError, "Tên sản phẩm phải từ 5 đến 50 ký tự.");
            isValid = false;
        }

        String priceText = txtPrice.getText().trim();
        if (priceText.isEmpty()) {
            showError(lblPriceError, "Giá không được để trống.");
            isValid = false;
        } else {
            try {
                int price = Integer.parseInt(priceText);
                if (price < 100) {
                    showError(lblPriceError, "Giá phải lớn hơn hoặc bằng 100.");
                    isValid = false;
                }
            } catch (NumberFormatException e) {
                showError(lblPriceError, "Giá phải là số nguyên hợp lệ.");
                isValid = false;
            }
        }

        String stockText = txtStock.getText().trim();
        if (stockText.isEmpty()) {
            showError(lblStockError, "Tồn kho không được để trống.");
            isValid = false;
        } else {
            try {
                int stock = Integer.parseInt(stockText);
                if (stock < 0) {
                    showError(lblStockError, "Tồn kho phải lớn hơn hoặc bằng 0.");
                    isValid = false;
                } else if (stock > 1000) {
                    showError(lblStockError, "Tồn kho phải nhỏ hơn hoặc bằng 1000.");
                    isValid = false;
                }
            } catch (NumberFormatException e) {
                showError(lblStockError, "Tồn kho phải là số nguyên hợp lệ.");
                isValid = false;
            }
        }

        if (comboCategory.getValue() == null) {
            showError(lblCategoryError, "Vui lòng chọn danh mục.");
            isValid = false;
        }

        return isValid;
    }

    private void setupErrorClearingListeners() {
        txtName.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.trim().isEmpty()) {
                clearError(lblNameError);
            }
        });

        txtPrice.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.trim().isEmpty()) {
                clearError(lblPriceError);
            }
        });

        txtStock.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.trim().isEmpty()) {
                clearError(lblStockError);
            }
        });
    }

    private void addNumericInputValidation(TextField textField) {
        textField.textProperty().addListener((obs, oldV, newV) -> {
            if (newV != null && !newV.matches("\\d*")) {
                textField.setText(oldV);
            }
        });
    }

    private void showError(Label errorLabel, String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
        errorLabel.setManaged(true);
    }

    private void clearError(Label errorLabel) {
        errorLabel.setText("");
        errorLabel.setVisible(false);
        errorLabel.setManaged(false);
    }

    private void clearAllErrors() {
        clearError(lblNameError);
        clearError(lblPriceError);
        clearError(lblStockError);
        clearError(lblCategoryError);
    }
}