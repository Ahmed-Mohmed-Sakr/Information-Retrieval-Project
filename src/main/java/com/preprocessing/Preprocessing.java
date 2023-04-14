package com.preprocessing;

/**
 * @author Wegdan
 */

import java.util.ArrayList;


public class Preprocessing {

    public static ArrayList<String> preprocess(String str,
            boolean stopWords, boolean normalization, boolean Stemming, boolean lemma) throws Exception {
        TextCleaning textCleaning = new TextCleaning();
        ArrayList<String> tokens = textCleaning.Tokenization(str);
        if(stopWords)
            tokens=textCleaning.stopWords(tokens);
        if (normalization) {
            tokens = textCleaning.normailzation(tokens);
        }
        if(Stemming){
            PorterStemmer stemmer = new PorterStemmer();
            ArrayList<String> stemOut=new ArrayList<>();
          for(String words  : tokens){
               stemmer.add(words.toCharArray(), words.length());
               stemmer.stem();
               String out = new String(stemmer.getResultBuffer(), 0, stemmer.getResultLength());
               stemOut.add(out);
          }
          tokens=stemOut;
        }
        if(lemma){
             tokens=StemLemmatize.lemmatizeTokens(tokens);
        }
        tokens.removeIf((String x)->x.equals("O"));
        return tokens;
    }

//    public static void main(String [] args) throws Exception {
//        String text = "How could you be seeing into my eyes like open doors? \n"+
//                "You led me down into my core where I've became so numb \n"+
//                "Without a soul my spirit's sleeping somewhere cold \n"+
//                "Until you find it there and led it back home \n"+
//                "You woke me up inside \n"+
//                "Called my name and saved me from the dark \n"+
//                "You have bidden my blood and it ran \n"+
//                "Before I would become undone \n"+
//                "You saved me from the nothing I've almost become \n"+
//                "You were bringing me to life \n"+
//                "Now that I knew what I'm without \n"+
//                "You can've just left me \n"+
//                "You breathed into me and made me real \n"+
//                "Frozen inside without your touch \n"+
//                "Without your love, darling \n"+
//                "Only you are the life among the dead \n"+
//                "I've been living a lie, there's nothing inside \n"+
//                "You were bringing me to life.";
//
//        var res=preprocess(text,true,true,true,true);
//
//        res.forEach(System.out::println);
//
//    }
    /*
     * Tokenization ✅
     * StopWords ✅
     * Normalization ✅
     * Stemming ✅
     * Lemma ✅
     * */
}

