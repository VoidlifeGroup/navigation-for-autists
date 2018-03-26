package com.ibm.mysampleapp.parser;

import android.widget.ImageView;

import java.util.ArrayList;

import com.ibm.mysampleapp.communication.URLs;
import com.ibm.mysampleapp.route.Route;
import com.ibm.mysampleapp.communication.GetJSON;
import com.ibm.mysampleapp.route.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Radoslav on 19.03.2018.
 */

public class JSONParser implements URLs{

    public static ArrayList<Route> routeParser(){
        ArrayList<Route> routeArrayList = new ArrayList<Route>();
        GetJSON getJSON = new GetJSON();
        JSONArray jsonArray = getJSON.getJSON(ADDRESS+API+ROUTE);

        if(jsonArray != null){
            System.out.println(jsonArray);
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = null;
                try {
                    jsonObject = jsonArray.getJSONObject(i);

                    if(jsonObject!=null) {
                        routeArrayList.add(new Route(
                                jsonObject.getString("title"),
                                jsonObject.getString("id")));
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

    public static ArrayList<Steps> stepsParser(String id){
        ArrayList<Steps> routeArrayList = new ArrayList<Steps>();
        GetJSON getJSON = new GetJSON();
        JSONArray jsonArray = getJSON.getJSON(ADDRESS+API+STEPS+QUERY+id);

//        if(jsonArray != null){
//            System.out.println(jsonArray);
//            for(int i=0; i<jsonArray.length(); i++){
//                JSONObject jsonObject = null;
//                try {
//                    jsonObject = jsonArray.getJSONObject(i);
//
//                    if(jsonObject!=null) {
//                        routeArrayList.add(new Steps(
//                                jsonObject.getString("deskription"),
//                                int step,
//                                int distance,
//                                String arrows,
//                                String routeTitle,
//                                ImageView image));
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            return routeArrayList;
//        }
        System.out.println("Nieco sa posralo");

        return null;
    }
}
