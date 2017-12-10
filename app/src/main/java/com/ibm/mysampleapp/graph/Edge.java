package com.ibm.mysampleapp.graph;

/**
 * Trieda Edge slúži na ukladanie dát o hrane. Sú tu uložené názvy obrázkov hrán, id vrcholov, ktoré
 * tvoria hranu, dĺžku hrany a id vrcholov nasledujúcich hrán pre určenie smeru otočenia.
 * @author Martin Bystriansky
 * @author Radoslav Soják
 * */
public class Edge {

    private final String image;
    private final String image2;
    private final int fromIdVerticle;
    private final int toIdVerticle;
    private final int distance;
    private final Integer leftArrow;
    private final Integer rightArrow;

    public Edge(int fromIdVerticle, int toIdVerticle, int distance, String image, String image2,
                Integer leftArrow, Integer rightArrow) {
        this.fromIdVerticle = fromIdVerticle;
        this.toIdVerticle = toIdVerticle;
        this.distance = distance;
        this.image = image;
        this.image2 = image2;
        this.leftArrow = leftArrow;
        this.rightArrow = rightArrow;
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

    public String getImage2() {
        return image2;
    }

    public Integer getRightArrow() {
        return rightArrow;
    }

    public Integer getLeftArrow() {
        return leftArrow;
    }
}
