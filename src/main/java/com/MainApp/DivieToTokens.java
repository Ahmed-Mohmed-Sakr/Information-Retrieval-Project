package com.MainApp;

import java.util.ArrayList;
import java.util.List;

public class DivieToTokens {
    public static List<String> Tokens( String SearchText ) {

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
}
