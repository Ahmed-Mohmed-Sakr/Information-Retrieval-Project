module MainApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.opennlp.tools;


    opens MainApp to javafx.fxml;
    exports MainApp;
}