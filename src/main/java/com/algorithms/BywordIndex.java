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

    public static List<Integer> search(String query,Map<String, Set<Integer>> biwordIndex) {
        String[] queryWords = query.split(" ");
        List<Integer> result = new LinkedList<>();

        for (int i = 0; i < queryWords.length - 1; i++) {
            String biword = queryWords[i] + " " + queryWords[i + 1];
            if (!biwordIndex.containsKey(biword)) {
                return Collections.emptyList();
            }
            if (result.isEmpty()) {
                result.addAll(biwordIndex.get(biword));
            } else {
                result.retainAll(biwordIndex.get(biword));
            }
        }

        return result;
    }

    public static List<Integer> searchStar(String query, Map<String, Set<Integer>> biwordIndex) {
        List<Integer> result = new LinkedList<>();

        for (String biword : biwordIndex.keySet()) {
            if (biword.startsWith(query)) {
                result.addAll(biwordIndex.get(biword));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] documents = {
                "the quick brown fox yousef here",
                "brown fox jumps over yousef here",
                "the lazy dog yousef here again"
        };
        IndexesFactory.setByWordIndex(documents);
        var x=IndexesFactory.getBywordIndex();
        System.out.println( x );

        System.out.println(search("yousef here",x));
    }
}