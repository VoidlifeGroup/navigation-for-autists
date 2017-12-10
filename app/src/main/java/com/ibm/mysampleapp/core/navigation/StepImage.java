package com.ibm.mysampleapp.core.navigation;

/**
 * Created by mato on 6. 11. 2017.
 */

public class StepImage {

    private final String sceneImage;
    private final int distance;
    private final Arrow arrow;

    public StepImage(String sceneImage, int distance, Arrow arrow) {
        this.sceneImage = sceneImage;
        this.distance = distance;
        this.arrow = arrow;
    }

    public String getSceneImage() {
        return this.sceneImage;
    }

    public int getDistance() {
        return distance;
    }

    public Arrow getArrow(){
        return arrow;
    }
}
