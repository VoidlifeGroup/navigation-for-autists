package com.ibm.mysampleapp.core;


/**
 * Created by mato on 6. 11. 2017.
 */

public class StepImage {

    private String sceneImage;
    private boolean rightArrow;
    private boolean leftArrow;
    private boolean forwardArrow;

    public StepImage(String sceneImage, boolean rightArrow, boolean leftArrow, boolean forwardArrow){
        this.sceneImage = sceneImage;
        this.rightArrow = rightArrow;
        this.leftArrow = leftArrow;
        this.forwardArrow = forwardArrow;
    }

    public String getSceneImage(){
        return this.sceneImage;
    }

    public boolean getRight(){
        return this.rightArrow;
    }

    public boolean getLeft(){
        return this.leftArrow;
    }

    public boolean getForward(){
        return this.forwardArrow;
    }

}
