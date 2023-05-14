package com.MainApp;

import com.DataSet.ReadData;
import com.algorithms.IncidenceMatrix;
import com.algorithms.IndexesFactory;
import com.algorithms.Lucene_Indexer;
import com.preprocessing.Preprocessing;
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
import java.util.*;

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

    public void onDoneButtonClicked(ActionEvent e) throws Exception {

        try{
            String []data= ReadData.readDocuments("D:\\fourth-year\\Second Semster\\IR\\Project\\Information-Retrieval-Project\\archive\\CISI.ALL");

            ArrayList<List<String>> cleanedData=new ArrayList<>();
        int z1 =0;
            for(var d:data){
                if(z1==100)
                    break;
                cleanedData.add(Preprocessing.preprocess(d,indixingData.get("StopWords").equals("1"),
                        indixingData.get("Normalization").equals("1"),
                        indixingData.get("Steaming").equals("1"),
                        indixingData.get("Lemetization").equals("1")));
                z1++;
            }

            if(indixingData.get("IndexWay")=="Incidence-matrix") {
                var matrix =  IndexesFactory.setIncidenceMatrix(cleanedData);
                matrix.forEach((x, list) -> {
                    System.out.print(x + "  ");
                    list.forEach(z -> System.out.print(z + " "));
                    System.out.println();
                });
            }else if(indixingData.get("IndexWay")=="Lucene"){
                Lucene_Indexer.Indexer();
            }else if(indixingData.get("IndexWay")=="Inverted-matrix"){
                var matrix= IndexesFactory.setInvertedIndex(cleanedData);
                matrix.forEach((x,list)->{
                    System.out.print(x + "  ");
                    list.forEach(z -> System.out.print(z + " "));
                    System.out.println();
                });
            }else if(indixingData.get("IndexWay")=="Positional-index"){
               var matrix= IndexesFactory.setPositionalIndex(cleanedData);

            }
        }catch (Exception ex){
            System.out.println(ex);
        }

        fxmlLoader = new FXMLLoader(mainApp.class.getResource("SearchingScene.fxml"));
        scene = new Scene(fxmlLoader.load());

        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
