package com.algorithms;

import com.DataSet.ReadData;
import com.preprocessing.Preprocessing;
import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

public class positionalIndex {
    public static Map<String, Map<Integer, List<Integer>>> positionalIndexAlgorithm(List<List<String>> documents) {
        Map<String, Map<Integer, List<Integer>>> index = new TreeMap<>();
        Map<String, Integer> docFreq = new TreeMap<>();
        int docId = 0;
            for (var doc : documents) {
                int pos = 0;
                for (var term : doc) {
                    if (!index.containsKey(term)) {
                        index.put(term, new TreeMap<>());
                    }
                    Map<Integer, List<Integer>> postings = index.get(term);
                    if (!postings.containsKey(docId)) {
                        postings.put(docId, new ArrayList<>());
                        docFreq.put(term, docFreq.getOrDefault(term, 0) + 1);
                    }
                    List<Integer> positions = postings.get(docId);
                    positions.add(pos);
                    pos++;
                }
                docId++;
            }
            return index;
    }
}