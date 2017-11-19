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

}
