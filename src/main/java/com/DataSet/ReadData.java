/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DataSet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author yousef
 */
public class ReadData {


    public static void main(String[] args) throws FileNotFoundException, IOException {
        String[] res = readDocuments( "/home/yousef/level 4/term2/information retrieval/project/Information-Retrieval-Project/archive/CISI.QRY" );
        System.out.println(res[1]);
    }

    public static String[] readDocuments(String filename) throws IOException {
        ArrayList<String> documents = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        StringBuilder documentBuilder = new StringBuilder();
        while (line != null) {
            if (line.startsWith(".I")) {
                // Start of a new document, add the previous one to the list
                if (documentBuilder.length() > 0) {
                    documents.add(documentBuilder.toString().trim());
                    documentBuilder.setLength(0);
                }
            } else if (line.startsWith(".W")) {
                // Start of the document content
                documentBuilder.append(line.substring(2)); // Add the content without the ".W"
                documentBuilder.append("\n"); // Add a newline
            } else {
                // Add a regular line to the document content
                documentBuilder.append(line);
                documentBuilder.append("\n"); // Add a newline
            }
            line = reader.readLine();
        }
        // Add the last document to the list
        if (documentBuilder.length() > 0) {
            documents.add(documentBuilder.toString().trim());
        }
        reader.close();
        return documents.toArray(new String[documents.size()]);
    }

    public static String[] readTokensFromFile(String filename) throws FileNotFoundException {
        // Open the file for reading
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        // Create an array to hold the tokens
        List<String> tokens = new ArrayList<String>();

        // Loop through each line of the file
        while (scanner.hasNextLine()) {
            // Read the line and split it into tokens
            String line = scanner.nextLine();
            String[] lineTokens = line.split("\\s+");

            // Add each token to the array
            for (String token : lineTokens) {
                tokens.add(token);
            }
        }

        // Close the scanner and return the array of tokens
        scanner.close();
        return tokens.toArray(new String[tokens.size()]);
    }

    public static String[][] readCisiRelFile(String fileName) {
        try {
            // Open the file for reading
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            // Read the contents of the file into a string
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            reader.close();
            String contents = sb.toString();

            // Split the contents into lines and then into words
            String[] lines = contents.split("\n");
            String[][] documents = new String[lines.length][];
            for (int i = 0; i < lines.length; i++) {
                String[] words = lines[i].split(" ");
                documents[i] = new String[words.length];
                for (int j = 0; j < words.length; j++) {
                    documents[i][j] = words[j];
                }
            }

            return documents;
        } catch (IOException e) {
            // Handle any errors that might occur
            e.printStackTrace();
            return null;
        }
    }


}