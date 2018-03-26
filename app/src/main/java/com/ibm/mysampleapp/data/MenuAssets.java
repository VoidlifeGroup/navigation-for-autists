package com.ibm.mysampleapp.data;

import com.ibm.mysampleapp.route.Route;

import java.util.ArrayList;

/**
 * Created by mato on 18. 3. 2018.
 */

public class MenuAssets {

    private static final ArrayList<String> menuList = new ArrayList<String>() {{
        add("VYBRAŤ TRASU");
    }};

    private static final ArrayList<String> chooseMode = new ArrayList<String>() {{
        add("DIJKSTRA MÓD");
        add("VLASTNÁ TRASA");
    }};

    private static final ArrayList<String> customMenuList = new ArrayList<String>() {{
        add("VYBRAŤ TRASU");
        add("EDITOVAŤ TRASU");
        add("NOVÁ TRASA");
    }};

    private static final ArrayList<Route> routeList = new ArrayList<Route>() {{
//        add(new Route("WC"));
//        add(new Route("F134"));
//        add(new Route("F135"));
//        add(new Route("F136"));
//        add(new Route("F137"));
//        add(new Route("F138"));
    }};





    public static ArrayList<String> getList(){
        return menuList;
    }

    public static ArrayList<String> getChooseMode(){
        return chooseMode;
    }

    public static ArrayList<String> getCustomMenuList(){
        return customMenuList;
    }

    public static ArrayList<Route> getRouteList(){
        return routeList;
    }
}
