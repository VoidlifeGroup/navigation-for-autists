package com.ibm.mysampleapp.graph;

import com.ibm.mysampleapp.core.navigation.Arrow;
import com.ibm.mysampleapp.core.RoomList;
import com.ibm.mysampleapp.core.navigation.StepImage;
import com.ibm.mysampleapp.core.navigation.TraceList;
import com.ibm.mysampleapp.graph.algorithms.Dijkstra;
import com.ibm.mysampleapp.parser.XmlPullParserNFA;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Trieda Graph primárne slúži na uloženie zoznamu vrcholov a hrán grafu.
 * */
public class Graph implements RoomList, TraceList {

    private final static int matrixConst = 9999;
    private final int numberOfVerticles;
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    private ArrayList<Verticle> verticles = new ArrayList<Verticle>();
    private Dijkstra dijkstra = new Dijkstra();

    /**
     * Konštruktor načíta graf z xml súboru
     */
    public Graph(InputStream s) {
        XmlPullParserNFA p = new XmlPullParserNFA();
        try {
            verticles = p.parseVerticles(s);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        numberOfVerticles = verticles.size();
        loadEdges();
    }

    /**
     * Načíta hrany, ktoré už sú uložené v zoznamoch hrán vrcholov
     */
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

    /**
     * Pripraví maticu hrán a ich dĺžok pre Dijktstrov algoritmus
     * @return vráti maticu vzdialeností
     */
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

    /**
     * Vyčistí roomList a následne ho naplní miestosťami zo zoznamu vrcholov
     */
    public void rooms() {
        clearRoomList();
        for (Verticle verticle : verticles) {
            if (verticle instanceof Room) {
                roomList.add((Room) verticle);
            }
        }
    }

    /**
     * Pomocou Dijkstrovho algoritmu nájde najkratšiu cestu a túto trasu potom uloží pomocou
     * StepImage do predtým vyčisteného traceListu
     * @param idFrom id vrcholu (room), z ktorého sa má hľadať cesta
     * @param idTo id vrcholu (room), do ktorého sa má hľadať cesta
     */
    public void traceList(int idFrom, int idTo) {

        ArrayList<Integer> result;
        clearTraceList(); //vycisti list

        result = dijkstra.algoCompute(matrix(), numberOfVerticles, idFrom, idTo);

        for (int i = 0; i < result.size() - 2; i++) { //zvoli zaciatocny vrchol hrany z vyslednych vrcholov
            for (Verticle verticle : verticles) { //prejde zoznam vrcholov
                if (verticle.getId() == result.get(i)) { //najde zvoleny vrchol
                    for (Edge edge : verticle.getEdges()) { //prechadza hrany
                        if (edge.getToIdVerticle() == result.get(i + 1)) { //najde hranu
                            traceList.add(new StepImage(edge.getImage(), edge.getDistance(),
                                    Arrow.FORWARD));
                            /*Ak dalsia hrana bude menit smer, to znamena uz nebude rovno,
                            vlozime dalsi stepImage, ktory bude sluzit na signalizaciu otocenia. */
                            Integer i1 = result.get(i + 2);
                            if (i1.equals(edge.getLeftArrow()) && edge.getImage2() != null) {
                                //switch najde smer
                                traceList.add(new StepImage(edge.getImage2(), 0,
                                        Arrow.LEFT));
                            } else if (i1.equals(edge.getRightArrow()) && edge.getImage2() != null){
                                traceList.add(new StepImage(edge.getImage2(), 0,
                                        Arrow.RIGHT));
                            }
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
                        traceList.add(new StepImage(edge.getImage(), edge.getDistance(),
                                Arrow.FORWARD));
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
}
