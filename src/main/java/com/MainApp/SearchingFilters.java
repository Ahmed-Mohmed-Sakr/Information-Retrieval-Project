package com.MainApp;

import com.preprocessing.Preprocessing;

import java.util.ArrayList;
import java.util.List;

public class SearchingFilters {
    public static List<String> FliterHere( List<String> words ) throws Exception {

        System.out.println( words );

        List<String> ans = new ArrayList<>() ;
        ArrayList<String> wordFilter = new ArrayList<>();
        boolean Normalization = SearchingScene_Controller.SearchingFilters.get("Normalization").equals("1");
        boolean Steaming = SearchingScene_Controller.SearchingFilters.get("Steaming").equals("1");
        boolean Lemetization = SearchingScene_Controller.SearchingFilters.get("Lemetization").equals("1");

        for(var word: words){
            wordFilter = Preprocessing.preprocess(word,false,Normalization,Steaming,Lemetization);
            System.out.println(wordFilter);
            ans.add(wordFilter.get(0));
        }
        for(var word: ans){
            System.out.println(word);
        }
        return ans;
    }
}
