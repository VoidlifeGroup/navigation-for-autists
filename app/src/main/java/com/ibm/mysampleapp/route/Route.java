package com.ibm.mysampleapp.route;

/**
 * Created by mato on 10. 3. 2018.
 */

public class Route {

    private String title;
    private String id;

    public Route(String title, String id) {
        this.title = title;
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
