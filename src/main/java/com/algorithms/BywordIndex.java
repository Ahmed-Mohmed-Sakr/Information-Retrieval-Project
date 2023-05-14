package com.algorithms;

import java.util.*;

public class BywordIndex {




    protected static Map<String, Set<Integer>> buildIndex(String[] documents) {
        Map<String, Set<Integer>> biwordIndex =new HashMap<>();
        for (int i = 0; i < documents.length; i++) {
            String[] words = documents[i].split(" ");
            for (int j = 0; j < words.length - 1; j++) {
                String biword = words[j] + " " + words[j + 1];
                Set<Integer> postings = biwordIndex.getOrDefault(biword, new HashSet<>());
                postings.add(i);
                biwordIndex.put(biword, postings);
            }
        }
        return biwordIndex;
    }

    protected static Set<Integer> search(String query,Map<String, Set<Integer>> biwordIndex) {
        String[] queryWords = query.split(" ");
        Set<Integer> result = new HashSet<>();

        for (int i = 0; i < queryWords.length - 1; i++) {
            String biword = queryWords[i] + " " + queryWords[i + 1];
            if (!biwordIndex.containsKey(biword)) {
                return Collections.emptySet();
            }
            if (result.isEmpty()) {
                result.addAll(biwordIndex.get(biword));
            } else {
                result.retainAll(biwordIndex.get(biword));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] documents = {
                "the quick brown fox",
                "brown fox jumps over",
                "the lazy dog"
        };
        IndexesFactory.setByWordIndex(documents);
        var x=IndexesFactory.getBywordIndex();
        System.out.println(search("brown fox",x));
    }
}