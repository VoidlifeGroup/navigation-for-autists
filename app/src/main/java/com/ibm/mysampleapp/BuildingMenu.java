package com.ibm.mysampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by mato on 1. 11. 2017.
 */

public class BuildingMenu extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //TODO MENU BUTTONS

        setContentView(R.layout.building_menu);

        final Intent autista3 = new Intent(BuildingMenu.this, BuildingNavigation.class);

        Button insideBuilding = (Button) findViewById(R.id.buildingButton1);

        insideBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(autista3);
            }

        });




    }
}
