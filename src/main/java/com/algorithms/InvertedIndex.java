package com.algorithms;

import java.io.*;
import java.util.*;

public class InvertedIndex {


    protected static Map<String, List<Integer>> createInvertedIndex(List<List<String>> documents){
        Map<String,List<Integer>> invertedIndex=new TreeMap<>();
        int i=0;
        for(var document:documents){
            for(var token:document){
                invertedIndex.computeIfAbsent(token, k -> new ArrayList<>());
                if(!invertedIndex.get(token).contains(i))
                     invertedIndex.get(token).add(i);
            }
            i++;
        }
        return invertedIndex;
    }
}


