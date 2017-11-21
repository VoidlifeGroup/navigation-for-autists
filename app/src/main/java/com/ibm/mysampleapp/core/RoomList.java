package com.ibm.mysampleapp.core;

import com.ibm.mysampleapp.graph.Room;

import java.util.ArrayList;

/**
 *
 */

public interface RoomList {

    ArrayList<Room> roomList = new ArrayList<>();

    default void clearRoomList() {
        if (!roomList.isEmpty()) {
            roomList.clear();
        }
    }

}
