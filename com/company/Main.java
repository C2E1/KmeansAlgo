package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Driver class for whole program
 * Takes in text data through command line arg
 */

public class Main {

    public static void main(String[] args) throws Exception{

        File file = new File(args[0]);
        Scanner fileIn = new Scanner(file);
        int clusters = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter number of clusters"));
        int iterations = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter number of iterations"));
        Kmeans KMS = new Kmeans(clusters,iterations);
        File file1 = new File("Summary.txt");
        double holder;
        //Data Input
        while(fileIn.hasNext()){
            Point datum = new Point(holder = Double.parseDouble(fileIn.next()),Double.parseDouble(fileIn.next()),
                    Double.parseDouble(fileIn.next()),Double.parseDouble(fileIn.next()),
                    Double.parseDouble(fileIn.next()),Double.parseDouble(fileIn.next()),
                    Double.parseDouble(fileIn.next()));
                    Integer.parseInt(fileIn.next());
                    if(holder < 13.5 )
                        datum.setCluster(1);
                    else if(holder > 16.5)
                        datum.setCluster(3);
                    else
                        datum.setCluster(2);


            KMS.add_Datum(datum);
        }
        KMS.kmeans_algo();
        //writes Summary to a text file
        FileWriter fileOut = new FileWriter(file1);
        fileOut.write(KMS.getSummary());
        fileOut.close();
        JOptionPane.showMessageDialog(null,"Done. Check Summary.txt for results.");
    }
}
