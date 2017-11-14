package com.ibm.mysampleapp.graph;

import java.util.ArrayList;
/**
 * Vrchol obsahuje id a typ
 */

public class Verticle {

    private final int idVerticle;
    private ArrayList<Edge> edges = new ArrayList<Edge>();

    public Verticle(int idVerticle, ArrayList edges){
        this.idVerticle = idVerticle;
        this.edges = edges;
    }

    public int getId() {
        return idVerticle;
    }

    // Neviem ci to bude mat nejake vyuzitie, zatial som to pridal pre testovacie ucely
    public ArrayList<Edge> getEdges(){
        return edges;
    }

}
