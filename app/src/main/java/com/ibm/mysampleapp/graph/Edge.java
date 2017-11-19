package com.ibm.mysampleapp.graph;


public class Edge {

    private final String image;
    private final int fromIdVerticle;
    private final int toIdVerticle;
    private final int distance;
    private final Integer leftArrowIdVerticle;
    private final Integer rightArrowIdVerticle;
    private final Integer forwardArrowIdVerticle;

    public Edge(int fromIdVerticle, int toIdVerticle, int distance, Integer leftArrowIdVerticle,
                Integer rightArrowIdVerticle, Integer forwardArrowIdVerticle, String image) {
        this.fromIdVerticle = fromIdVerticle;
        this.toIdVerticle = toIdVerticle;
        this.distance = distance;
        this.leftArrowIdVerticle = leftArrowIdVerticle;
        this.rightArrowIdVerticle = rightArrowIdVerticle;
        this.forwardArrowIdVerticle = forwardArrowIdVerticle;
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

    public Integer getLeftArrowIdVerticle() {
        return leftArrowIdVerticle;
    }

    public Integer getRigthArrowIdVerticle() {
        return rightArrowIdVerticle;
    }

    public Integer getUpArrowIdVerticle() {
        return forwardArrowIdVerticle;
    }
}
