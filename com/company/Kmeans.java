package com.company;
import java.util.ArrayList;

/**
 * Created by Christian on 9/30/2016.
 */
public class Kmeans {
    /**
     * @param num_of_clusters number of clusters chosen
     * @param current_best stores the best IV/EV ratio aming all K-Means runs
     * @param Kmean_holder Stores all K-Mean runs in a choosen array size
     * @param Data Stores seven Dimensional data into an array list
     */
    private int num_of_clusters;
    private double current_best;
    private KmeansAlgo[] Kmean_holder;
    private ArrayList<Point> Data;
    private String summary;

    /**
     *
     * @param num_of_clusters number of cluster chosen for K-means algorithm
     * @param iterations Number if K-Means run
     */
    public Kmeans(int num_of_clusters,int iterations) {
        this.num_of_clusters = num_of_clusters;

        this.Kmean_holder = new KmeansAlgo[iterations];
        Data = new ArrayList<Point>();
    }

    /**
     *
     * @param datum Seven Dimensional point to add data Array List
     */
    public void add_Datum(Point datum){
        Data.add(datum);
    }


    /**
     * This function calls the K-Means algorithm
     * It will call the K-means algorithm for a set number of iterations
     * Will store each run of the K-means algorithm in the K-mean holder array
     */
    public void kmeans_algo(){
        summary = ("There were " + Kmean_holder.length + " iterations of K-means Algorithm" + System.getProperty("line.separator"));
        for(int iter = 0; iter < Kmean_holder.length;iter++) {
            KmeansAlgo KMS = new KmeansAlgo(num_of_clusters, Data);
            KMS.start();
            Kmean_holder[iter] = KMS;
            summary += KMS.getSummary();
        }

    }

    public String getSummary(){
        return summary;
    }

}
