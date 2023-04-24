package com.MainApp;

import com.algorithms.IncidenceMatrix;
import com.algorithms.IndexesFactory;
import com.algorithms.Lucene_Searcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class SearchingScene_Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    private TextField searchInputField;
    @FXML
    private TextArea searchOutputField;
    @FXML
    private Button SearchButton;
    @FXML
    private ChoiceBox<String> chooseIndexToSearch;

    private String[] idixingWayes = {"Lucene", "Term-document",
            "Incidence-matrix", "Inverted-index", "Positional-index", "Bi-word-index"};


    /**
     * index it search in form gui
     *
     */
    // text that we need to search for.
    String SearchText = "";
    String SearchResult = "";
    String IndexWay = "Lucene";
 //  Map<String, String> searchingData = new HashMap<String, String>() {
 //      {
 //          put("IndexWay","Lucene");
 //      }
 //  };

    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseIndexToSearch.getItems().addAll(idixingWayes);
        chooseIndexToSearch.setOnAction(this::getIndexWay);
    }
    public void getIndexWay(ActionEvent e) {
        String mySelectedIndexWay = chooseIndexToSearch.getValue();

        if(mySelectedIndexWay.isEmpty())
            return;
        IndexWay = mySelectedIndexWay;
        System.out.println(mySelectedIndexWay);
    }


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
        if(IndexWay.equals("Incidence-matrix")) {
            Map<String, List<Boolean>> matrix = IndexesFactory.getIncidenceMatrix();

            try {
                if (matrix.get(SearchText) != null) {
                    List<Boolean> list = matrix.get(SearchText);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) == true) {
                            SearchResult += "data found in doucument number" + (i + 1) + "\n";
                        }
                    }
                }
            } catch (Exception exception) {
                System.out.println("error in fetching data: \n" + exception);

                SearchResult = "Error in Showing Data :(";
            }
        }else if(IndexWay.equals("Lucene")){
            try {
             int hits =   Lucene_Searcher.searcher(SearchText);
             SearchResult = "Found " + hits +
                     " document(s) that matched query '" + SearchText + "'";
            } catch (IOException | ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
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
