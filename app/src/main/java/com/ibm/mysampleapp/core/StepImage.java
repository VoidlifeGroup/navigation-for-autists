package com.ibm.mysampleapp.core;


/**
 * Created by mato on 6. 11. 2017.
 */

public class StepImage {

    private final String sceneImage;
    private final int distance;

    public StepImage(String sceneImage, int distance) {
        this.sceneImage = sceneImage;
        this.distance = distance;
    }

    public String getSceneImage() {
        return this.sceneImage;
    }

    public int getDistance() {
        return distance;
    }
}
