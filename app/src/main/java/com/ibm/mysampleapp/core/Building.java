package com.ibm.mysampleapp.core;

import java.util.ArrayList;

public class Building {
    private String name;
    private ArrayList listOfRooms;

    Building(String name, ArrayList listOfRooms) {
        this.name = name;
        this.listOfRooms = listOfRooms;
    }

    public String getName(){
        return name;
    }

    ArrayList getRooms(){
        return listOfRooms;
    }
}
