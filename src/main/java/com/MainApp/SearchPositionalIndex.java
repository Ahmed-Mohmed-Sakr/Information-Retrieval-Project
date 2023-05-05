package com.MainApp;

import com.algorithms.IndexesFactory;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchPositionalIndex {

    public static ArrayList<Integer> SearchYaLeazy(String Token) {
        var matrix = IndexesFactory.getPositionalIndex();
        ArrayList<Integer> result = new ArrayList<>();

        for (Map.Entry<String, Map<Integer, List<Integer>>> entry : matrix.entrySet()) {
            String key = entry.getKey();
            Map<Integer, List<Integer>> innerMap = entry.getValue();
            System.out.print(key + ":");

            if (Token.equals(key)) {
                for (Map.Entry<Integer, List<Integer>> innerEntry : innerMap.entrySet()) {
                    Integer innerKey = innerEntry.getKey();
                    List<Integer> innerList = innerEntry.getValue();
                    System.out.print(" " + innerKey + ": " + innerList);

                    result.add(innerKey);
                }
            }

            System.out.println();
        }

        return result;
    }


    public static List<Integer> Search( String SearchText ){

        List<Integer> ans = new ArrayList<>();
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
                var ret = SearchYaLeazy(words.get(0));
                ans = ret;

            } else if (AND == true) {

                // AND Operation ;
                var ret1 = SearchYaLeazy(words.get(0));
                var ret2 = SearchYaLeazy(words.get(1));


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
                var ret1 = SearchYaLeazy(words.get(0));
                var ret2 = SearchYaLeazy(words.get(1));

                ans = new ArrayList<>(ret1);
                for (Integer element : ret2) { // Iterate over ret2
                    if (!ans.contains(element)) { // Check if element is not already present in the mergedList
                        ans.add(element); // Add element to the mergedList
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("error in fetching data: \n" + e);
            throw new RuntimeException(e);
        }

        return ans ;
    }

}
