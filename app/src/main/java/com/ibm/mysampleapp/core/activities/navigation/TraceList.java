package com.ibm.mysampleapp.core.activities.navigation;

import java.util.ArrayList;

/**
 * Created by Radoslav on 15.11.2017.
 */

public interface TraceList {

    ArrayList<StepImage> traceList = new ArrayList<>();

    default void clearTraceList() {
        if (!traceList.isEmpty()) {
            traceList.clear();
        }
    }

}
