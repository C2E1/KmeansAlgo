package com.company;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
	// write your code here

        File file = new File(args[0]);
        Scanner fileIn = new Scanner(file);
        Kmeans KMS = new Kmeans(3,10);
        File file1 = new File("Summary.txt");
        //Data Input
        while(fileIn.hasNext()){
            Point datum = new Point(Double.parseDouble(fileIn.next()),Double.parseDouble(fileIn.next()),
                    Double.parseDouble(fileIn.next()),Double.parseDouble(fileIn.next()),
                    Double.parseDouble(fileIn.next()),Double.parseDouble(fileIn.next()),
                    Double.parseDouble(fileIn.next()));
            fileIn.next();

            KMS.add_Datum(datum);
        }
        KMS.kmeans_algo();
        FileWriter fileOut = new FileWriter(file1);
        fileOut.write(KMS.getSummary());
        fileOut.close();
    }
}
