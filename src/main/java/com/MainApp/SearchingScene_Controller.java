package com.MainApp;

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
import com.MainApp.SearchIncidenceMatrix;
import com.MainApp.DivieToTokens;
import java.io.IOException;
import java.net.URL;
import java.util.*;

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

    // text that we need to search for.
    String SearchText = "";
    String SearchResult = "";
    String IndexWay = "Lucene";

    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseIndexToSearch.getItems().addAll(idixingWayes);
        chooseIndexToSearch.setOnAction(this::getIndexWay);
    }

    public void getIndexWay(ActionEvent e) {
        String mySelectedIndexWay = chooseIndexToSearch.getValue();

        if (mySelectedIndexWay.isEmpty())
            return;
        IndexWay = mySelectedIndexWay;
        System.out.println(mySelectedIndexWay);
    }


    public void onSearchButtonClicked(ActionEvent e) {
        // here we get string that we need to search for
        SearchResult = "";
        SearchText = searchInputField.getText().trim().toLowerCase();

        if (SearchText.isEmpty()) {
            return;
        }

        if (IndexWay.equals("Incidence-matrix")) {
            try{

                var ans = SearchIncidenceMatrix.Search( SearchText );
                for (int i = 0; i < ans.size(); i++) {
                    SearchResult += "data found in doucument number --> " + (ans.get(i) + 1) + "\n";
                }
            } catch (Exception exception) {
                System.out.println("error in fetching data: \n" + exception);
                SearchResult = "Error in Showing Data :(";
            }
        } else if (IndexWay.equals("Lucene")) {
            SearchResult = "" ;
            ArrayList<Integer> ans = SearchLucene.Search(SearchText);

            SearchResult = "Found " + ans.size() +
                    " document(s) that matched query '" + SearchText + "':";
            for (var id:ans) {
                SearchResult+=("\n"+"docID :"+String.valueOf(id)) ;
            }

        }
          else if (IndexWay.equals("Inverted-index")) {

            try{
                var ans = SearchInverted.Search(SearchText);
                for (int i = 0; i < ans.size(); i++) {
                    SearchResult += "data found in doucument number --> " + (ans.get(i)+1) + "\n";
                }

            } catch (Exception exception) {
                SearchResult = "Error in Showing Data :(";
            }
        }

         else if(IndexWay.equals("Positional-index")){
//            var matrix= IndexesFactory.getPositionalIndex(); // (get posResult) term:word docId: 1 [pos1, pos2]
//
//            for (Map.Entry<String, Map<Integer, List<Integer>>> entry : matrix.entrySet()) {
//                String key = entry.getKey();
//                Map<Integer, List<Integer>> innerMap = entry.getValue();
//                System.out.print(key + ":");
//                for (Map.Entry<Integer, List<Integer>> innerEntry : innerMap.entrySet()) {
//                    Integer innerKey = innerEntry.getKey();
//
//                    List<Integer> innerList = innerEntry.getValue();
//                    System.out.print(" " + innerKey + ": " + innerList);
//                }
//                System.out.println();
//            }

            //implement searchCode Here
            try{
                var ans = SearchPositionalIndex.Search(SearchText);
                for (int i = 0; i < ans.size(); i++) {
                    SearchResult += "data found in doucument number --> " + (ans.get(i)+1) + "\n";
                }

            } catch (Exception exception) {
                SearchResult = "Error in Showing Data :(";
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
