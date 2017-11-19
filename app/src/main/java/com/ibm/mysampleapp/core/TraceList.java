package com.ibm.mysampleapp.core;

import java.util.ArrayList;

/**
 * Created by Radoslav on 15.11.2017.
 */

public interface TraceList {

    ArrayList<StepImage> traceList = new ArrayList<>();

    default void clearTraceList() {
        traceList.clear();
    }

    default void waitMe(){
        try {
            traceList.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    default void notifyMe(){
        traceList.notify();
    }

}
