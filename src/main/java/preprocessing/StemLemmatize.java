/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package preprocessing;




import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StemLemmatize {

    public static String[] lemmatizeTokens(String[] tokens) throws Exception {


        InputStream inputStreamPOSTagger = new FileInputStream("D:\\AFinalYear\\IR\\Information-Retrieval-Project\\archive\\en-pos-maxent.bin");
        POSModel posModel = new POSModel(inputStreamPOSTagger);
        POSTaggerME posTagger = new POSTaggerME(posModel);
        String tags[] = posTagger.tag(tokens);
        InputStream dictLemmatizer = new FileInputStream("D:\\AFinalYear\\IR\\Information-Retrieval-Project\\archive\\en-lemmatizer.dict");
        DictionaryLemmatizer lemmatizer = new DictionaryLemmatizer(
                dictLemmatizer);
        String[] lemmas = lemmatizer.lemmatize(tokens, tags);

        return lemmas;
    }

}
