package com.ibm.mysampleapp.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ibm.mysampleapp.R;
import com.ibm.mysampleapp.adapters.BuildingAdapter;
import com.ibm.mysampleapp.parser.XmlPullParserNFA;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Táto class slúži ako aktivita pre zobrazenie zoznamu budov ktoré naša aplikácia podporuje.
 */

public class BuildingMenu extends AppCompatActivity implements BuildingList {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.building_menu);

        final Intent autista3 = new Intent(BuildingMenu.this,
                RoomMenu.class);


        final Context context = getApplicationContext();
        InputStream iStream = context.getResources().openRawResource(R.raw.buildings);
        XmlPullParserNFA p = new XmlPullParserNFA();
        ArrayList<Building> buildings = new ArrayList<>();
        try {
            buildings = p.parseBuildings(iStream);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        buildingNames.addAll(buildings);

        final BuildingAdapter cAdapter = new BuildingAdapter(buildingNames, getApplicationContext());
        ListView buildingList = (ListView) findViewById(R.id.list);
        buildingList.setAdapter(cAdapter);
        buildingList.setOnItemClickListener((parent, view, position, id) -> {
            Building b = cAdapter.getItem(position);
            autista3.putExtra("building", b);
            startActivity(autista3);
        });
    }
}
