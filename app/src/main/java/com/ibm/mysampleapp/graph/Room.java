package com.ibm.mysampleapp.graph;

import java.util.ArrayList;


public class Room extends Verticle {

    private final String name;

    public Room(int idVerticle, ArrayList edges, String name) {
        super(idVerticle, edges);
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
