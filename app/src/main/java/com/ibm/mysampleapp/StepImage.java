package com.ibm.mysampleapp;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by mato on 6. 11. 2017.
 */

public class StepImage {

    private Bitmap sceneImage;
    private boolean rightArrow;
    private boolean leftArrow;
    private boolean backArrow;
    private boolean forwardArrow;

    public StepImage(Bitmap sceneImage, boolean rightArrow, boolean leftArrow, boolean backArrow,
                     boolean forwardArrow){
        this.sceneImage = sceneImage;
        this.rightArrow = rightArrow;
        this.leftArrow = leftArrow;
        this.backArrow = backArrow;
        this.forwardArrow = forwardArrow;
    }

    public Bitmap getSceneImage(){
        return this.sceneImage;
    }


    public boolean getRight(){
        return this.rightArrow;
    }

    public boolean getLeft(){
        return this.leftArrow;
    }

    public boolean getBack(){
        return this.backArrow;
    }

    public boolean getForward(){
        return this.forwardArrow;
    }

}
