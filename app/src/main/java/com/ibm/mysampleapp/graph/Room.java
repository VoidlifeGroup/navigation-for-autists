package com.ibm.mysampleapp.graph;

import java.util.ArrayList;

/**
 * Trieda Room rozširuje triedu Verticle pre potreby zobrazenia názvu miestností tu sú uložené ich
 * názvy.
 * */
public class Room extends Verticle {

    private final String name;

    public Room(int idVerticle, ArrayList edges, String name) {
        super(idVerticle, edges);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
