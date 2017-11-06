package com.ibm.mysampleapp;

import java.util.ArrayList;

class Building {
    private String name;
    private ArrayList listOfRooms;

    Building(String name, ArrayList listOfRooms) {
        this.name = name;
        this.listOfRooms = listOfRooms;
    }

    String getName(){
        return name;
    }

    ArrayList getListOfRooms(){
        return listOfRooms;
    }
}
