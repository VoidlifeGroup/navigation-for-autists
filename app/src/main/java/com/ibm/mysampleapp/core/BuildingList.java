package com.ibm.mysampleapp.core;

import java.util.ArrayList;

/**
 *
 */

public interface BuildingList {

    ArrayList<Building> buildingNames = new ArrayList<>();

    default void clearBuildingList() {
        buildingNames.clear();
    }

}
