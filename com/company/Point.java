package com.company;

import java.text.DecimalFormat;


public class Point {

    private double Area;
        private double Perimeter;
        private double compactness;
        private double length_of_kernel;
        private double width_of_kernel;
        private double asymmetry_coefficient;
        private double length_of_kernel_groove;
        private int Cluster;

    public Point(double area, double perimeter, double compactness, double length_of_kernel, double width_of_kernel, double asymmetry_coefficient, double length_of_kernel_groove) {
        Area = area;
        Perimeter = perimeter;
        this.compactness = compactness;
        this.length_of_kernel = length_of_kernel;
        this.width_of_kernel = width_of_kernel;
        this.asymmetry_coefficient = asymmetry_coefficient;
        this.length_of_kernel_groove = length_of_kernel_groove;
    }

    public double getArea() {
        return Area;
    }

    public void setArea(double area) {
        Area = area;
    }

    public double getPerimeter() {
        return Perimeter;
    }

    public void setPerimeter(double perimeter) {
        Perimeter = perimeter;
    }

    public double getCompactness() {
        return compactness;
    }

    public void setCompactness(double compactness) {
        this.compactness = compactness;
    }

    public double getLength_of_kernel() {
        return length_of_kernel;
    }

    public void setLength_of_kernel(double length_of_kernel) {
        this.length_of_kernel = length_of_kernel;
    }

    public double getWidth_of_kernel() {
        return width_of_kernel;
    }

    public void setWidth_of_kernel(double width_of_kernel) {
        this.width_of_kernel = width_of_kernel;
    }

    public double getAsymmetry_coefficient() {
        return asymmetry_coefficient;
    }

    public void setAsymmetry_coefficient(double asymmetry_coefficient) {
        this.asymmetry_coefficient = asymmetry_coefficient;
    }

    public double getLength_of_kernel_groove() {
        return length_of_kernel_groove;
    }

    public void setLength_of_kernel_groove(double length_of_kernel_groove) {
        this.length_of_kernel_groove = length_of_kernel_groove;
    }

    public int getCluster() {
        return Cluster;
    }

    public void setCluster(int cluster) {
        Cluster = cluster;
    }

    /**
     * prints seven dimensional points up to four decimal places for each component
     * @return
     */
    @Override
    public String toString() {
//        return "Point{" +
//                "Area=" + Area +
//                ", Perimeter=" + Perimeter +
//                ", compactness=" + compactness +
//                ", length_of_kernel=" + length_of_kernel +
//                ", width_of_kernel=" + width_of_kernel +
//                ", asymmetry_coefficient=" + asymmetry_coefficient +
//                ", length_of_kernel_groove=" + length_of_kernel_groove +
//                ", Cluster=" + Cluster +
//                '}';
        DecimalFormat df = new DecimalFormat("0.####");

        return "<" +
                 df.format(Area) +
                "," + df.format(Perimeter) +
                "," + df.format(compactness) +
                "," + df.format(length_of_kernel) +
                "," + df.format(width_of_kernel) +
                "," + df.format(asymmetry_coefficient) +
                "," + df.format(length_of_kernel_groove) +
                ">";
    }
}
