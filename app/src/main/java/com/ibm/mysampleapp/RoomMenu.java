package com.ibm.mysampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ibm.mysampleapp.adapters.RoomAdapter;

/**
 * Táto class slúži ako aktivita pre zobrazenie zoznamu miestností pre danú budovu.
 */

public class RoomMenu extends AppCompatActivity implements BuildingList{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent autista4 = new Intent(RoomMenu.this,
                BuildingNavigation.class);

        setContentView(R.layout.room_menu);

        //TODO musí vypisovať cez custom adapter inak zatial vypisuje miesto nazvu miestnosti jej adresu
        RoomAdapter roomAdapter = new RoomAdapter(roomNames, getApplicationContext());
        ListView roomList = (ListView) findViewById(R.id.list);
        roomList.setAdapter(roomAdapter);
        roomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked perform some action...
                startActivity(autista4);
            }
        });
    }
}
