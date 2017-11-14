package com.ibm.mysampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ibm.mysampleapp.adapters.BuildingAdapter;

/**
 * Táto class slúži ako aktivita pre zobrazenie zoznamu budov ktoré naša aplikácia podporuje.
 */

public class BuildingMenu extends AppCompatActivity implements BuildingList{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.building_menu);

        final Intent autista3 = new Intent(BuildingMenu.this,
                RoomMenu.class);

        // TODO Načítvať zoznam miestností zo súboru
        roomNames.add(new Room("F125"));
        roomNames.add(new Room("F130"));
        roomNames.add(new Room("F131"));
        // TODO Načítavať zoznam budov zo súboru
        buildingNames.add(new Building("Univerzita Mateja Bela", roomNames));
        buildingNames.add(new Building("Univerzita Komenského", roomNames));

        BuildingAdapter cAdapter = new BuildingAdapter(buildingNames, getApplicationContext());
        ListView buildingList = (ListView) findViewById(R.id.list);
        buildingList.setAdapter(cAdapter);
        buildingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked perform some action...
                startActivity(autista3);
            }
        });
    }
}
