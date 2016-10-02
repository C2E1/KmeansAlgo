package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class KmeansAlgo {
    /**
     * This class will actually Implement the K-means algorithm
     * @param num_of_cluster will store how many clusters
     * @param Extracluster_Variability will tell us variability between the clusters
     * @param Intercluster_Variability will be the sum of IV from all clusters
     * @param IV_EV_Ratio IV/EV. Tells us how good the model is
     * @param clusters will store out cluster in an array
     * @param converge will tell us if our K-means algorithm converged, will change if point changes cluster*
     * @param String that will give summary of current run of K-means Algorithm
     * */
    private int num_of_cluster;
    private double Extracluster_Variability;
    private double Intercluster_Variability;
    private double IV_EV_Ratio;
    private Cluster[] clusters;
    private ArrayList<Point> Data;
    private boolean converge;
    private int iterations;
    private String summary;



    /**
     * Constructs the Kmeans Algorithm
     * @param num_of_cluster will store how many clusters
     * @param data ArrayList that has all of our input data
     * */

    public KmeansAlgo(int num_of_cluster,ArrayList<Point> data) {
        summary = "";
        this.num_of_cluster = num_of_cluster;
        clusters = new Cluster[num_of_cluster];
        this.Data = data;
        iterations = 0;

        for(int clus_id = 1; clus_id <= num_of_cluster;clus_id++ )
            clusters[clus_id - 1] = new Cluster(clus_id);
    }

    /**
     * This function start off the K-means clustering.
     * It gives each point a random cluster, then proceeds to calculate centroids
     */
    public void start(){
        for(Point datum : Data){
            //for each datum assign a random cluster
            //int rand_cluster =(int)((Math.random() * (num_of_cluster)));
            Random rand = new Random();
            int rand_cluster = rand.nextInt(num_of_cluster) + 1;
            datum.setCluster(rand_cluster);
            clusters[rand_cluster-1].getPointSet().add(datum);
        }

        calculateCentroids();
        KmeansAlg();
        calculate_IV_EV_Ratio();
    }

    /**
     * Does the KmeansAlg until convergence
     */
    private void KmeansAlg(){
        converge = false;
        while(!converge){
            converge = true;
            iterations++;
             for(Point datum: Data){
                 Point Closest_centroid = closestCentroid(datum);
                 if(Closest_centroid.getCluster() != datum.getCluster()) {
                     converge = false;

                 }
                 getClusterobject(datum).removePoint(datum);
                 getClusterobject(Closest_centroid).addPoint(datum);
             }
            calculateCentroids();
            calculate_IV_EV_Ratio();
            //System.out.println(toString());
            addToSummary();
        }
        //System.out.println("");

           calculate_IV_EV_Ratio();

    }



    /**
     * Function calculates the Euclidean Distance between two Points
     * @param P1 our first point
     * @param P2 our second point
     * @return  the value of the distance
     */

    public double EuclideanDistance(Point P1, Point P2){
        return Math.sqrt(Math.pow(P1.getArea()-P2.getArea(),2) +
                Math.pow(P1.getAsymmetry_coefficient()-P2.getAsymmetry_coefficient(),2)+
                Math.pow(P1.getCompactness()-P2.getCompactness(),2)+
                Math.pow(P1.getLength_of_kernel()-P2.getLength_of_kernel(),2)+
                Math.pow(P1.getLength_of_kernel_groove()-P2.getLength_of_kernel_groove(),2) +
                Math.pow(P1.getPerimeter()-P2.getPerimeter(),2)+
                Math.pow(P1.getWidth_of_kernel()-P2.getWidth_of_kernel(),2));
    }

    /**
     *
     * @param datum Point of interest
     * @return the Centroid as a point object, Object clusterId will tell us what cluster it belongs to.
     */
    public Point closestCentroid(Point datum) {
        Point Closest = clusters[0].getCentroid();
        double min_distance = EuclideanDistance(datum, Closest);
        for (int cluster_idx = 1; cluster_idx < num_of_cluster; cluster_idx++) {
            double test_dist = EuclideanDistance(datum, clusters[cluster_idx].getCentroid());
            if (test_dist < min_distance) {
                min_distance = test_dist;
                Closest = clusters[cluster_idx].getCentroid();
            }

        }
        return Closest;
    }

    /**
     * Returns cluster object as opposed to cluster ID
     * @param datum Point of interest
     * @return Cluster that point belongs to
     */
    private Cluster getClusterobject(Point datum){
        return clusters[datum.getCluster() - 1];
    }


    /**
     * Initiates the Centroid calculation for each of the clusters
     */
    private void calculateCentroids(){
        for(int cluster_idx = 0; cluster_idx < num_of_cluster;cluster_idx++)
            clusters[cluster_idx].calculateCentroid();

    }

    /**
     * Adds up all Individual Cluster Variability
     */
    private void calculate_InterCluster_Variability(){
        Intercluster_Variability = 0;
        for(int cluster_idx = 0; cluster_idx < num_of_cluster;cluster_idx++)
            Intercluster_Variability += clusters[cluster_idx].getClusterIV();
    }

    /**
     * Calculates every clusters Intercluster Variability
     */
    private void calculate_Individual_IV(){
        for(int cluster_idx = 0; cluster_idx < num_of_cluster;cluster_idx++)
            clusters[cluster_idx].calculateClusterIV();
    }

    public double getIntercluster_Variability() {
        return Intercluster_Variability;
    }

    /**
     * Calculates Extracluster variability between all clusters
     * Uses the EV_between_Cluster function to calculate EV value
     */
    private void calculate_ExtraCluster_Variability(){
        Extracluster_Variability = 0;
        for(int cluster_idx = 0; cluster_idx < num_of_cluster - 1;cluster_idx++){
            for(int cluster_idx2 = cluster_idx + 1; cluster_idx2 < num_of_cluster;cluster_idx2++){
                Extracluster_Variability += EV_between_Cluster(clusters[cluster_idx],clusters[cluster_idx2]);
            }
        }

    }

    /**
     * Calculates the Extracluster variability between two clusters
     * @param Cluster1 First Cluster of interest
     * @param Cluster2 Second Cluster of interest
     * @return The calculated EV between the two clusters of interest
     */
    private double EV_between_Cluster(Cluster Cluster1, Cluster Cluster2){
        double EVbetween = 0;
        for(Point datum_C1 : Cluster1.getPointSet()){
            for(Point datum_C2 : Cluster2.getPointSet()){
                EVbetween += EuclideanDistance(datum_C1,datum_C2);
            }
        }
        return EVbetween;
    }

    public double getExtracluster_Variability() {
        return Extracluster_Variability;
    }

    /**
     * Calculates the IV/EV ratio for the given run of the K-means algorithm
     */
    private void calculate_IV_EV_Ratio(){
        calculate_Individual_IV();
        calculate_InterCluster_Variability();
        calculate_ExtraCluster_Variability();
        IV_EV_Ratio = Intercluster_Variability/Extracluster_Variability;
    }

    public double getIV_EV_Ratio() {
        return IV_EV_Ratio;
    }

    @Override
    public String toString() {
        return "KmeansAlgo{" +
                "num_of_cluster=" + num_of_cluster +
                ", Extracluster_Variability=" + Extracluster_Variability +
                ", Intercluster_Variability=" + Intercluster_Variability +
                ", IV_EV_Ratio=" + IV_EV_Ratio +
                '}';
    }

    private void addToSummary(){
        summary += ("Instance #:" + iterations + System.getProperty("line.separator"));
        summary += toString();
        summary += System.getProperty("line.separator");
    }

    public String getSummary() {
        summary =("Iteration took " + iterations + " instance(s) to converge" + System.getProperty("line.separator") + summary + System.getProperty("line.separator") );
        return summary;
    }
}
