package MainApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import MainApp.mainApp;

import java.io.IOException;
import javafx.scene.Parent;

public class mainApp  extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainApp.class.getResource("configrationScene.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load());

        stage.setResizable(false);
        stage.setTitle("IR 2023!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
