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

    public List<Integer> SearchPls(String Token) {

        List<Integer> ans = new ArrayList<>();
        Map<String, List<Boolean>> matrix = IndexesFactory.getIncidenceMatrix();
        if (matrix.get(Token) != null) {
            List<Boolean> list = matrix.get(Token);

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == true) {
//                    SearchResult += "data found in doucument number" + (i + 1) + "\n";
                    ans.add(i);
                }
            }
        }
        return ans;
    }

    public List<Integer> SearchInverted(String Token) {

        List<Integer> ans = new ArrayList<>();
        Map<String, List<Integer>> matrix = IndexesFactory.getInvertedIndex();
        Map<String, Map<Integer, List<Integer>>> matix= null;
        if (matrix.get(Token) != null) {
            List<Integer> list = matrix.get(Token);

            for (int i = 0; i < list.size(); i++) {

                    ans.add(list.get(i));

            }
        }
        return ans;
    }

    public List<String> Tokens() {
        List<String> ret = new ArrayList<>();
        String cur = "";
        for (int i = 0; i < SearchText.length(); i++) {
            if (cur.length() >= 1 && (SearchText.charAt(i) == '&' || SearchText.charAt(i) == '|')) {
                ret.add(cur);
                cur = "";
                continue;
            }
            cur += SearchText.charAt(i);
        }

        if (cur.length() >= 1)
            ret.add(cur);
        return ret;
    }


    public ArrayList<Integer> SearchLucene(String Token) throws IOException, ParseException {
        ArrayList<Integer> ret = Lucene_Searcher.searcher(Token);
        return ret;
    }

    public void onSearchButtonClicked(ActionEvent e) {
        // here we get string that we need to search for
        SearchResult = "";
        SearchText = searchInputField.getText().trim().toLowerCase();

        if (SearchText.isEmpty()) {
            return;
        }

        if (IndexWay.equals("Incidence-matrix")) {
            Map<String, List<Boolean>> matrix = IndexesFactory.getIncidenceMatrix();

            boolean AND = false, OR = false;
            List<String> needs = new ArrayList<>();

            for (int i = 0; i < SearchText.length(); i++) {
                if (SearchText.charAt(i) == '&')
                    AND = true;

                if (SearchText.charAt(i) == '|')
                    OR = true;
            }

            try {
                var words = Tokens();

                System.out.println("words in Token equal = ");
                for (int i = 0; i < words.size(); i++) {
                    System.out.println(words.get(i));
                }
                System.out.println("-------------------------------------");
                List<Integer> ans = new ArrayList<>();

                if (AND == false && OR == false) {
                    var ret = SearchPls(words.get(0));
                    ans = ret;

                } else if (AND == true) {

                    // AND Operation ;
                    var ret1 = SearchPls(words.get(0));
                    var ret2 = SearchPls(words.get(1));


                    for (int i = 0; i < ret1.size(); i++) {
                        for (int j = 0; j < ret2.size(); j++) {
                            if (ret1.get(i) == ret2.get(j)) {
                                ans.add(ret1.get(i));
                                break;
                            }
                        }
                    }


                } else {
                    // OR Operation ;
                    var ret1 = SearchPls(words.get(0));
                    var ret2 = SearchPls(words.get(1));

                    ans = new ArrayList<>(ret1);
                    for (Integer element : ret2) { // Iterate over ret2
                        if (!ans.contains(element)) { // Check if element is not already present in the mergedList
                            ans.add(element); // Add element to the mergedList
                        }
                    }
                }

                for (int i = 0; i < ans.size(); i++) {
                    SearchResult += "data found in doucument number --> " + (ans.get(i) + 1) + "\n";
                }

            } catch (Exception exception) {
                System.out.println("error in fetching data: \n" + exception);
                SearchResult = "Error in Showing Data :(";
            }
        } else if (IndexWay.equals("Lucene")) {
            try {
                SearchResult = "" ;
                boolean AND = false, OR = false;
                List<String> needs = new ArrayList<>();

                for (int i = 0; i < SearchText.length(); i++) {
                    if (SearchText.charAt(i) == '&')
                        AND = true;

                    if (SearchText.charAt(i) == '|')
                        OR = true;
                }
                ArrayList<Integer> ans = new ArrayList<>();

                var words = Tokens();
                if (AND == false && OR == false) {
                    ans = SearchLucene(words.get(0));
                } else if (OR == true) {
                    ArrayList<Integer> ret1 = SearchLucene(words.get(0));
                    ArrayList<Integer> ret2 = SearchLucene(words.get(1));

                    ans.addAll(ret1);
                    for (Integer element : ret2) {
                        if (!ans.contains(element)) {
                            ans.add(element);
                        }
                    }
                }else{
                    ArrayList<Integer> ret1 = SearchLucene(words.get(0));
                    ArrayList<Integer> ret2 = SearchLucene(words.get(1));

                    for (int i = 0; i < ret1.size(); i++) {
                        for (int j = 0; j < ret2.size(); j++) {
                            if (ret1.get(i) == ret2.get(j)) {
                                ans.add(ret1.get(i));
                                break;
                            }
                        }
                    }

                }

                SearchResult = "Found " + ans.size() +
                        " document(s) that matched query '" + SearchText + "':";
                for (var id:ans) {
                    SearchResult+=("\n"+"docID :"+String.valueOf(id)) ;
                }

            } catch (IOException | ParseException ex) {
                throw new RuntimeException(ex);
            }
        }else if (IndexWay.equals("Inverted-index")) {
            //
            Map<String, List<Integer>> matrix = IndexesFactory.getInvertedIndex();
            boolean AND = false, OR = false;
            List<String> needs = new ArrayList<>();

            for (int i = 0; i < SearchText.length(); i++) {
                if (SearchText.charAt(i) == '&')
                    AND = true;

                if (SearchText.charAt(i) == '|')
                    OR = true;
            }

            try {
                var words = Tokens();

                System.out.println("words in Token equal = ");
                for (int i = 0; i < words.size(); i++) {
                    System.out.println(words.get(i));
                }
                System.out.println("-------------------------------------");
                List<Integer> ans = new ArrayList<>();

                if (AND == false && OR == false) {
                    var ret = SearchInverted(words.get(0));
                    ans = ret;

                } else if (AND == true) {

                    // AND Operation ;
                    var ret1 = SearchInverted(words.get(0));
                    var ret2 = SearchInverted(words.get(1));


                    for (int i = 0; i < ret1.size(); i++) {
                        for (int j = 0; j < ret2.size(); j++) {
                            if (ret1.get(i) == ret2.get(j)) {
                                ans.add(ret1.get(i));
                                break;
                            }
                        }
                    }


                } else {
                    // OR Operation ;
                    var ret1 = SearchInverted(words.get(0));
                    var ret2 = SearchInverted(words.get(1));

                    ans = new ArrayList<>(ret1);
                    for (Integer element : ret2) { // Iterate over ret2
                        if (!ans.contains(element)) { // Check if element is not already present in the mergedList
                            ans.add(element); // Add element to the mergedList
                        }
                    }
                }

                for (int i = 0; i < ans.size(); i++) {
                    SearchResult += "data found in doucument number --> " + (ans.get(i)+1) + "\n";
                }

            } catch (Exception exception) {
                System.out.println("error in fetching data: \n" + exception);
                SearchResult = "Error in Showing Data :(";
            }
        }else if(IndexWay.equals("Positional-index")){
            var matrix= IndexesFactory.getPositionalIndex(); // (get posResult) term:word docId: 1 [pos1, pos2]
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
