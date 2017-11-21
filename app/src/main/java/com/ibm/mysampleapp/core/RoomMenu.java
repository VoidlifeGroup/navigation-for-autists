package com.ibm.mysampleapp.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import java.io.InputStream;

import com.ibm.mysampleapp.R;
import com.ibm.mysampleapp.adapters.RoomAdapter;
import com.ibm.mysampleapp.graph.Graph;
import com.ibm.mysampleapp.graph.Room;


/**
 * Táto class slúži ako aktivita pre zobrazenie zoznamu miestností pre danú budovu.
 */

public class RoomMenu extends AppCompatActivity implements RoomList {

    private Building building;
    private Graph graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.room_menu);

        final Intent goToMainActivity = new Intent(RoomMenu.this,
                MainActivity.class);

        final EditText etSearchB = (EditText) findViewById(R.id.room_input);

        readRooms();

        RoomAdapter roomAdapter = new RoomAdapter(roomList, getApplicationContext());
        ListView roomListView = (ListView) findViewById(R.id.list);
        roomListView.setAdapter(roomAdapter);

        roomListView.setOnItemClickListener((parent, view, position, id) -> {
            Room room = roomAdapter.getItem(position);
            graph.traceList(0, room.getId());//TODO prednastavene id 0
            goToMainActivity.putExtra("room_name", roomAdapter.getItem(position).getName());
            startActivity(goToMainActivity);
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

    private void readRooms() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            building = (Building) getIntent().getSerializableExtra("building");
        }
        Context context = getApplicationContext();
        InputStream iStream = context.getResources().openRawResource
                (getResources().getIdentifier(building.getXml(),
                        "raw", getPackageName()));
        graph = new Graph(building.getName(), iStream);

        clearRoomList();
        graph.rooms();
    }
}
