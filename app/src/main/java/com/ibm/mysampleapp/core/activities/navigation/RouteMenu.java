package com.ibm.mysampleapp.core.activities.navigation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ibm.mysampleapp.R;
import com.ibm.mysampleapp.adapters.MainMenuAdapter;
import com.ibm.mysampleapp.adapters.RouteAdapter;
import com.ibm.mysampleapp.data.MenuAssets;

import com.ibm.mysampleapp.parser.JSONParser;
/**
 * Created by mato on 19. 3. 2018.
 */

public class RouteMenu extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_rw);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RouteAdapter(this, JSONParser.routeParser());
        mRecyclerView.setAdapter(mAdapter);
    }
}
