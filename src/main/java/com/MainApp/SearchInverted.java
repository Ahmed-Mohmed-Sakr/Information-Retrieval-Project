package com.MainApp;

import com.algorithms.IndexesFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchInverted {
    public static List<Integer> SearchInverted(String Token) {

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

    public static List<Integer> Search( String SearchText ){

        List<Integer> ans = new ArrayList<>();
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
            var words = DivieToTokens.Tokens(SearchText);

            System.out.println("words in Token equal = ");
            for (int i = 0; i < words.size(); i++) {
                System.out.println(words.get(i));
            }
            System.out.println("-------------------------------------");

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
        } catch (Exception e) {
            System.out.println("error in fetching data: \n" + e);
//            SearchResult = "Error in Showing Data :(";
            throw new RuntimeException(e);
        }

        return ans ;
    }
}
