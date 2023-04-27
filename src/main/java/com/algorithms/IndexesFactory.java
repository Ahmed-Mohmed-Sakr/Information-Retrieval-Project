package com.algorithms;

import java.util.List;
import java.util.Map;

public class IndexesFactory {
    private static Map<String, List<Boolean>> incidenceMatrix;

    private static Map<String, List<Integer>> invertedIndex;

    public static Map<String, List<Boolean>> getIncidenceMatrix() {
        if(incidenceMatrix !=null)
            return incidenceMatrix;
        try {
            incidenceMatrix = IndexesRepository.readIncidenceMatrixFromFile("/home/yousef/level 4/term2/information retrieval/finla project/Information-Retrieval-Project/archive/IncidenceMatrix.txt");
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

}
