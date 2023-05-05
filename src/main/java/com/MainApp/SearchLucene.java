package com.MainApp;

import com.algorithms.Lucene_Searcher;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchLucene {
    public static ArrayList<Integer> SearchLucene(String Token) throws IOException, ParseException {
        ArrayList<Integer> ret = Lucene_Searcher.searcher(Token);
        return ret;
    }

    public static ArrayList<Integer> Search( String SearchText ){

        ArrayList<Integer> ans = new ArrayList<>();

        try {
            boolean AND = false, OR = false;
            List<String> needs = new ArrayList<>();

            for (int i = 0; i < SearchText.length(); i++) {
                if (SearchText.charAt(i) == '&')
                    AND = true;

                if (SearchText.charAt(i) == '|')
                    OR = true;
            }

            var words = DivieToTokens.Tokens(SearchText);
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
            } else {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return ans;
    }
}
