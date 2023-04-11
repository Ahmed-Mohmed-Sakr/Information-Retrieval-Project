package MainApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchingScene_Controller {

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    private TextField searchInputField;
    @FXML
    private TextArea searchOutputField;
    @FXML
    private Button SearchButton;

    // text that we need to search for.
    String SearchText = "";
    String SearchResult = "";

    public void onSearchButtonClicked(ActionEvent e) {
        // here we get string that we need to search for
        SearchText = searchInputField.getText().trim();

        if(SearchText.isEmpty()) {
            return;
        }

        /**
         * take care Tawfik here :)
         *
         * -here you will send text and get result then pass it to searchOutputField line by line.
         *
         * -you will Write
         * SearchResult = YOUR_Search_Result.
         */

        if (SearchResult.isEmpty())
            SearchResult = "NO DATA TO SHOW :(";

        searchOutputField.clear();
        searchOutputField.setText(SearchResult);
    }

    public void onBackButtonClicked(ActionEvent e) throws IOException {
        fxmlLoader = new FXMLLoader(mainApp.class.getResource("configrationScene.fxml"));
        scene = new Scene(fxmlLoader.load());

        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
