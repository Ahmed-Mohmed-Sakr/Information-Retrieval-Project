package com.algorithms;

import java.util.List;
import java.util.Map;

public class IndexesFactory {
    private static Map<String, List<Boolean>> incidenceMatrix;

    private static Map<String, List<Integer>> invertedIndex;

    private static Map<String, Map<Integer, List<Integer>>> positionalIndex;
    public static Map<String, List<Boolean>> getIncidenceMatrix() {
        if(incidenceMatrix !=null)
            return incidenceMatrix;
        try {
            incidenceMatrix = IndexesRepository.readIncidenceMatrixFromFile("D:\\fourth-year\\Second Semster\\IR\\Project\\Information-Retrieval-Project\\archive\\IncidenceMatrix.txt");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return incidenceMatrix;
    }


    public static Map<String ,List<Boolean>> setIncidenceMatrix(List<List<String>>documents){
        incidenceMatrix=IncidenceMatrix.createMatrix(documents);
        try {
            IndexesRepository.writeIncidenceMatrixToFile(incidenceMatrix, "D:\\fourth-year\\Second Semster\\IR\\Project\\Information-Retrieval-Project\\archive\\IncidenceMatrix.txt");
        }catch (Exception ex){
            System.out.println(ex);
        }
        return incidenceMatrix;
    }
    public static Map<String, List<Integer>> getInvertedIndex() {
        if(invertedIndex !=null)
            return invertedIndex;
        try {
            invertedIndex = IndexesRepository.readInvertedIndexFromFile("D:\\fourth-year\\Second Semster\\IR\\Project\\Information-Retrieval-Project\\archive\\InvertedIndex.txt");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return invertedIndex;
    }

    public static Map<String, List<Integer>> setInvertedIndex(List<List<String>>documents){
        invertedIndex=InvertedIndex.createInvertedIndex(documents);
        try{
            IndexesRepository.writeInvertedIndexToFile(invertedIndex,"D:\\fourth-year\\Second Semster\\IR\\Project\\Information-Retrieval-Project\\archive\\InvertedIndex.txt");
        }catch (Exception ex){
            System.out.println(ex);
        }
        return invertedIndex;
    }

    public static Map<String, Map<Integer, List<Integer>>> getPositionalIndex() {
        if(positionalIndex !=null)
            return positionalIndex;
        try {
            positionalIndex = IndexesRepository.readIndexFromFile("D:\\fourth-year\\Second Semster\\IR\\Project\\Information-Retrieval-Project\\archive\\positionalIndex.txt");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return positionalIndex;
    }

    public static Map<String, Map<Integer, List<Integer>>> setPositionalIndex(List<List<String>>documents){
        positionalIndex = com.algorithms.positionalIndex.positionalIndexAlgorithm(documents);
        try{
            IndexesRepository.writepositionalIndexFromFile(positionalIndex,"D:\\fourth-year\\Second Semster\\IR\\Project\\Information-Retrieval-Project\\archive\\positionalIndex.txt");
        }catch (Exception ex){
            System.out.println(ex);
        }
        return positionalIndex;
    }
}
