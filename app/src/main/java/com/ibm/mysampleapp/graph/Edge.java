package com.ibm.mysampleapp.graph;


public class Edge {

    private final String image;
    private final int fromIdVerticle;
    private final int toIdVerticle;
    private final int distance;
    private final Integer leftArrow;
    private final Integer rightArrow;
    private final Integer forwardArrow;

    public Edge(int fromIdVerticle, int toIdVerticle, int distance, String image,
                Integer leftArrow, Integer rightArrow, Integer forwardArrow) {
        this.fromIdVerticle = fromIdVerticle;
        this.toIdVerticle = toIdVerticle;
        this.distance = distance;
        this.image = image;
        this.leftArrow = leftArrow;
        this.rightArrow = rightArrow;
        this.forwardArrow = forwardArrow;
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

    public Integer getRightArrow() {
        return rightArrow;
    }

    public Integer getForwardArrow() {
        return forwardArrow;
    }

    public Integer getLeftArrow() {
        return leftArrow;
    }
}
