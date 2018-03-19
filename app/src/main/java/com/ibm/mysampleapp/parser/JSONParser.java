package com.ibm.mysampleapp.parser;

import java.util.ArrayList;
import com.ibm.mysampleapp.route.Route;
import com.ibm.mysampleapp.communication.GetJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Radoslav on 19.03.2018.
 */

public class JSONParser {

    public static ArrayList<Route> routeParser(){
        ArrayList<Route> routeArrayList = new ArrayList<Route>();
        GetJSON getJSON = new GetJSON();
        JSONArray jsonArray = getJSON.getJSON("http://192.168.0.100:8000/api/route/route/");

        if(jsonArray != null){
            System.out.println(jsonArray);
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = null;
                try {
                    jsonObject = jsonArray.getJSONObject(i);

                    if(jsonObject!=null) {
                        routeArrayList.add(new Route(jsonObject.getString("title")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return routeArrayList;
        }
        System.out.println("Nieco sa posralo");
        return null;
    }
}
