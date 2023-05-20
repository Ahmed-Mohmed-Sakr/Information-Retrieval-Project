package com.MainApp;

import com.algorithms.IndexesFactory;
import com.algorithms.BywordIndex;

import java.util.*;

public class SearchBiWordIndex {

    public static List<Integer> SearchBiWord(String Token) {

        var allToken = IndexesFactory.getBywordIndex();
        return BywordIndex.search( Token , allToken );
    }

    public static List<Integer> SearchBiWordSatr(String Token) {
        var allToken = IndexesFactory.getBywordIndex();
        return BywordIndex.searchStar( Token , allToken );
    }

    public static List<Integer> SearchBiWordEnd(String Token) {
        var allToken = IndexesFactory.getBywordIndex();
        return BywordIndex.searchEndWith( Token , allToken );
    }

    public static boolean AND = false, OR = false;
    public static int star = -1 ;
    public static void AndOr( String SearchText ){
        for (int i = 0; i < SearchText.length(); i++) {
            if (SearchText.charAt(i) == '&')
                AND = true;

            if (SearchText.charAt(i) == '|')
                OR = true;

            if (SearchText.charAt(i) == '*')
                star = i;

        }
    }

    public static List<Integer> Search( String SearchText ){

        AND = false; OR = false;  star = -1;
        AndOr( SearchText );
        List<Integer> ans = new ArrayList<>();

        try {

            System.out.println( SearchText );
            List<String> words = DivieToTokens.Tokens(SearchText);
//            var words = SearchingFilters.FliterHere( Words );

            System.out.println("words in Token equal = " + words.size() );
            for (int i = 0; i < words.size(); i++) {
                System.out.println(words.get(i));
            }

            if( star != -1 ){
                if( star == words.get(0).length()  && words.size() == 1 ) {
                    var ret = SearchBiWordSatr(words.get(0));
                    ans = ret;
                } else if ( star == 0 ) {
                    var ret = SearchBiWordEnd(words.get(0));
                    ans = ret;
                }else{
                    var ret1 = SearchBiWordSatr(words.get(0));
                    var ret2 = SearchBiWordEnd(words.get(1));

                    Set<Integer> uniqueElements = new LinkedHashSet<>();

                    for (int i = 0; i < ret1.size(); i++) {
                        for (int j = 0; j < ret2.size(); j++) {
                            if (ret1.get(i) == ret2.get(j)) {
                                uniqueElements.add(ret1.get(i));
                                break;
                            }
                        }
                    }

                    ans.addAll(uniqueElements);

                }
            }
            else if (AND == false && OR == false) {
                String BiWord = words.get(0) + " " + words.get(1) ;
                var ret = SearchBiWord(BiWord);
                ans = ret;
            } else if (AND == true) {

                String BiWord1 = words.get(0) + " " + words.get(1);
                String BiWord2 = words.get(2) + " " + words.get(3);
                // AND Operation ;
                var ret1 = SearchBiWord(BiWord1);
                var ret2 = SearchBiWord(BiWord2);


                for (int i = 0; i < ret1.size(); i++) {
                    for (int j = 0; j < ret2.size(); j++) {
                        if (ret1.get(i) == ret2.get(j)) {
                            ans.add(ret1.get(i));
                            break;
                        }
                    }
                }

            }else {
                // OR Operation ;
                String BiWord1 = words.get(0) + " " + words.get(1);
                String BiWord2 = words.get(2) + " " + words.get(3);

                var ret1 = SearchBiWord(BiWord1);
                var ret2 = SearchBiWord(BiWord2);

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
