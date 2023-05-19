package com.MainApp;

import com.algorithms.IndexesFactory;

import java.util.*;

public class SearchPositionalIndex {

    public static ArrayList<Integer> SearchYaLeazy(String Token) {
        var matrix = IndexesFactory.getPositionalIndex();
        ArrayList<Integer> result = new ArrayList<>();

        for (Map.Entry<String, Map<Integer, List<Integer>>> entry : matrix.entrySet()) {
            String key = entry.getKey();
            Map<Integer, List<Integer>> innerMap = entry.getValue();
           // System.out.print(key + ":");
            if (Token.equals(key)) {
                for (Map.Entry<Integer, List<Integer>> innerEntry : innerMap.entrySet()) {
                    Integer innerKey = innerEntry.getKey();
                    List<Integer> innerList = innerEntry.getValue();
//                    System.out.print(" " + innerKey + ": " + innerList);

                    result.add(innerKey);
                }
            }
        }
        return result;
    }

    public static List<Integer> searchPhrase(String query) {

        Map<String, Map<Integer, List<Integer>>> positionalIndex = IndexesFactory.getPositionalIndex();
        String[] terms = query.split(" ");
        List<Integer> result = new ArrayList<>();

        if (terms.length == 0) {
            return result;
        }

        Map<Integer, List<Integer>> firstTermPositions = positionalIndex.getOrDefault(terms[0], Collections.emptyMap());
        for (Integer docId : firstTermPositions.keySet()) {
            List<Integer> positions = firstTermPositions.get(docId);
            for (Integer pos : positions) {
                boolean found = true;
                for (int i = 1; i < terms.length; i++) {
                    Map<Integer, List<Integer>> nextTermPositions = positionalIndex.getOrDefault(terms[i], Collections.emptyMap());
                    List<Integer> nextPositions = nextTermPositions.getOrDefault(docId, Collections.emptyList());
                    if (!nextPositions.contains(pos + i)) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    result.add(docId);
                    break;
                }
            }
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
//            var words = DivieToTokens.Tokens(SearchText);

            List<String> Words = DivieToTokens.Tokens(SearchText);

            var words = SearchingFilters.FliterHere( Words );

            System.out.println("words in Token equal = ");
            for (int i = 0; i < words.size(); i++) {
                System.out.println(words.get(i));
            }
            System.out.println("-------------------------------------");
            if( words.size() > 2 ){
                var ret = searchPhrase( SearchText );
                ans = ret;
            }
            else if (AND == false && OR == false) {
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
