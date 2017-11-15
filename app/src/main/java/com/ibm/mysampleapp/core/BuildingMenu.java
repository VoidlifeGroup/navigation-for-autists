package com.ibm.mysampleapp.core;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ibm.mysampleapp.R;
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

        // TODO Načítvať zoznam miestností zo súboru, bude po vybratí budovy

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
