package com.ibm.mysampleapp.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.ibm.mobilefirstplatform.clientsdk.android.core.api.BMSClient;


import com.ibm.bluemix.appid.android.api.AppID;
import com.ibm.bluemix.appid.android.api.AppIDAuthorizationManager;

import com.cloudant.sync.datastore.Datastore;
import com.cloudant.sync.datastore.DatastoreManager;
import com.cloudant.sync.datastore.DatastoreNotCreatedException;
import com.ibm.mysampleapp.R;
import com.ibm.mysampleapp.algo.Dijkstra;
import com.ibm.mysampleapp.graph.Graph;

import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements BuildingList{

    private boolean ASISTENT_MODE_ACTIVE = false;
    private java.net.URI cloudantUri;
    private Datastore ds;
    private DatastoreManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        

        // Core SDK must be initialized to interact with Bluemix Mobile services.
        BMSClient.getInstance().initialize(getApplicationContext(), BMSClient.REGION_UK);



        AppID.getInstance().initialize(this, getString(R.string.authTenantId), BMSClient.REGION_UK);
        BMSClient.getInstance().setAuthorizationManager(new AppIDAuthorizationManager(AppID.getInstance()));


        // Create a DatastoreManager using application internal storage path
        java.io.File path = getApplicationContext().getDir("datastores", android.content.Context.MODE_PRIVATE);
        manager = DatastoreManager.getInstance(path);

        try {
            cloudantUri = new java.net.URI(getApplicationContext().getResources().getString(R.string.cloudantUrl1) + "/your_db_name");
            // Create the Datastore object you'll use in all of your Cloudant operations:
            ds = manager.openDatastore("my_datastore");

            // At this point, you may wish to create pull and push replicators for bi-directional sync.  It is up
            // to you, the developer, to program the interaction between the Cloudant database and the mobile application.
            // A simple example follows.  The example does not include a countdown latch, does not show how to
            // subscribe to sync events, and ignores sync errors.

            // Create and run the pull replicator
            //com.cloudant.sync.replication.Replicator pullReplicator = com.cloudant.sync.replication.ReplicatorBuilder.pull().from(cloudantUri).to(ds).build();
            //pullReplicator.start();
            // Create and run the push replicator
            //com.cloudant.sync.replication.Replicator pushReplicator = com.cloudant.sync.replication.ReplicatorBuilder.push().to(cloudantUri).from(ds).build();
            //pushReplicator.start();

        } catch (java.net.URISyntaxException | DatastoreNotCreatedException e) {
            android.util.Log.e("TAG", e.getMessage(), e);
        }

        final Intent autista2 = new Intent(MainActivity.this, BuildingMenu.class);

        Button buildingMenu = (Button) findViewById(R.id.button);

        buildingMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(autista2);
            }

        });

        // Iba dočasné znázornenie prvotnej funkcionality triedy Graph a dijk. algoritmu
//        Context context = getApplicationContext();
//        InputStream iStream = context.getResources().openRawResource(R.raw.fpvumb);
//
//        Graph g = new Graph("Test FPV UMB", iStream);
//        int[][] matica = g.matrix();
//        Dijkstra dijk = new Dijkstra();
//        ArrayList<Integer> al;
//        al = dijk.algoCompute(matica, g.getNumberOfVerticles(), 7, 2);
//        System.out.println("velkost arraylistu al: " + al.size());
//        for(int i = 0; i < al.size(); i++){
//            System.out.println("Cesta ide takto(id vrcholov): " + al.get(i));
//        }
//        g.traceList(al);

        // Iba dočasné znázornenie prvotnej funkcionality triedy Graph a dijk. algoritmu
        // testovaci graf
        Context context = getApplicationContext();
        InputStream iStream = context.getResources().openRawResource(R.raw.testovaci_graf);

        Graph g = new Graph("Test", iStream);
        int[][] matica = g.matrix();
        Dijkstra dijk = new Dijkstra();
        ArrayList<Integer> al;
        al = dijk.algoCompute(matica, g.getNumberOfVerticles(), 10, 8);
        System.out.println("velkost arraylistu al: " + al.size());
        for(int i = 0; i < al.size(); i++){
            System.out.println("Cesta ide takto(id vrcholov): " + al.get(i));
        }
        g.traceList(al);


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
