package com.ibm.mysampleapp.core;

import java.util.ArrayList;

/**
 *
 */

public interface BuildingList {

    ArrayList<Building> buildingList = new ArrayList<>();

    default void clearBuildingList() {
        if (!buildingList.isEmpty()) {
            buildingList.clear();
        }
    }

}
