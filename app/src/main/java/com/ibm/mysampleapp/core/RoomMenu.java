package com.ibm.mysampleapp.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.ibm.mysampleapp.R;
import com.ibm.mysampleapp.adapters.RoomAdapter;
import com.ibm.mysampleapp.algo.Dijkstra;
import com.ibm.mysampleapp.graph.Graph;
import com.ibm.mysampleapp.graph.Room;

import java.io.InputStream;

/**
 * Táto class slúži ako aktivita pre zobrazenie zoznamu miestností pre danú budovu.
 */

public class RoomMenu extends AppCompatActivity implements RoomList {

    private Building b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.room_menu);

        final Intent autista4 = new Intent(RoomMenu.this,
                Navigation.class);

        final EditText etSearchB = (EditText) findViewById(R.id.room_input);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            b = (Building) getIntent().getSerializableExtra("building");
        }
        Context context = getApplicationContext();
        InputStream iStream = context.getResources().openRawResource
                (getResources().getIdentifier(b.getXml(),
                        "raw", getPackageName()));
        Graph g = new Graph(b.getName(), iStream);

        if (!rooms.isEmpty()) {
            clearRoomList();
        }

        g.rooms();

        RoomAdapter roomAdapter = new RoomAdapter(rooms, getApplicationContext());
        ListView roomList = (ListView) findViewById(R.id.list);
        roomList.setAdapter(roomAdapter);

        roomList.setOnItemClickListener((parent, view, position, id) -> {
            Dijkstra dijkstra = new Dijkstra();
            Room room = roomAdapter.getItem(position);
            g.traceList(dijkstra.algoCompute(g.matrix(), g.getNumberOfVerticles(),
                    0, room.getId()));
            startActivity(autista4);
        });

        etSearchB.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                roomAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });
    }
}
