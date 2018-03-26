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
    private String arrows;
    private String routeTitle;

    public Steps(String description, int step, int distance,
                 String arrows, String routeTitle, ImageView image) {
        this.description = description;
        this.step = step;
        this.image = image;
        this.distance = distance;
        this.arrows = arrows;
        this.routeTitle = routeTitle;
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

    public String getArrows() {
        return this.arrows;
    }

    public String  getRoute() {
        return this.routeTitle;
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

    public void setArrows(String arrows) {
        this.arrows = arrows;
    }

    public void setRoute(String routeTitle) {
        this.routeTitle = routeTitle;
    }


}

