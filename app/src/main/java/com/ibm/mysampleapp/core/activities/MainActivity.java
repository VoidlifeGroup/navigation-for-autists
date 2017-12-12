package com.ibm.mysampleapp.core.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.cloudant.sync.datastore.Datastore;
import com.cloudant.sync.datastore.DatastoreManager;
import com.cloudant.sync.datastore.DatastoreNotCreatedException;
import com.ibm.bluemix.appid.android.api.AppID;
import com.ibm.bluemix.appid.android.api.AppIDAuthorizationManager;
import com.ibm.mobilefirstplatform.clientsdk.android.core.api.BMSClient;
import com.ibm.mysampleapp.R;
import com.ibm.mysampleapp.core.Building;
import com.ibm.mysampleapp.core.activities.navigation.Navigation;
import com.ibm.mysampleapp.graph.algorithms.Dijkstra;


public class MainActivity extends AppCompatActivity {

    private java.net.URI cloudantUri;
    private Datastore ds;
    private DatastoreManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button chooseBuilding = (Button) findViewById(R.id.choose_building);
        Button chooseFrom = (Button) findViewById(R.id.choose_from);
        Button chooseTo = (Button) findViewById(R.id.choose_to);
        Button searchBttn = (Button) findViewById(R.id.search_button);

        Typeface centuryGothicRglr = Typeface.createFromAsset(getAssets(),
                "fonts/century_gothic_regular.ttf");
        chooseBuilding.setTypeface(centuryGothicRglr);
        chooseFrom.setTypeface(centuryGothicRglr);
        chooseTo.setTypeface(centuryGothicRglr);
        searchBttn.setTypeface(centuryGothicRglr);

        // Core SDK must be initialized to interact with Bluemix Mobile services.
        BMSClient.getInstance().initialize(getApplicationContext(), BMSClient.REGION_UK);


        AppID.getInstance().initialize(this, getString(R.string.authTenantId),
                BMSClient.REGION_UK);
        BMSClient.getInstance().setAuthorizationManager(new AppIDAuthorizationManager(
                AppID.getInstance()));


        // Create a DatastoreManager using application internal storage path
        java.io.File path = getApplicationContext().getDir("datastores",
                android.content.Context.MODE_PRIVATE);
        manager = DatastoreManager.getInstance(path);

        try {
            cloudantUri = new java.net.URI(getApplicationContext().getResources().
                    getString(R.string.cloudantUrl1) + "/your_db_name");
            // Create the Datastore object you'll use in all of your Cloudant operations:
            ds = manager.openDatastore("my_datastore");

            // At this point, you may wish to create pull and push replicators for bi-directional
            // sync.  It is up
            // to you, the developer, to program the interaction between the Cloudant database and
            // the mobile application.
            // A simple example follows.  The example does not include a countdown latch, does not
            // show how to
            // subscribe to sync events, and ignores sync errors.

            // Create and run the pull replicator
            //com.cloudant.sync.replication.Replicator pullReplicator =
            // com.cloudant.sync.replication.ReplicatorBuilder.pull().from(cloudantUri).to(ds).
            // build();
            //pullReplicator.start();
            // Create and run the push replicator
            //com.cloudant.sync.replication.Replicator pushReplicator =
            // com.cloudant.sync.replication.ReplicatorBuilder.push().to(cloudantUri).from(ds).
            // build();
            //pushReplicator.start();

        } catch (java.net.URISyntaxException | DatastoreNotCreatedException e) {
            android.util.Log.e("TAG", e.getMessage(), e);
        }


        //----------------------------------------------------------------------------
        //Prepinanie medzi aktivitami
        //TODO kedze vzniklo kopec bugov pri tomto prepinaní a spuštaní a zachovavaní stavov tak to
        //vsetko treba pofixovat... pravdepodobne sa to bude riešiť cez Enum, ked zistií kde
        //vznikli bugy

        if (extras != null && (extras.containsKey("name_building"))) {
            chooseBuilding.setText((String) getIntent().getSerializableExtra("name_building"));
            chooseBuilding.setTextColor(Color.BLACK);
            chooseFrom.setText("Vchod");
            chooseFrom.setTextColor(Color.BLACK);
        }

        if (extras != null && extras.containsKey("room_name")){
            chooseTo.setText((String) getIntent().getSerializableExtra(
                    "room_name"));
            chooseTo.setTextColor(Color.BLACK);
        }

        final Intent goToBuildingMenu = new Intent(MainActivity.this,
                BuildingMenu.class);

        chooseBuilding.setOnClickListener((View v) -> {
            startActivity(goToBuildingMenu);
        });

        final Intent goToRoomMenu = new Intent(MainActivity.this, RoomMenu.class);

        chooseTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Building b = null;
                if(extras != null && (extras.containsKey("building"))){
                    b = (Building) getIntent().getSerializableExtra("building");
                }
                if (extras != null && (extras.containsKey("dijkstra"))) {
                    Dijkstra dijkstra = (Dijkstra) getIntent()
                            .getSerializableExtra("dijkstra");
                    goToRoomMenu.putExtra("dijkstra", dijkstra);
                }
                goToRoomMenu.putExtra("building", b);
                startActivity(goToRoomMenu);
            }
        });

        final Intent goToNavigation = new Intent(MainActivity.this, Navigation.class);

        searchBttn.setOnClickListener((View v) -> {
            if (extras != null && (extras.containsKey("dijkstra"))) {
                Dijkstra dijkstra = (Dijkstra) getIntent()
                        .getSerializableExtra("dijkstra");
                goToNavigation.putExtra("dijkstra", dijkstra);
            }
            startActivity(goToNavigation);
        });
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }
}
