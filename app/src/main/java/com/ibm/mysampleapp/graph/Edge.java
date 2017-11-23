package com.ibm.mysampleapp.graph;


public class Edge {

    private final String image;
    private final int fromIdVerticle;
    private final int toIdVerticle;
    private final int distance;

    public Edge(int fromIdVerticle, int toIdVerticle, int distance,  String image) {
        this.fromIdVerticle = fromIdVerticle;
        this.toIdVerticle = toIdVerticle;
        this.distance = distance;
        this.image = image;
    }

    public int getToIdVerticle() {
        return toIdVerticle;
    }

    public int getFromIdVerticle() {
        return fromIdVerticle;
    }

    public int getDistance() {
        return distance;
    }

    public String getImage() {
        return image;
    }

}
