package com.company;

import java.util.HashSet;

/**
 * Created by Christian on 9/30/2016.
 */
public class Cluster {
        private double ClusterIV;
        private Point centroid;
        private HashSet<Point> pointSet;
        private int clusterID;

    public Cluster(int clusterID) {
        this.clusterID = clusterID;
        pointSet = new HashSet<Point>();
    }

    public HashSet<Point> getPointSet() {
        return pointSet;
    }

    public void calculateCentroid(){
        Point Center;
        double avgs[] = new double[7];
        for(int i = 0; i < avgs.length;i++)
            avgs[i] = 0;
        for(Point p : pointSet){
            avgs[0] += p.getArea();
            avgs[1] += p.getPerimeter();
            avgs[2] += p.getCompactness();
            avgs[3] += p.getLength_of_kernel();
            avgs[4] += p.getWidth_of_kernel();
            avgs[5] += p.getAsymmetry_coefficient();
            avgs[6] += p.getLength_of_kernel_groove();
        }
        centroid = new Point(avgs[0]/pointSet.size(),avgs[1]/pointSet.size(),
                avgs[2]/pointSet.size(),avgs[3]/pointSet.size(),
                avgs[4]/pointSet.size(),avgs[5]/pointSet.size(),
                avgs[6]/pointSet.size());
        centroid.setCluster(clusterID);

    }

    public Point getCentroid() {
        return centroid;
    }

    public void addPoint(Point datum){
        datum.setCluster(clusterID);
        pointSet.add(datum);

    }

    public void removePoint(Point datum){
        pointSet.remove(datum);
    }

    public void calculateClusterIV(){
            ClusterIV = 0;
            for(Point datum : pointSet){
                ClusterIV += EuclideanDistance(datum,centroid);
            }
    }

    public double getClusterIV(){
        return ClusterIV;
    }


    public double EuclideanDistance(Point P1, Point P2){
        return Math.sqrt(Math.pow(P1.getArea()-P2.getArea(),2) +
                Math.pow(P1.getAsymmetry_coefficient()-P2.getAsymmetry_coefficient(),2)+
                Math.pow(P1.getCompactness()-P2.getCompactness(),2)+
                Math.pow(P1.getLength_of_kernel()-P2.getLength_of_kernel(),2)+
                Math.pow(P1.getLength_of_kernel_groove()-P2.getLength_of_kernel_groove(),2) +
                Math.pow(P1.getPerimeter()-P2.getPerimeter(),2)+
                Math.pow(P1.getWidth_of_kernel()-P2.getWidth_of_kernel(),2));
    }

}
