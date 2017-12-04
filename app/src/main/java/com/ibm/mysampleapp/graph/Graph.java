package com.ibm.mysampleapp.graph;

import com.ibm.mysampleapp.core.Arrow;
import com.ibm.mysampleapp.core.RoomList;
import com.ibm.mysampleapp.core.StepImage;
import com.ibm.mysampleapp.core.TraceList;
import com.ibm.mysampleapp.graph.algorithms.Dijkstra;
import com.ibm.mysampleapp.parser.XmlPullParserNFA;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class Graph implements RoomList, TraceList {

    private final static int matrixConst = 9999;
    private final int numberOfVerticles;
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    private ArrayList<Verticle> verticles = new ArrayList<Verticle>();
    private Dijkstra dijkstra = new Dijkstra();

    public Graph(String building, InputStream s) {

        XmlPullParserNFA p = new XmlPullParserNFA();
        try {
            verticles = p.parseVerticles(s);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        numberOfVerticles = verticles.size();
        loadEdges();
    }

    private int[][] matrix() {
        int[][] matrix = new int[numberOfVerticles][numberOfVerticles];

        for (int i = 0; i < numberOfVerticles; i++) {
            for (int j = 0; j < numberOfVerticles; j++) {
                matrix[i][j] = matrixConst;
            }
        }

        for (Edge edge : edges) {
            matrix[edge.getFromIdVerticle()][edge.getToIdVerticle()] = edge.getDistance();
        }
        return matrix;
    }

    private void loadEdges() {
        ArrayList<Edge> edges_temp = new ArrayList<Edge>();
        for (Verticle verticle : verticles) {
            ArrayList<Edge> temp;
            temp = verticle.getEdges();
            for (Edge edge : temp) {
                if (!edges_temp.contains(edge)) {
                    edges_temp.add(edge);
                }
            }
        }
        this.edges = edges_temp;
    }

    public void rooms() {
        for (Verticle verticle : verticles) {
            if (verticle instanceof Room) {
                roomList.add((Room) verticle);
            }
        }
    }

    public void traceList(int idFrom, int idTo) {

        ArrayList<Integer> result;
        clearTraceList(); //vycisti list
        Arrow arrow;

        result = dijkstra.algoCompute(matrix(), numberOfVerticles, idFrom, idTo);

        for (int i = 0; i < result.size() - 2; i++) { //zvoli zaciatocny vrchol hrany z vyslednych vrcholov
            arrow = Arrow.ERROR;
            for (Verticle verticle : verticles) { //prejde zoznam vrcholov
                if (verticle.getId() == result.get(i)) { //najde zvoleny vrchol
                    for (Edge edge : verticle.getEdges()) { //prechadza hrany
                        if (edge.getToIdVerticle() == result.get(i + 1)) { //najde hranu
                            Integer i1 = result.get(i + 2);
                            if (i1.equals(edge.getLeftArrow())) { //switch najde smer
                                arrow = Arrow.LEFT;
                            } else if (i1.equals(edge.getRightArrow())) {
                                arrow = Arrow.RIGHT;
                            } else if (i1.equals(edge.getForwardArrow())) {
                                arrow = Arrow.FORWARD;
                            }
                            //prida obrazok do zoznamu
                            traceList.add(new StepImage(edge.getImage(), edge.getDistance(), arrow));
                            break;
                        }
                    }
                    break;
                }
            }
        }
         /*posledna hrana zatial takto natvrdo riesena
        TODO optimalizovat (ked bude chut, cas)*/
        for (Verticle verticle : verticles) {
            if (verticle.getId() == result.get(result.size() - 2)) {
                for (Edge edge : verticle.getEdges()) {
                    if (edge.getToIdVerticle() == result.get(result.size() - 1)) {
                        traceList.add(new StepImage(edge.getImage(), edge.getDistance(), Arrow.FORWARD));
                        break;
                    }
                }
                break;
            }
        }
        for (StepImage stepImage1 : traceList) {
            System.out.println("obr: " + stepImage1.getSceneImage());
            System.out.println("smer: " + stepImage1.getArrow());
        }
    }

    public Dijkstra getDijkstra(){
        return dijkstra;
    }
}
