package com.algorithms;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexesRepository {

    public static void writeInvertedIndexToFile(Map<String, List<Integer>> invertedIndex, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            for (Map.Entry<String, List<Integer>> entry : invertedIndex.entrySet()) {
                writer.print(entry.getKey() + ": ");
                List<Integer> documentIndices = entry.getValue();
                for (int i = 0; i < documentIndices.size(); i++) {
                    writer.print(documentIndices.get(i));
                    if (i < documentIndices.size() - 1) {
                        writer.print(", ");
                    }
                }
                writer.println();
            }
        }
    }

    public static Map<String, List<Integer>> readInvertedIndexFromFile(String filePath) throws IOException {
        Map<String, List<Integer>> invertedIndex = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                String term = parts[0];
                List<Integer> documentIndices = new ArrayList<>();
                if (parts.length > 1) {
                    String[] indices = parts[1].split(", ");
                    for (String index : indices) {
                        documentIndices.add(Integer.parseInt(index));
                    }
                }
                invertedIndex.put(term, documentIndices);
            }
        }
        return invertedIndex;
    }

    public static void writeIncidenceMatrixToFile(Map<String, List<Boolean>> invertedIndex, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            for (Map.Entry<String, List<Boolean>> entry : invertedIndex.entrySet()) {
                writer.print(entry.getKey() + ": ");
                List<Boolean> documentFlags = entry.getValue();
                for (int i = 0; i < documentFlags.size(); i++) {
                    writer.print(documentFlags.get(i));
                    if (i < documentFlags.size() - 1) {
                        writer.print(", ");
                    }
                }
                writer.println();
            }
        }
    }

    public static Map<String, List<Boolean>> readIncidenceMatrixFromFile(String filePath) throws IOException {
        Map<String, List<Boolean>> invertedIndex = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                String term = parts[0];
                List<Boolean> documentFlags = new ArrayList<>();
                if (parts.length > 1) {
                    String[] flags = parts[1].split(", ");
                    for (String flag : flags) {
                        documentFlags.add(Boolean.parseBoolean(flag));
                    }
                }
                invertedIndex.put(term, documentFlags);
            }
        }
        return invertedIndex;
    }
}
