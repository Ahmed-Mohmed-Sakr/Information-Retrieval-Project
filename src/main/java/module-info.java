module MainApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.opennlp.tools;


    opens com.MainApp to javafx.fxml;
    exports com.MainApp;
}