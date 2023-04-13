package preprocessing;

import opennlp.tools.tokenize.SimpleTokenizer;

import java.io.*;
import java.util.*;

/**
 *
 * @author Wegdan
 */
public class Preprocessing {

    public static void main(String[] args) throws Exception {


        // TextCleaning c = new TextCleaning();

        // BufferedReader br = new BufferedReader(new FileReader("C://Users//Wegdan//Documents//NetBeansProjects//Preprocessing//dataDir//textfile.txt"));
        // String st;
        // // c.stopWords("and or or/and m:lk");
        // ArrayList<String> s1 = new ArrayList<>();
        // while ((st = br.readLine()) != null) {

        //     c.Tokenization(st);

        //     s1.addAll(c.stopWords(st));
        // }
        // ArrayList<String> stre = c.normailzation(s1);

        // PorterStemmer stemmer = new PorterStemmer();
        // for(String words  : stre){
        //     stemmer.add(words.toCharArray(), words.length());
        //     stemmer.stem();
        //     String out = new String(stemmer.getResultBuffer(), 0, stemmer.getResultLength());
        //     System.out.println("Before "+words+" Stemmed word: " + out);
        // }
        System.out.println("apache open nlp lemmatizer");
        String text = "How could you be seeing into my eyes like open doors? \n"+
                "You led me down into my core where I've became so numb \n"+
                "Without a soul my spirit's sleeping somewhere cold \n"+
                "Until you find it there and led it back home \n"+
                "You woke me up inside \n"+
                "Called my name and saved me from the dark \n"+
                "You have bidden my blood and it ran \n"+
                "Before I would become undone \n"+
                "You saved me from the nothing I've almost become \n"+
                "You were bringing me to life \n"+
                "Now that I knew what I'm without \n"+
                "You can've just left me \n"+
                "You breathed into me and made me real \n"+
                "Frozen inside without your touch \n"+
                "Without your love, darling \n"+
                "Only you are the life among the dead \n"+
                "I've been living a lie, there's nothing inside \n"+
                "You were bringing me to life.";
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String[] tokens = tokenizer.tokenize(text);
        String[] res=StemLemmatize.lemmatizeTokens(tokens);
        for(var x:res){
            System.out.println(x);
        }
    }
    /*
    * Tokenization ✅
    * StopWords ✅
    * Normalization ✅
    * Stemming ✅
    * Lemma
    * */
}
