package com.ibm.mysampleapp.route;

import android.media.Image;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by mato on 10. 3. 2018.
 */

public class Steps {

    private String description;
    private int step;
    private ImageView image;
    private int distance;
    private ArrayList<String> arrows = new ArrayList<String>();
    private Route route;

    public Steps(String description, int step, ImageView image, int distance,
                 ArrayList<String> arrows, Route route) {
        this.description = description;
        this.step = step;
        this.image = image;
        this.distance = distance;
        this.arrows = arrows;
        this.route = route;
    }

    //gettre

    public String getDescription() {
        return this.description;
    }

    public int getStep() {
        return this.step;
    }

    public ImageView getImage() {
        return this.image;
    }

    public int getDistance() {
        return this.distance;
    }

    public ArrayList<String> getArrows() {
        return this.arrows;
    }

    public Route getRoute() {
        return this.route;
    }

    //settre

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setArrows(ArrayList<String> arrows) {
        this.arrows = arrows;
    }

    public void setRoute(Route route) {
        this.route = route;
    }


}

