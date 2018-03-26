package com.ibm.mysampleapp.core.activities.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ibm.mysampleapp.R;


/**
 * Created by mato on 26. 3. 2018.
 */

public class NewRoute extends AppCompatActivity {

    ImageView routeImage = (ImageView) findViewById(R.id.routeImage);
    EditText routeName = (EditText) findViewById(R.id.routeName);
    Button createSteps = (Button) findViewById(R.id.create_steps);
    Button finishActivity = (Button) findViewById(R.id.finish_route);

    String routeNameString;
    int routeID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_route_layout);

        createSteps.setOnClickListener((View v) -> {
            Intent intent = new Intent(getApplicationContext(), NewStep.class);
            getApplicationContext().startActivity(intent);
        });


        finishActivity.setOnClickListener(v -> {
            routeNameString = routeName.getText().toString();
            if(nameIsEmpty(routeNameString)){
                routeName.setError("Zadajte názov trasy!");
            } else{
                Intent intent = new Intent(getApplicationContext(), RouteMenu.class);

                //TODO parsnúť route s routeNameString a vratiť routeID

                intent.putExtra("ROUTE_ID", routeID);
                getApplicationContext().startActivity(intent);
                finish();
            }
        });

    }

    public boolean nameIsEmpty(String routeNameString){
        return routeNameString.matches("");
    }
}