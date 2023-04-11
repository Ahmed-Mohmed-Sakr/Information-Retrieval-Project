package MainApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class IndexingScene_Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    private CheckBox Lemetization;
    @FXML
    private CheckBox Normalization;
    @FXML
    private CheckBox Steaming;
    @FXML
    private CheckBox StopWords;
    @FXML
    private CheckBox Tokenization;
    @FXML
    private ChoiceBox<String> chooseIndex;

    private String[] idixingWayes = {"Lucene", "Term-document",
            "Incidence-matrix", "Inverted-matrix", "Positional-index", "Bi-word-index"};

    /**
     * Tawfik: Just take that data from that file :) (indixingData)
     *
     * look below to see were to call it (look to the ed of file)
     */
    Map<String, String> indixingData = new HashMap<String, String>() {
        {
            put("IndexWay","Lucene");
            put("Lemetization", "0");
            put("Normalization", "0");
            put("Steaming", "0");
            put("StopWords", "0");
            put("Tokenization", "0");
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseIndex.getItems().addAll(idixingWayes);
        chooseIndex.setOnAction(this::getIndexWay);
    }
    public void getIndexWay(ActionEvent e) {
        String mySelectedIndexWay = chooseIndex.getValue();

        if(mySelectedIndexWay.isEmpty())
            return;
        indixingData.put("IndexWay",mySelectedIndexWay);
        System.out.println(mySelectedIndexWay);
    }


    @FXML
    void onLemetizationChange(ActionEvent event) {
        if (Lemetization.isSelected())
            indixingData.put("Lemetization", "1");
        else
            indixingData.put("Lemetization", "0");
    }

    @FXML
    void onNormalizationChange(ActionEvent event) {
        if (Normalization.isSelected())
            indixingData.put("Normalization", "1");
        else
            indixingData.put("Normalization", "0");
    }

    @FXML
    void onSteamingChange(ActionEvent event) {
        if (Steaming.isSelected())
            indixingData.put("Steaming", "1");
        else
            indixingData.put("Steaming", "0");
    }

    @FXML
    void onStopWordsChange(ActionEvent event) {
        if (StopWords.isSelected())
            indixingData.put("StopWords", "1");
        else
            indixingData.put("StopWords", "0");
    }

    @FXML
    void onTokenizationChange(ActionEvent event) {
        if (Tokenization.isSelected())
            indixingData.put("Tokenization", "1");
        else
            indixingData.put("Tokenization", "0");
    }

    public void onDoneButtonClicked(ActionEvent e) throws IOException {

        /**
         * FOCUS HERE Tawfik ==>
         *
         * you need to take indixingData only and do indixing with it here
         */

        fxmlLoader = new FXMLLoader(mainApp.class.getResource("SearchingScene.fxml"));
        scene = new Scene(fxmlLoader.load());

        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
