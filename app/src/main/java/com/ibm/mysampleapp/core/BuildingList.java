package com.ibm.mysampleapp.core;

import com.ibm.mysampleapp.graph.Room;

import java.util.ArrayList;

/**
 * Slúži na implementovanie a pracu s listami medzi aktivitami
 */

public interface BuildingList {

    ArrayList<Room> rooms = new ArrayList<>();

    ArrayList<Building> buildingNames = new ArrayList<>();

}
