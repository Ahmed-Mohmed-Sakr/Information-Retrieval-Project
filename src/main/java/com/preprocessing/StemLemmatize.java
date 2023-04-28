/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preprocessing;




import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class StemLemmatize {

    public static ArrayList<String> lemmatizeTokens(ArrayList<String> inputTokens) throws Exception {

        String[] tokens=new String[inputTokens.size()];
        int count=0;
        for (String inputToken : inputTokens) {
            tokens[count++]=inputToken;
        }

        InputStream inputStreamPOSTagger = new FileInputStream("D:\\fourth-year\\Second Semster\\IR\\Project\\Information-Retrieval-Project\\archive\\en-pos-maxent.bin");
        POSModel posModel = new POSModel(inputStreamPOSTagger);
        POSTaggerME posTagger = new POSTaggerME(posModel);
        String tags[] = posTagger.tag(tokens);
        InputStream dictLemmatizer = new FileInputStream("D:\\fourth-year\\Second Semster\\IR\\Project\\Information-Retrieval-Project\\archive\\en-lemmatizer.dict");
        DictionaryLemmatizer lemmatizer = new DictionaryLemmatizer(
                dictLemmatizer);
        String[] lemmas = lemmatizer.lemmatize(tokens, tags);

        return new ArrayList<>(Arrays.asList(lemmas));
    }

}
