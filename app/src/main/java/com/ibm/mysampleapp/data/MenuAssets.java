package com.ibm.mysampleapp.data;

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



    public static ArrayList<String> getList(){
        return menuList;
    }

    public static ArrayList<String> getChooseMode(){
        return chooseMode;
    }

    public static ArrayList<String> getCustomMenuList(){
        return customMenuList;
    }
}
