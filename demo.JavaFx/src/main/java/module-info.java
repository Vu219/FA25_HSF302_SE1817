module javafx.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens javafx.javafx to javafx.fxml;
    exports javafx.javafx;
}