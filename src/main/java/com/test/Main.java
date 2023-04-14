package com.test;

import com.DataSet.ReadData;
import com.algorithms.IncidenceMatrix;
import com.preprocessing.Preprocessing;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String []args) throws Exception {
        String [] data= ReadData.readDocuments("D:\\AFinalYear\\IR\\Information-Retrieval-Project\\archive\\CISI.QRY");

        ArrayList<List<String>> cleanedData=new ArrayList<>();
        int xx=10;
        for(var d:data){
            xx--;
            if(xx==0){
                break;
            }
            cleanedData.add(Preprocessing.preprocess(d,true,true,true,true));
        }

        var matrix= IncidenceMatrix.createMatrix(cleanedData);
        matrix.forEach((x,list)->{
            System.out.print(x+"  ");
            list.forEach(z-> System.out.print(z+" "));
            System.out.println();
        });
    }
}
