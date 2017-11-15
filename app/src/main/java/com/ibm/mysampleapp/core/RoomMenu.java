package com.ibm.mysampleapp.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ibm.mysampleapp.R;
import com.ibm.mysampleapp.adapters.RoomAdapter;
import com.ibm.mysampleapp.graph.Graph;

import java.io.InputStream;

/**
 * Táto class slúži ako aktivita pre zobrazenie zoznamu miestností pre danú budovu.
 */

public class RoomMenu extends AppCompatActivity implements BuildingList{

    private Building b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent autista4 = new Intent(RoomMenu.this,
                Navigation.class);

        setContentView(R.layout.room_menu);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            b = (Building)getIntent().getSerializableExtra("building");
        }
        Context context = getApplicationContext();
        InputStream iStream = context.getResources().openRawResource
                (getResources().getIdentifier(b.getXml(),
                "raw", getPackageName()));
        Graph g = new Graph(b.getName(), iStream);
        rooms.addAll(g.rooms());

        RoomAdapter roomAdapter = new RoomAdapter(rooms, getApplicationContext());
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
