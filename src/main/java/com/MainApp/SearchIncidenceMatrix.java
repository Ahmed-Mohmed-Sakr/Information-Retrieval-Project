package com.MainApp;

import com.algorithms.IndexesFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchIncidenceMatrix {
    public static String test = "Yousef";


    public static List<Integer> SearchPls(String Token) {

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
    public static List<Integer> Search( String SearchText ) {
        Map<String, List<Boolean>> matrix = IndexesFactory.getIncidenceMatrix();

        boolean AND = false, OR = false;
        List<String> needs = new ArrayList<>();

        for (int i = 0; i < SearchText.length(); i++) {
            if (SearchText.charAt(i) == '&')
                AND = true;

            if (SearchText.charAt(i) == '|')
                OR = true;
        }

        List<Integer> ans;
        try {
//                var words = Tokens();
//            var words = DivieToTokens.Tokens(SearchText);

            List<String> Words = DivieToTokens.Tokens(SearchText);
            var words = SearchingFilters.FliterHere( Words );

            System.out.println("words in Token equal = ");
            for (int i = 0; i < words.size(); i++) {
                System.out.println(words.get(i));
            }
            System.out.println("-------------------------------------");
            ans = new ArrayList<>();

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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ans;
    }
}
