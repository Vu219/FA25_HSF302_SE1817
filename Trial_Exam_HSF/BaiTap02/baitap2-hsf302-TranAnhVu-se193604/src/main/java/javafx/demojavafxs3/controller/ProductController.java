package javafx.demojavafxs3.controller;

import javafx.animation.PauseTransition;
import javafx.demojavafxs3.HelloApplication;
import javafx.demojavafxs3.entity.SonyCategories;
import javafx.demojavafxs3.entity.SonyProducts;
import javafx.demojavafxs3.service.SonyProductsService;
import javafx.demojavafxs3.service.UserSession;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductController implements Initializable {

    private final SonyProductsService sonyProductsService;
    private final UserSession userSession;
    private final ConfigurableApplicationContext springContext; // Cần để tải FXML

    @FXML private Label lblWelcome;
    @FXML private Button btnCreateProduct;
    @FXML private AnchorPane adminPane;
    @FXML private TableView<SonyProducts> productTable;
    @FXML private TableColumn<SonyProducts, Long> colId;
    @FXML private TableColumn<SonyProducts, String> colName;
    @FXML private TableColumn<SonyProducts, Integer> colPrice;
    @FXML private TableColumn<SonyProducts, Integer> colStock;
    @FXML private TableColumn<SonyProducts, String> colCategory;
    @FXML private TableColumn<SonyProducts, String> colCreatedAt;
    @FXML private TableColumn<SonyProducts, Void> colActions;
    @FXML private TreeView<String> topProductsTree;
    @FXML private VBox topProductsPane;

    @FXML private TextField txtSearch;
    @FXML private Button btnSearch;
    @FXML private Button btnClearSearch;
    @FXML private Label lblSearchResults;
    @FXML private Label lblNotification;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!userSession.isLoggedIn()) {
            handleLogout(null);
            return;
        }

        setupRoleBasedUI();
        setupTableColumns();
        loadProductData();

        if (userSession.getRoleID() == 1) {
            loadTopProductsReport();
        }

        setupSearch();
        lblSearchResults.setVisible(false);
        lblNotification.setVisible(false);
    }

    private void setupRoleBasedUI() {
        lblWelcome.setText("Chào: " + userSession.getLoggedInAccount().getPhone());
        boolean isAdmin = (userSession.getRoleID() == 1);

        btnCreateProduct.setVisible(isAdmin);
        colActions.setVisible(isAdmin);
        adminPane.setVisible(isAdmin);
        topProductsPane.setVisible(isAdmin);

        if (isAdmin) {
            lblWelcome.setText("Welcome, Admin - Phone: " + userSession.getLoggedInAccount().getPhone());
        } else {
            lblWelcome.setText("Welcome, Staff - Phone: " + userSession.getLoggedInAccount().getPhone());
        }
    }

    private void setupSearch() {
        btnSearch.setOnAction(e -> handleSearch());
        txtSearch.setOnAction(e -> handleSearch());
        btnClearSearch.setOnAction(e -> {
            txtSearch.clear();
            hideSearchResults();
            loadProductData();
        });

        txtSearch.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.isEmpty()) {
                hideSearchResults();
            }
        });
    }

    @FXML
    private void handleSearch() {
        String keyword = txtSearch.getText().trim();
        if (keyword.isEmpty()) {
            loadProductData();
            hideSearchResults();
            return;
        }

        List<SonyProducts> allProducts = sonyProductsService.getAllProducts();
        List<SonyProducts> filteredProducts = allProducts.stream()
                .filter(product -> product.getProductName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        productTable.setItems(FXCollections.observableArrayList(filteredProducts));

        showSearchResults(keyword, filteredProducts.size());
    }

    private void showSearchResults(String keyword, int resultCount) {
        if (resultCount == 0) {
            lblSearchResults.setText("❌ Không tìm thấy sản phẩm nào với từ khóa: \"" + keyword + "\"");
            lblSearchResults.getStyleClass().setAll("search-result-error");
        } else {
            lblSearchResults.setText("✅ Tìm thấy " + resultCount + " sản phẩm với từ khóa: \"" + keyword + "\"");
            lblSearchResults.getStyleClass().setAll("search-result-success");
        }
        lblSearchResults.setVisible(true);
    }

    private void hideSearchResults() {
        lblSearchResults.setVisible(false);
    }

    private void showNotification(String message, String type) {
        lblNotification.setText(message);
        lblNotification.getStyleClass().setAll("notification-" + type);
        lblNotification.setVisible(true);

        // Tự động ẩn sau 3 giây
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e -> lblNotification.setVisible(false));
        pause.play();
    }

    private void showSuccessNotification(String message) {
        showNotification("✅ " + message, "success");
    }

    private void showErrorNotification(String message) {
        showNotification("❌ " + message, "error");
    }

    private void showInfoNotification(String message) {
        showNotification("ℹ️ " + message, "info");
    }

    private void setupTableColumns() {
        colId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        // Format datetime cho cột CreatedAt
        colCreatedAt.setCellValueFactory(cellData -> {
            LocalDateTime createdAt = cellData.getValue().getCreatedAt();
            String formattedDate = (createdAt != null) ? createdAt.format(dateFormatter) : "N/A";
            return new SimpleStringProperty(formattedDate);
        });

        colCategory.setCellValueFactory(cellData -> {
            SonyCategories category = cellData.getValue().getCategory();
            String categoryName = (category != null) ? category.getCateName() : "N/A";
            return new SimpleStringProperty(categoryName);
        });

        colActions.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Sửa");
            private final Button deleteButton = new Button("Xóa");
            private final HBox pane = new HBox(5, editButton, deleteButton);
            {
                editButton.getStyleClass().add("btn-primary");
                editButton.setOnAction(event -> {
                    SonyProducts product = getTableView().getItems().get(getIndex());
                    handleEditProduct(product);
                });
                deleteButton.getStyleClass().add("btn-secondary");
                deleteButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");
                deleteButton.setOnAction(event -> {
                    SonyProducts product = getTableView().getItems().get(getIndex());
                    handleDeleteProduct(product);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        });
    }

    @FXML
    private void loadProductData() {
        List<SonyProducts> products = sonyProductsService.getAllProducts();
        productTable.setItems(FXCollections.observableArrayList(products));

        if (userSession.getRoleID() == 1) {
            loadTopProductsReport();
        }
    }

    private void loadTopProductsReport() {
        List<SonyProducts> topProducts = sonyProductsService.getTop3ProductsByCategory();
        Map<String, List<SonyProducts>> groupedProducts = topProducts.stream()
                .filter(p -> p.getCategory() != null)
                .collect(Collectors.groupingBy(p -> p.getCategory().getCateName()));

        TreeItem<String> root = new TreeItem<>("Top 3 Sản phẩm theo Danh mục");
        root.setExpanded(true);

        root.getValue();

        for (Map.Entry<String, List<SonyProducts>> entry : groupedProducts.entrySet()) {
            TreeItem<String> categoryItem = new TreeItem<>(entry.getKey());
            categoryItem.setExpanded(true);

            for (SonyProducts product : entry.getValue()) {
                String productInfo = String.format("%s - Giá: $%d - Tồn: %d",
                        product.getProductName(), product.getPrice(), product.getStock());
                TreeItem<String> productItem = new TreeItem<>(productInfo);
                categoryItem.getChildren().add(productItem);
            }
            root.getChildren().add(categoryItem);
        }
        topProductsTree.setRoot(root);

        topProductsTree.getStyleClass().add("tree-view");

        applyTreeViewStyles();
    }

    private void applyTreeViewStyles() {
        topProductsTree.setCellFactory(tv -> new TreeCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                    getStyleClass().removeAll("tree-cell-root", "tree-cell-category", "tree-cell-product");
                } else {
                    setText(item);

                    getStyleClass().removeAll("tree-cell-root", "tree-cell-category", "tree-cell-product");

                    TreeItem<String> treeItem = getTreeItem();
                    if (treeItem != null) {
                        if (treeItem.getParent() == null) {
                            // Root item
                            getStyleClass().add("tree-cell-root");
                        } else if (treeItem.getParent().getParent() == null) {
                            // Category item
                            getStyleClass().add("tree-cell-category");
                        } else {
                            // Product item
                            getStyleClass().add( "tree-cell-product");
                        }
                    }
                }
            }
        });
    }

    @FXML
    private void handleCreateProduct() {
        showProductFormModal(null);
    }

    private void handleEditProduct(SonyProducts product) {
        showProductFormModal(product);
    }

    private void showProductFormModal(SonyProducts product) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("product-form-view.fxml"));
            fxmlLoader.setControllerFactory(springContext::getBean);
            Parent root = fxmlLoader.load();

            ProductFormController formController = fxmlLoader.getController();
            formController.initData(product, this); // Truyền reference đến controller cha

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initOwner(productTable.getScene().getWindow());
            modalStage.setTitle(product == null ? "Tạo Sản phẩm Mới" : "Chỉnh sửa Sản phẩm");
            modalStage.setScene(new Scene(root));
            modalStage.setResizable(false);

            modalStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            showErrorNotification("Lỗi khi mở form sản phẩm!");
        }
    }

    public void onProductSaved(boolean isNewProduct) {
        loadProductData();
        if (isNewProduct) {
            showSuccessNotification("Tạo sản phẩm mới thành công!");
        } else {
            showSuccessNotification("Chỉnh sửa sản phẩm thành công!");
        }
    }

    private void handleDeleteProduct(SonyProducts product) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận Xóa");
        alert.setHeaderText("Bạn có chắc muốn xóa sản phẩm này?");
        alert.setContentText(product.getProductName());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                sonyProductsService.deleteProduct(product.getProductID());
                loadProductData();
                showSuccessNotification("Xóa sản phẩm thành công!");
            } catch (Exception e) {
                showErrorNotification("Lỗi khi xóa sản phẩm: " + e.getMessage());
            }
        }
    }

    @FXML
    private void handleRefresh() {
        loadProductData();
        hideSearchResults();
        txtSearch.clear();
        showInfoNotification("Đã làm mới dữ liệu!");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        userSession.clearSession();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
            fxmlLoader.setControllerFactory(springContext::getBean);
            Parent root = fxmlLoader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Sony Login");
            loginStage.setScene(new Scene(root));

            Stage productStage = (Stage) productTable.getScene().getWindow();
            productStage.close();

            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}