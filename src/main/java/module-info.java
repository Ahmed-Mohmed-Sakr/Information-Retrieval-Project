module MainApp {
    requires javafx.controls;
    requires javafx.fxml;


    opens MainApp to javafx.fxml;
    exports MainApp;
}