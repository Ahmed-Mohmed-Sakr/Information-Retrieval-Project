module MainApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.opennlp.tools;
    requires lucene.analyzers.common;
    requires lucene.core;
    requires lucene.queryparser;


    opens com.MainApp to javafx.fxml;
    exports com.MainApp;
}