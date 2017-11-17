package com.ibm.mysampleapp.core;

import java.io.Serializable;
import java.util.ArrayList;

public class Building implements Serializable {
    private String name;
    private String xml;
    private ArrayList listOfRooms;

    public Building(String name, String xml) {
        this.name = name;
        this.xml = xml;
    }

    public String getName() {
        return name;
    }

    public String getXml() {
        return xml;
    }

    ArrayList getRooms() {
        return listOfRooms;
    }
}
