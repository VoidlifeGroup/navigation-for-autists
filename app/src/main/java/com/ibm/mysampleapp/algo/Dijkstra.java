package com.ibm.mysampleapp.algo;

import java.util.ArrayList;
import java.util.Collections;

public class Dijkstra {

    public ArrayList<Integer> algoCompute(int[][] matrix, int numberOfVerticles, int startID,
                                          int endID) {

        int[] preD = new int[numberOfVerticles];
        ArrayList<Integer> result = new ArrayList<Integer>();
        int min, nextVerticle = 0; // min holds the minimum value, nextVerticle holds the value for the next Verticle.
        int[] distance = new int[numberOfVerticles]; // the distance matrix
        int[] visited = new int[numberOfVerticles]; // the visited array

        for (int i = 0; i < distance.length; i++) {
            visited[i] = 0; //initialize visited array to zeros
            preD[i] = startID;
        }

        distance = matrix[startID]; //initialize the distance array
        visited[startID] = 1; //set the source Verticle as visited
        distance[startID] = 0; //set the distance from source to source to zero which is the starting point

        for (int counter = 0; counter < numberOfVerticles; counter++) {
            min = 9999;
            for (int i = 0; i < numberOfVerticles; i++) {
                if (min > distance[i] && visited[i] != 1) {
                    min = distance[i];
                    nextVerticle = i;
                }
            }

            visited[nextVerticle] = 1;
            for (int i = 0; i < numberOfVerticles; i++) {
                if (visited[i] != 1) {
                    if (min + matrix[nextVerticle][i] < distance[i]) {
                        distance[i] = min + matrix[nextVerticle][i];
                        preD[i] = nextVerticle;
                    }

                }

            }

        }
        // Názorné výpisy
        for (int i = 0; i < numberOfVerticles; i++)
            System.out.print("|" + distance[i]);

        System.out.println("|");

        int j;
        for (int i = 0; i < numberOfVerticles; i++) {
            if (i != startID) {

                System.out.print("Path = " + i);
                j = i;
                do {
                    j = preD[j];
                    System.out.print(" <- " + j);
                }
                while (j != startID);
            }
            System.out.println();
        }

        j = endID;
        while (j != startID) {
            result.add(j);
            j = preD[j];
        }
        result.add(startID);
        Collections.reverse(result);
        return result;
    }
}
