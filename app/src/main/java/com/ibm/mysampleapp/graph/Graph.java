package com.ibm.mysampleapp.graph;


import com.ibm.mysampleapp.core.BuildingList;
import com.ibm.mysampleapp.parser.XmlPullParserNFA;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class Graph implements BuildingList{

    private ArrayList<Edge> edges = new ArrayList<Edge>();
    private ArrayList<Verticle> verticles = new ArrayList<Verticle>();
    private final int numberOfVerticles;
    private final static int matrixConst = 9999;

    public Graph(String building, InputStream s){

        XmlPullParserNFA p = new XmlPullParserNFA();
        try {
            verticles = p.parseVerticles(s);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        numberOfVerticles = verticles.size();
        loadEdges();
    }

    public int[][] matrix(){
        int[][] matrix = new int[numberOfVerticles][numberOfVerticles];

        for(int i=0; i<numberOfVerticles; i++){
            for(int j=0; j<numberOfVerticles; j++){
                matrix[i][j] = matrixConst;
            }
        }

        for (Edge edge : edges){
            matrix[edge.getFromIdVerticle()][edge.getToIdVerticle()] = edge.getDistance();
        }
        return matrix;
    }

    private void loadEdges(){
        ArrayList<Edge> edges_temp = new ArrayList<Edge>();
        for (Verticle verticle : verticles){
            ArrayList<Edge> temp;
            temp = verticle.getEdges();
            for (Edge edge : temp){
                if(!edges_temp.contains(edge)){
                    edges_temp.add(edge);
                }
            }
        }
        this.edges = edges_temp;
    }

    public ArrayList<Room> rooms(){
        ArrayList<Room> rooms = new ArrayList<>();

        for (Verticle verticle : verticles){
            if (verticle instanceof Room){
                rooms.add((Room) verticle);
            }
        }
        return rooms;
    }

    public int getNumberOfVerticles(){
        return numberOfVerticles;
    }
}
