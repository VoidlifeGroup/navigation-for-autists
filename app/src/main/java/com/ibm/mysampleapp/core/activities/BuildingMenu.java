package com.ibm.mysampleapp.core.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.ibm.mysampleapp.R;
import com.ibm.mysampleapp.adapters.BuildingAdapter;
import com.ibm.mysampleapp.core.Building;
import com.ibm.mysampleapp.core.BuildingList;
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

        final Intent goToMainActivity = new Intent(BuildingMenu.this,
                MainActivity.class);

        final EditText etSearchB = (EditText) findViewById(R.id.building_input);

        readBuildings();

        final BuildingAdapter cAdapter = new BuildingAdapter(buildingList, getApplicationContext());
        ListView buildingListView = (ListView) findViewById(R.id.list);
        buildingListView.setAdapter(cAdapter);
        buildingListView.setOnItemClickListener((parent, view, position, id) -> {
            Building b = cAdapter.getItem(position);
            goToMainActivity.putExtra("building", b);
            goToMainActivity.putExtra("name_building", cAdapter.getItem(position).getName());
            startActivity(goToMainActivity);
            finish();
        });

        etSearchB.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Call back the Adapter with current character to Filter
                cAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });
    }

    /**
     * Nacita budovy do listu z xml suboru
     * Pred naplnenim zoznamu ho zmaze
     * */
    private void readBuildings(){
        Context context = getApplicationContext();
        InputStream iStream = context.getResources().openRawResource(R.raw.buildings);
        XmlPullParserNFA p = new XmlPullParserNFA();
        ArrayList<Building> buildings = new ArrayList<>();

        try {
            buildings = p.parseBuildings(iStream);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        clearBuildingList();
        buildingList.addAll(buildings);
    }
}
