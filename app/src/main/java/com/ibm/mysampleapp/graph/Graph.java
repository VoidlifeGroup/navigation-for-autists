package com.ibm.mysampleapp.graph;

import com.ibm.mysampleapp.core.RoomList;
import com.ibm.mysampleapp.core.StepImage;
import com.ibm.mysampleapp.core.TraceList;
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

    public int[][] matrix() {
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
                rooms.add((Room) verticle);
            }
        }
    }

    public int getNumberOfVerticles() {
        return numberOfVerticles;
    }

    public void traceList(ArrayList<Integer> result) {

        clearTraceList();

        boolean forward, right, left;
        for (int i = 0; i < result.size() - 2; i++) {
            forward = right = left = false;
            for (Verticle verticle : verticles) {
                if (verticle.getId() == result.get(i)) {
                    for (Edge edge : verticle.getEdges()) {
                        if (edge.getToIdVerticle() == result.get(i + 1)) {
                            Integer i1 = result.get(i + 2);
                            if (i1.equals(edge.getLeftArrowIdVerticle())) {
                                left = true;

                            } else if (i1.equals(edge.getRigthArrowIdVerticle())) {
                                right = true;

                            } else if (i1.equals(edge.getUpArrowIdVerticle())) {
                                forward = true;

                            }
                            traceList.add(new StepImage(edge.getImage(), right, left, forward));
                            break;
                        }
                    }
                    break;
                }
            }
        }
        /*posledna hrana zatial takto natvrdo riesena
        TODO optimalizovat (ked bude chut, cas)*/
        System.out.println("idem");
        for (Verticle verticle : verticles) {
            if (verticle.getId() == result.get(result.size() - 2)) {
                for (Edge edge : verticle.getEdges()) {
                    if (edge.getToIdVerticle() == result.get(result.size() - 1)) {
                        traceList.add(new StepImage(edge.getImage(), false,
                                false, false));
                        break;
                    }
                }
                break;
            }
        }
//        notifyMe();
        for (StepImage stepImage1 : traceList) {
            System.out.println("obr: " + stepImage1.getSceneImage());
            System.out.println("F: " + stepImage1.getForward());
            System.out.println("R: " + stepImage1.getRight());
            System.out.println("L: " + stepImage1.getLeft());
        }
    }
}
