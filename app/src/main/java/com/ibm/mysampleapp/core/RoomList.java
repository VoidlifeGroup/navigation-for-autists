package com.ibm.mysampleapp.core;

import com.ibm.mysampleapp.graph.Room;

import java.util.ArrayList;

/**
 *
 */

public interface RoomList {

    ArrayList<Room> rooms = new ArrayList<>();

    default void clearRoomList() {
        rooms.clear();
    }

}
